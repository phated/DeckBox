package com.r0adkll.deckbuilder.arch.ui.features.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ftinc.kit.arch.presentation.BaseFragment
import com.ftinc.kit.arch.presentation.delegates.StatefulFragmentDelegate
import com.ftinc.kit.arch.util.uiDebounce
import com.ftinc.kit.widget.EmptyView
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
import com.jakewharton.rxrelay2.PublishRelay
import com.r0adkll.deckbuilder.R
import com.r0adkll.deckbuilder.arch.domain.Format
import com.r0adkll.deckbuilder.arch.domain.features.expansions.model.Expansion
import com.r0adkll.deckbuilder.arch.ui.features.browser.adapter.ExpansionRecyclerAdapter
import com.r0adkll.deckbuilder.arch.ui.features.browser.adapter.Item
import com.r0adkll.deckbuilder.arch.ui.features.browser.di.BrowseModule
import com.r0adkll.deckbuilder.arch.ui.features.home.di.HomeComponent
import com.r0adkll.deckbuilder.arch.ui.features.search.SearchActivity
import com.r0adkll.deckbuilder.arch.ui.features.setbrowser.SetBrowserActivity
import com.r0adkll.deckbuilder.internal.analytics.Analytics
import com.r0adkll.deckbuilder.internal.analytics.Event
import com.r0adkll.deckbuilder.util.DialogUtils
import com.r0adkll.deckbuilder.util.DialogUtils.DialogText.Resource
import com.r0adkll.deckbuilder.util.ScreenUtils
import com.r0adkll.deckbuilder.util.ScreenUtils.smallestWidth
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_browse.*
import javax.inject.Inject

class BrowseFragment : BaseFragment(), BrowseUi, BrowseUi.Actions, BrowseUi.Intentions {

    override var state: BrowseUi.State = BrowseUi.State.DEFAULT

    @Inject lateinit var renderer: BrowseRenderer
    @Inject lateinit var presenter: BrowsePresenter

    private lateinit var adapter: ExpansionRecyclerAdapter

    private val downloadClicks = PublishRelay.create<Expansion>()
    private val dismissClicks = PublishRelay.create<Unit>()
    private val downloadFormatClicks = PublishRelay.create<Format>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browse, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ExpansionRecyclerAdapter(requireActivity(), downloadClicks, dismissClicks, downloadFormatClicks) {
            when (it) {
                is Item.ExpansionSet -> {
                    Analytics.event(Event.SelectContent.BrowseExpansionSet(it.expansion.code))
                    val intent = SetBrowserActivity.createIntent(requireActivity(), it.expansion)
                    startActivity(intent)
                }
                else -> {}
            }
        }
        adapter.emptyView = emptyView
        recycler.layoutManager = if (smallestWidth(ScreenUtils.Config.TABLET_10)) {
            GridLayoutManager(requireActivity(), TABLET_SPAN_SIZE)
        } else {
            LinearLayoutManager(requireActivity())
        }
        recycler.adapter = adapter
        (recycler.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false

        swipeRefresh.setColorSchemeResources(
            R.color.poketype_fire,
            R.color.poketype_grass,
            R.color.poketype_water,
            R.color.poketype_electric,
            R.color.poketype_fighting,
            R.color.poketype_psychic,
            R.color.poketype_steel,
            R.color.poketype_dragon,
            R.color.poketype_fairy,
            R.color.poketype_dark
        )

        actionSearch.setOnClickListener {
            startActivity(SearchActivity.createIntent(it.context))
        }
    }

    override fun setupComponent() {
        getComponent(HomeComponent::class)
            .plus(BrowseModule(this))
            .inject(this)

        delegates += StatefulFragmentDelegate(renderer, Lifecycle.Event.ON_START)
        delegates += StatefulFragmentDelegate(presenter, Lifecycle.Event.ON_START)
    }

    override fun render(state: BrowseUi.State) {
        this.state = state
        renderer.render(state)
    }

    override fun refreshExpansions(): Observable<Unit> {
        return swipeRefresh.refreshes()
            .doOnNext {
                Analytics.event(Event.SelectContent.Action("refresh_expansions"))
            }
    }

    override fun downloadExpansion(): Observable<Expansion> {
        return downloadClicks
            .doOnNext {
                Analytics.event(Event.SelectContent.Action("download_expansion", it.name))
            }
    }

    override fun downloadFormatExpansions(): Observable<List<Expansion>> {
        return downloadFormatClicks
            .uiDebounce()
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { format ->
                val formatName = format.name.toLowerCase().capitalize()
                val expansions = state.expansions.filter {
                    when (format) {
                        Format.STANDARD -> it.standardLegal
                        Format.EXPANDED -> it.expandedLegal
                        else -> true
                    }
                }
                Analytics.event(Event.SelectContent.Action("download_format", format.name))
                DialogUtils.confirmDialog(requireActivity(),
                    Resource(R.string.dialog_confirm_download_format, formatName),
                    Resource(R.string.dialog_confirm_download_format_message, expansions.size, formatName),
                    R.string.action_download, android.R.string.cancel)
                    .flatMap {
                        if (it) {
                            Analytics.event(Event.SelectContent.Action("download_format", "accepted"))
                            Observable.just(expansions)
                        } else {
                            Analytics.event(Event.SelectContent.Action("download_format", "denied"))
                            Observable.empty()
                        }
                    }
            }
    }

    override fun hideOfflineOutline(): Observable<Unit> {
        return dismissClicks
            .doOnNext {
                Analytics.event(Event.SelectContent.Action("hide_offline_outline"))
            }
    }

    override fun setExpansionsItems(items: List<Item>) {
        adapter.submitList(items)
    }

    override fun showLoading(isLoading: Boolean) {
        emptyView.state = if (isLoading) {
            EmptyView.State.LOADING
        } else {
            EmptyView.State.EMPTY
        }
        swipeRefresh.isRefreshing = isLoading
    }

    override fun showError(description: String) {
        emptyView.message = description
    }

    override fun hideError() {
        emptyView.setMessage(R.string.empty_browse_message)
    }

    companion object {
        private const val TABLET_SPAN_SIZE = 3

        fun newInstance(): BrowseFragment = BrowseFragment()
    }
}
