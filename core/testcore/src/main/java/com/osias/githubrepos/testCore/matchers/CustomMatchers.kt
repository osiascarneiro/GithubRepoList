package com.osias.githubrepos.testCore.matchers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

object CustomMatchers {
    //Credits to https://stackoverflow.com/a/34795431
    fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return checkDescendant(viewHolder.itemView, itemMatcher)
            }
        }
    }

    private fun checkDescendant(view: View, itemMatcher: Matcher<View?>): Boolean {
        val result = itemMatcher.matches(view)
        val hasChild = view is ViewGroup
        if(result) return true
        if(!result && hasChild) {
            for (i in 0 until (view as ViewGroup).childCount) {
                val resultChild = checkDescendant(view.getChildAt(i), itemMatcher)
                if(resultChild) return true
            }
            return false
        }
        else return false
    }

    fun withItemCount(count: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count: $count")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                return item?.adapter?.itemCount == count
            }
        }
    }

}