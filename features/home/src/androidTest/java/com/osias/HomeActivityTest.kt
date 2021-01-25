package com.osias

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.osias.di.mockErrorModule
import com.osias.di.mockModule
import com.osias.githubrepos.home.view.HomeActivity
import com.osias.githubrepos.testCore.base.BaseTest
import com.osias.githubrepos.testCore.rule.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest: BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)
        .putExtra(HomeActivity.TEST_EXTRA, true)
    private var scenario: ActivityScenario<HomeActivity>? = null

    @After
    fun tearDown() {
        scenario?.close()
        unloadKoinModules(mockModule)
        unloadKoinModules(mockErrorModule)
    }

    @Test
    fun testSuccessfulWith9Items() {
        loadKoinModules(mockModule)
        ActivityScenario.launch<HomeActivity>(intent)
        home {
            checkNineItemsInList()
            checkMockTitleInThirdPosition()
            checkLoginInSeconPosition()
            checkStarCountInFourthPosition()
            checkForkCountInFifthPosition()
        }
    }

    @Test
    fun testError() {
        loadKoinModules(mockErrorModule)
        ActivityScenario.launch<HomeActivity>(intent)
        home {
            checkNoItemsInList()
        }
    }

}