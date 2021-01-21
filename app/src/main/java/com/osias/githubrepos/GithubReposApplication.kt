package com.osias.githubrepos

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class GithubReposApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubReposApplication)
        }

    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}