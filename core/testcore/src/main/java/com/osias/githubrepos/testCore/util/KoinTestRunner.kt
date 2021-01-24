package com.osias.githubrepos.testCore.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.osias.githubrepos.testCore.KoinTestApp

class KoinTestRunner: AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, KoinTestApp::class.java.name, context)
    }

}