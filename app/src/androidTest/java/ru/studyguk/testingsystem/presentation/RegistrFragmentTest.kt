package ru.studyguk.testingsystem.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import ru.studyguk.testingsystem.R

@RunWith(AndroidJUnit4::class)
class RegistrFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shortPassword_causesError() {
        onView(withId(R.id.textViewRegistrLink))
            .perform(ViewActions.click())
        onView(withId(R.id.editTextTextEmailAddressRegistr))
            .perform(ViewActions.typeText("ppp@gmail.com"))
        onView(withId(R.id.editTextTextPasswordRegistr))
            .perform(ViewActions.typeText("11111"))
        onView(withId(R.id.editTextTextRepeatPassword))
            .perform(ViewActions.typeText("11111"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonLoginRegistr))
            .perform(ViewActions.click())
        onView(withId(R.id.editTextTextPasswordRegistr))
            .check(ViewAssertions.matches(hasErrorText("Пароль должен содержать хотя бы 6 символов")))
    }

    @Test
    fun passwordsDoNotMatch_causesError() {
        onView(withId(R.id.textViewRegistrLink))
            .perform(ViewActions.click())
        onView(withId(R.id.editTextTextEmailAddressRegistr))
            .perform(ViewActions.typeText("ppp@gmail.com"))
        onView(withId(R.id.editTextTextPasswordRegistr))
            .perform(ViewActions.typeText("111111"))
        onView(withId(R.id.editTextTextRepeatPassword))
            .perform(ViewActions.typeText("111112"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonLoginRegistr))
            .perform(ViewActions.click())
        onView(withId(R.id.editTextTextRepeatPassword))
            .check(ViewAssertions.matches(hasErrorText("Пароли не совпадают, проверьте введённые данные")))
    }
}