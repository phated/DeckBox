package com.r0adkll.deckbuilder.tools

import com.r0adkll.deckbuilder.arch.domain.features.cards.model.Expansion
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.PokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.StackedPokemonCard
import io.pokemontcg.model.SubType
import io.pokemontcg.model.SuperType


object ModelUtils {

    fun createPokemonCard(): PokemonCard {
        return PokemonCard("", "", null, "", "", null, SuperType.POKEMON, SubType.BASIC, null, null,
                null, ",", "", "", "", Expansion("", "", "", "", 0, false, false, "", ""), null, null,
                null, null)
    }

    fun createStackedPokemonCard(count: Int = 1): StackedPokemonCard {
        return StackedPokemonCard(createPokemonCard(), count)
    }
}