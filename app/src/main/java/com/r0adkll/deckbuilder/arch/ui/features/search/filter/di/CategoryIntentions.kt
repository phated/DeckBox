package com.r0adkll.deckbuilder.arch.ui.features.search.filter.di


import com.jakewharton.rxrelay2.Relay
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.Filter
import io.pokemontcg.model.SuperType
import io.reactivex.Observable


interface CategoryIntentions {

    fun filterChanges(): Relay<Filter>
    fun categoryChange(): Observable<SuperType>
}