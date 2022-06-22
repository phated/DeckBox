@file:Suppress("DEPRECATION")

package com.r0adkll.deckbuilder

import android.app.Application
import com.bumptech.glide.request.target.ViewTarget
import com.ftinc.kit.app.AppDelegate
import com.ftinc.kit.widget.EmptyView
import com.google.firebase.FirebaseApp
import com.jakewharton.threetenabp.AndroidThreeTen
import com.r0adkll.deckbuilder.internal.analytics.Analytics
import com.r0adkll.deckbuilder.internal.analytics.LoggingAnalyticInterface
import com.r0adkll.deckbuilder.internal.di.AppComponent
import com.r0adkll.deckbuilder.internal.di.AppModule
import com.r0adkll.deckbuilder.internal.di.DaggerAppComponent
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class DeckApp : Application() {

    companion object {
        @JvmStatic lateinit var component: AppComponent
    }

    @Inject lateinit var delegates: Set<@JvmSuppressWildcards AppDelegate>

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        installDagger().inject(this)
        installAnalytics()
        installDelegates()
        installFirestore()
        installRxErrorHandler()

        // Setup Glide to allow for custom tag id's so we can set tags to images for our own purpose
        ViewTarget.setTagId(R.id.glide_tag_id)

        @Suppress("MagicNumber")
        EmptyView.defaultMessageTextSizeSp = 20
    }

    private fun installAnalytics() {
        Analytics.add(LoggingAnalyticInterface())
//        Analytics.userProperty(UserProperty.FLAVOR, BuildConfig.FLAVOR)
    }

    private fun installDelegates() {
        delegates.forEach { it.onCreate(this) }
    }

    private fun installFirestore() {
        FirebaseApp.initializeApp(this)
    }

    private fun installRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            var ex: Throwable? = e
            if (e is UndeliverableException) {
                ex = e.cause
            }
            if (e is IOException || e is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
            }
            if (e is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
            }
            if (e is NullPointerException || e is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), e)
            }
            if (e is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    ?.uncaughtException(Thread.currentThread(), e)
            }
            Timber.w(ex, "Undeliverable exception received, not sure what to do")
        }
    }

    @Suppress("NON_FINAL_MEMBER_IN_FINAL_CLASS")
    open fun installDagger(): AppComponent {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        return component
    }
}
