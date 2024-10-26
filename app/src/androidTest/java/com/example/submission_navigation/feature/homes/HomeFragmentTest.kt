package com.example.submission_navigation.feature.homes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.submission_navigation.R
import com.example.submission_navigation.feature.main.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testSearchView() {
        // Klik searchView
        onView(withId(R.id.searchView)).perform(click())

        // Masukkan teks ke dalam searchView
        onView(withId(R.id.searchView)).perform(typeText("user123"), pressImeActionButton())

        // Memeriksa apakah teks yang dimasukkan muncul dengan benar di searchView
        onView(withId(R.id.searchView)).check(matches(withText("user123")))

        // Memeriksa apakah hasil pencarian diaktifkan dengan benar
        onView(withId(R.id.recyclerHome)).check(matches(isDisplayed()))
    }
}
