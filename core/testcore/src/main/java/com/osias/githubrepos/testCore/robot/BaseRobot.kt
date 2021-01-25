package com.osias.githubrepos.testCore.robot

import android.app.Instrumentation
import android.content.IntentFilter
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.osias.githubrepos.testCore.matchers.CustomMatchers
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

open class BaseRobot {

    protected fun checkNumberOfItensInList(recyclerId: Int, count: Int): ViewInteraction =
        getViewWithId(recyclerId).check(matches(CustomMatchers.withItemCount(count)))

    protected fun checkTextInPosition(recyclerId: Int, position: Int, text: String): ViewInteraction = getViewWithId(recyclerId)
        .check(
            matches(
                    CustomMatchers.atPosition(
                            position,
                            withText(text)
                    )
            )
        )

    protected fun getViewWithId(id: Int) = Espresso.onView(ViewMatchers.withId(id))

}