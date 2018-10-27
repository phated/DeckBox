package com.r0adkll.deckbuilder.arch.data


import android.accounts.AccountAuthenticatorResponse
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.r0adkll.deckbuilder.BuildConfig
import com.r0adkll.deckbuilder.arch.data.database.DeckDatabase
import com.r0adkll.deckbuilder.arch.data.features.account.DefaultAccountRepository
import com.r0adkll.deckbuilder.arch.data.features.cards.DefaultCacheManager
import com.r0adkll.deckbuilder.arch.data.features.cards.cache.CardCache
import com.r0adkll.deckbuilder.arch.data.features.cards.cache.RoomCardCache
import com.r0adkll.deckbuilder.arch.data.features.cards.repository.DefaultCardRepository
import com.r0adkll.deckbuilder.arch.data.features.cards.repository.source.CombinedSearchDataSource
import com.r0adkll.deckbuilder.arch.data.features.cards.repository.source.SearchDataSource
import com.r0adkll.deckbuilder.arch.data.features.community.cache.CommunityCache
import com.r0adkll.deckbuilder.arch.data.features.community.cache.FirestoreCommunityCache
import com.r0adkll.deckbuilder.arch.data.features.community.repository.DefaultCommunityRepository
import com.r0adkll.deckbuilder.arch.data.features.decks.cache.DeckCache
import com.r0adkll.deckbuilder.arch.data.features.decks.cache.SwitchingDeckCache
import com.r0adkll.deckbuilder.arch.data.features.decks.repository.DefaultDeckRepository
import com.r0adkll.deckbuilder.arch.data.features.editing.cache.RoomSessionCache
import com.r0adkll.deckbuilder.arch.data.features.editing.cache.SessionCache
import com.r0adkll.deckbuilder.arch.data.features.editing.repository.DefaultEditRepository
import com.r0adkll.deckbuilder.arch.data.features.expansions.CachingExpansionDataSource
import com.r0adkll.deckbuilder.arch.data.features.expansions.ExpansionDataSource
import com.r0adkll.deckbuilder.arch.data.features.exporter.ptcgo.DefaultPtcgoExporter
import com.r0adkll.deckbuilder.arch.data.features.exporter.tournament.DefaultTournamentExporter
import com.r0adkll.deckbuilder.arch.data.features.importer.repository.DefaultImporter
import com.r0adkll.deckbuilder.arch.data.features.missingcard.repository.DefaultMissingCardRepository
import com.r0adkll.deckbuilder.arch.data.features.testing.DefaultDeckTester
import com.r0adkll.deckbuilder.arch.data.features.validation.model.BasicRule
import com.r0adkll.deckbuilder.arch.data.features.validation.model.DuplicateRule
import com.r0adkll.deckbuilder.arch.data.features.validation.model.PrismStarRule
import com.r0adkll.deckbuilder.arch.data.features.validation.model.SizeRule
import com.r0adkll.deckbuilder.arch.data.features.validation.repository.DefaultDeckValidator
import com.r0adkll.deckbuilder.arch.data.remote.plugin.CacheInvalidatePlugin
import com.r0adkll.deckbuilder.arch.data.remote.plugin.RemotePlugin
import com.r0adkll.deckbuilder.arch.domain.features.account.AccountRepository
import com.r0adkll.deckbuilder.arch.domain.features.cards.CacheManager
import com.r0adkll.deckbuilder.arch.domain.features.cards.repository.CardRepository
import com.r0adkll.deckbuilder.arch.domain.features.community.repository.CommunityRepository
import com.r0adkll.deckbuilder.arch.domain.features.decks.repository.DeckRepository
import com.r0adkll.deckbuilder.arch.domain.features.editing.repository.EditRepository
import com.r0adkll.deckbuilder.arch.domain.features.exporter.ptcgo.PtcgoExporter
import com.r0adkll.deckbuilder.arch.domain.features.exporter.tournament.TournamentExporter
import com.r0adkll.deckbuilder.arch.domain.features.importer.repository.Importer
import com.r0adkll.deckbuilder.arch.domain.features.missingcard.repository.MissingCardRepository
import com.r0adkll.deckbuilder.arch.domain.features.testing.DeckTester
import com.r0adkll.deckbuilder.arch.domain.features.validation.model.Rule
import com.r0adkll.deckbuilder.arch.domain.features.validation.repository.DeckValidator
import com.r0adkll.deckbuilder.internal.di.scopes.AppScope
import com.r0adkll.deckbuilder.util.Schedulers
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import io.pokemontcg.Config
import io.pokemontcg.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import java.util.concurrent.Executors


