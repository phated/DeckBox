package com.r0adkll.deckbuilder.arch.ui.features.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.ftinc.kit.extensions.dp
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.r0adkll.deckbuilder.R
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.PokemonCard
import com.r0adkll.deckbuilder.arch.ui.widgets.PokemonCardView
import com.r0adkll.deckbuilder.util.bindOptionalView
import com.r0adkll.deckbuilder.util.bindView
import timber.log.Timber

class PokemonCardViewHolder(
    itemView: View,
    val displayWhenOne: Boolean = false,
    val startDragImmediately: Boolean = false,
    val removeCardClicks: Relay<PokemonCard> = PublishRelay.create(),
    val addCardClicks: Relay<List<PokemonCard>> = PublishRelay.create()
) : RecyclerView.ViewHolder(itemView) {

    val cardView: PokemonCardView by bindView(R.id.card)
    private val actionLayout: LinearLayout? by bindOptionalView(R.id.action_layout)
    private val actionRemove: ImageView? by bindOptionalView(R.id.action_remove)
    private val actionAdd: ImageView? by bindOptionalView(R.id.action_add)
    val collectionCounter: TextView? by bindOptionalView(R.id.count)

    fun bind(
        card: PokemonCard,
        count: Int,
        evolution: PokemonCardView.Evolution = PokemonCardView.Evolution.NONE,
        isEditMode: Boolean = false,
        collectionCount: Int = 0,
        isCollectionMode: Boolean = false
    ) {
        cardView.displayCountWhenOne = displayWhenOne
        cardView.card = card
        cardView.count = count
        cardView.startDragImmediately = startDragImmediately
        cardView.evolution = evolution

        if (isCollectionMode) {
            collectionCounter?.isVisible = true
            collectionCounter?.text = "$collectionCount"
            cardView.imageAlpha = if (collectionCount >= count) 255 else 102
            Timber.v("Binding(id=${card.id}, collection=$collectionCount, count=$count, resultAlpha=${cardView.alpha}")
        } else {
            collectionCounter?.isGone = true
            cardView.alpha = 1f
        }

        actionLayout?.isVisible = (isEditMode && !displayWhenOne) ||
            isEditMode && ((displayWhenOne && count > 0) || count > 1)
        actionRemove?.setOnClickListener { removeCardClicks.accept(card) }
        actionAdd?.setOnClickListener { addCardClicks.accept(listOf(card)) }

        if (displayWhenOne && count > 0) {
            cardView.elevation = dp(8)
        } else {
            cardView.elevation = dp(4)
        }
    }

    companion object {

        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup,
            displayWhenOne: Boolean = false,
            startDragImmediately: Boolean = false,
            removeCardClicks: Relay<PokemonCard> = PublishRelay.create(),
            addCardClicks: Relay<List<PokemonCard>> = PublishRelay.create()
        ): PokemonCardViewHolder {
            val layout = if (startDragImmediately) R.layout.item_pokemon_card else R.layout.item_pokemon_card_collection
            val view = inflater.inflate(layout, parent, false)
            return PokemonCardViewHolder(view, displayWhenOne, startDragImmediately,
                removeCardClicks = removeCardClicks, addCardClicks = addCardClicks)
        }

        fun createHorizontal(
            inflater: LayoutInflater,
            parent: ViewGroup,
            displayWhenOne: Boolean = false,
            startDragImmediately: Boolean = false
        ): PokemonCardViewHolder {
            val view = inflater.inflate(R.layout.item_pokemon_card_horizontal, parent, false)
            return PokemonCardViewHolder(view, displayWhenOne, startDragImmediately)
        }
    }
}
