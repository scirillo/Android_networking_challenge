package com.example.android.networkconnect

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import org.hamcrest.core.IsNot.not

@RunWith(AndroidJUnit4::class)
class CharacterListTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testOnFetchActionRecyclerViewSize() {
        onView(withId(R.id.fetch_action)).perform(click())

        onView(withId(R.id.characters_recycler)).check(matches(hasChildCount(20)))
    }

    @Test
    fun testOnFetchActionFragmentIsDisplayed() {
        onView(withId(R.id.fetch_action)).perform(click())
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    }

    @Test
    fun testOnClearActionFragmentIsDisplayed() {
        onView(withId(R.id.clear_action)).perform(click())
        onView(withId(R.id.fragment_container)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testOnFetchActionRecyclerViewIsDisplayed() {
        onView(withId(R.id.fetch_action)).perform(click())
        onView(withId(R.id.characters_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun testOnFetchActionProgressIsDisplayed() {
        onView(withId(R.id.fetch_action)).perform(click())
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun testOnNextPageProgressIsDisplayed() {
        onView(withId(R.id.fetch_action)).perform(click())

        onView(withId(R.id.next)).perform(click())
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun testOnPrevPageProgressIsDisplayed() {
        onView(withId(R.id.fetch_action)).perform(click())

        onView(withId(R.id.prev)).perform(click())
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }
}