@Module
class DataModule {

    @Provides @AppScope @IntoSet
    fun provideCacheInvalidatePlugin(plugin: CacheInvalidatePlugin): RemotePlugin = plugin


    @Provides @AppScope
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }


    @Provides @AppScope
    fun provideRxSharedPreferences(sharedPreferences: SharedPreferences) : RxSharedPreferences {
        return RxSharedPreferences.create(sharedPreferences)
    }


    @Provides @AppScope
    fun provideSchedulers(): Schedulers = Schedulers(
            AndroidSchedulers.mainThread(),
            io.reactivex.schedulers.Schedulers.io(),
            io.reactivex.schedulers.Schedulers.computation(),
            io.reactivex.schedulers.Schedulers.io(),
            Executors.newSingleThreadExecutor(),
            Executors.newSingleThreadExecutor()
    )


    @Provides @AppScope
    fun providePokemonApiConfig(): Config {
        val level = if (BuildConfig.DEBUG) BODY else NONE
        return Config(logLevel = level)
    }


    @Provides @AppScope
    fun providePokemonApi(config: Config): Pokemon = Pokemon(config)


    /**
     * Change Log
     * ---
     * 1. Initial Version
     */
    @Provides @AppScope
    fun provideRoomDatabase(context: Context): DeckDatabase {
        return Room.databaseBuilder(context, DeckDatabase::class.java, "Room_" + BuildConfig.DATABASE_NAME)
                .build()
    }


    /*
     * Caching
     */

    @Provides @AppScope
    fun provideCardCache(cache: RoomCardCache): CardCache = cache


    @Provides @AppScope
    fun provideDeckCache(cache: SwitchingDeckCache): DeckCache = cache


    @Provides @AppScope
    fun provideCommunityCache(cache: FirestoreCommunityCache): CommunityCache = cache


    @Provides @AppScope
    fun provideSessionCache(cache: RoomSessionCache): SessionCache = cache


    @Provides @AppScope
    fun provideCacheManager(manager: DefaultCacheManager): CacheManager = manager


    /*
     * Data Sources
     */

    @Provides @AppScope
    fun provideExpansionDataSource(dataSource: CachingExpansionDataSource): ExpansionDataSource = dataSource


    @Provides @AppScope
    fun provideSearchDataSource(dataSource: CombinedSearchDataSource): SearchDataSource = dataSource


    /*
     * Repositories
     */

    @Provides @AppScope
    fun provideAccountRepository(repository: DefaultAccountRepository): AccountRepository = repository


    @Provides @AppScope
    fun provideDecksRepository(repository: DefaultDeckRepository): DeckRepository = repository


    @Provides @AppScope
    fun provideCommunityRepository(repository: DefaultCommunityRepository): CommunityRepository = repository


    @Provides @AppScope
    fun provideEditRepository(repository: DefaultEditRepository): EditRepository = repository


    @Provides @AppScope
    fun provideCardRepository(repository: DefaultCardRepository): CardRepository = repository


    @Provides @AppScope
    fun provideMissingCardRepository(repository: DefaultMissingCardRepository): MissingCardRepository = repository


    @Provides @AppScope
    fun provideDeckValidator(validator: DefaultDeckValidator): DeckValidator = validator


    @Provides @AppScope
    fun provideImporter(importer: DefaultImporter): Importer = importer


    @Provides @AppScope
    fun providePtcgoExporter(): PtcgoExporter = DefaultPtcgoExporter()


    @Provides @AppScope
    fun provideTournamentExporter(exporter: DefaultTournamentExporter): TournamentExporter = exporter

    /*
     * Deck Validation Rules
     */

    @Provides @AppScope
    @ElementsIntoSet
    fun provideDefaultRuleSet() : Set<Rule> {
        return setOf(
                SizeRule(),
                DuplicateRule(),
                BasicRule(),
                PrismStarRule()
        )
    }


    @Provides @AppScope
    fun provideDeckTester(tester: DefaultDeckTester): DeckTester = tester

}