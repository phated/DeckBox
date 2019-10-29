package com.r0adkll.deckbuilder.arch.data.features.cards.repository.source

import com.r0adkll.deckbuilder.arch.data.AppPreferences
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.Filter
import com.r0adkll.deckbuilder.arch.domain.features.cards.model.PokemonCard
import com.r0adkll.deckbuilder.arch.domain.features.remote.Remote
import com.r0adkll.deckbuilder.util.extensions.combineLatest
import com.r0adkll.deckbuilder.util.helper.Connectivity
import io.pokemontcg.model.SuperType
import io.reactivex.Observable
import io.reactivex.functions.Function
import timber.log.Timber

class DefaultCardDataSource(
        val preferences: AppPreferences,
        val diskSource: CardDataSource,
        val networkSource: CardDataSource,
        val previewSource: CardDataSource,
        val connectivity: Connectivity,
        val remote: Remote
) : CardDataSource {

    override fun search(type: SuperType?, query: String, filter: Filter?): Observable<List<PokemonCard>> {
        val filterExpansionCodes = filter?.expansions?.map { it.code }
        val forceDiskSearch = filterExpansionCodes
                ?.let { it.isNotEmpty() && preferences.offlineExpansions.get().containsAll(it) }
                ?: false
        val previewExpansionVersion = remote.previewExpansionVersion?.expansionCode
        val previewExpansion = filter?.expansions?.find { it.code == previewExpansionVersion && it.isPreview }
        return if (connectivity.isConnected() && !forceDiskSearch) {
            if (previewExpansion != null || filter?.expansions.isNullOrEmpty()) {
                // if the user has selected to search the preview expansion or the user doesn't select any expansions
                // search both local preview cache and normal network + disk fallback
                val previewFilter = filter?.copy(includePreview = true) ?: Filter(includePreview = true)
                val previewSearch = diskSource.search(type, query, previewFilter)
                        .defaultIfEmpty(emptyList())
                        .onErrorReturnItem(emptyList())

                val networkSearch = networkSource.search(type, query, filter)
                        .onErrorResumeNext(Function {
                            Timber.e(it, "Error searching for cards")
                            diskSource.search(type, query, filter)
                        })

                previewSearch combineLatest networkSearch
            } else {
                networkSource.search(type, query, filter)
                        .onErrorResumeNext(Function {
                            Timber.e(it, "Error searching for cards")
                            diskSource.search(type, query, filter)
                        })
            }
        } else {
            if (previewExpansion != null || filter?.expansions.isNullOrEmpty()) {
                val previewFilter = filter?.copy(includePreview = true) ?: Filter(includePreview = true)
                val previewSearch = diskSource.search(type, query, previewFilter)
                        .defaultIfEmpty(emptyList())
                        .onErrorReturnItem(emptyList())

                diskSource.search(type, query, filter) combineLatest previewSearch
            } else {
                diskSource.search(type, query, filter)
            }
        }
    }

    override fun find(ids: List<String>): Observable<List<PokemonCard>> {
        return if (connectivity.isConnected()) {
            diskSource.find(ids)
                    .flatMap { diskCards ->
                        val missingIds = ids.filter { id -> diskCards.none { card -> card.id == id } }
                        if (missingIds.isNotEmpty()) {
                            networkSource.find(missingIds)
                                    .map { it.plus(diskCards) }
                        } else {
                            Observable.just(diskCards)
                        }
                    }
        } else {
            diskSource.find(ids)
        }
    }
}
