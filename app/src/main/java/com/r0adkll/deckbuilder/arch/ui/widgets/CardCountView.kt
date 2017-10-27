package com.r0adkll.deckbuilder.arch.ui.widgets

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.ftinc.kit.kotlin.extensions.color
import com.ftinc.kit.kotlin.extensions.dipToPx
import com.ftinc.kit.kotlin.extensions.dpToPx
import com.ftinc.kit.kotlin.extensions.spToPx
import com.r0adkll.deckbuilder.R
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.PokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.StackedPokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.decks.model.Deck
import io.pokemontcg.model.SuperType


class CardCountView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val pokemonCountView: TextView
    private val trainerCountView: TextView
    private val energyCountView: TextView


    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER

        pokemonCountView = createCountView(R.drawable.ic_pokeball)
        trainerCountView = createCountView(R.drawable.ic_wrench)
        energyCountView = createCountView(R.drawable.ic_flash)

        addView(pokemonCountView)
        addView(trainerCountView)
        addView(energyCountView)
    }


    fun count(deck: Deck) {
        pokemonCountView.text = "${deck.pokemonCount}"
        trainerCountView.text = "${deck.trainerCount}"
        energyCountView.text = "${deck.energyCount}"
    }


    fun count(cards: List<PokemonCard>) {
        pokemonCountView.text = cards.count { it.supertype == SuperType.POKEMON }.toString()
        trainerCountView.text = cards.count { it.supertype == SuperType.TRAINER }.toString()
        energyCountView.text = cards.count { it.supertype == SuperType.ENERGY }.toString()
    }


    fun count(superType: SuperType, cards: List<PokemonCard>) {
        val count = cards.count { it.supertype == superType }.toString()
        when(superType) {
            SuperType.POKEMON -> pokemonCountView.text = count
            SuperType.TRAINER -> trainerCountView.text = count
            SuperType.ENERGY -> energyCountView.text = count
        }
    }


    fun countStacks(superType: SuperType, cards: List<StackedPokemonCard>) {
        var count = 0
        cards.filter { it.card.supertype == superType }
                .forEach { count += it.count }

        when(superType) {
            SuperType.POKEMON -> pokemonCountView.text = count.toString()
            SuperType.TRAINER -> trainerCountView.text = count.toString()
            SuperType.ENERGY -> energyCountView.text = count.toString()
        }
    }


    private fun createCountView(@DrawableRes icon: Int): TextView {
        val view = TextView(context)
        view.gravity = Gravity.CENTER
        view.text = "26"
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        view.setTextColor(color(R.color.black56))
        view.typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        view.compoundDrawablePadding = dipToPx(4f)
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, icon, 0)
        return view
    }


    override fun generateDefaultLayoutParams(): LayoutParams {
        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lp.marginStart = dipToPx(4f)
        lp.marginEnd = dipToPx(4f)
        return lp
    }
}