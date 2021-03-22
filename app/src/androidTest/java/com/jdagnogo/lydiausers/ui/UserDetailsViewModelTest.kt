package com.jdagnogo.lydiausers.ui

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.UserDaoTest.Companion.FAKE_NAME
import com.jdagnogo.lydiausers.repository.UserDaoTest.Companion.FAKE_NAT
import com.jdagnogo.lydiausers.repository.UserDaoTest.Companion.FAKE_PHONE
import com.jdagnogo.lydiausers.ui.UserDetailsFragment.Companion.USER_KEY
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher


@RunWith(AndroidJUnit4::class)
class UserDetailsViewModelTest {
    @Test
    fun testUserDetailsFragmentDisplayTheCorrectInfo() {
        val fragmentArgs = bundleOf(
            USER_KEY to
                    User(
                        name = FAKE_NAME,
                        nationality = FAKE_NAT,
                        phone = FAKE_PHONE
                    )
        )

        launchFragmentInContainer<UserDetailsFragment>(fragmentArgs)

        onView(withId(R.id.user_name)).check(matches(withText(FAKE_NAME)))
        onView(withId(R.id.user_phone)).check(matches(withText(FAKE_PHONE)))
        onView(withTagValue(equalTo(R.drawable.france))).check(matches(isDisplayed()))
    }

    @Test
    fun testUserWithBrAsNationalityShouldDisplayBrazilFlag() {
        val fragmentArgs = bundleOf(
            USER_KEY to User(nationality = "BR")
        )

        launchFragmentInContainer<UserDetailsFragment>(fragmentArgs)

        onView(withTagValue(equalTo(R.drawable.brazil))).check(matches(isDisplayed()))
    }

    @Test
    fun testUserWithMaleAsGenderShouldDisplayMaleIcon() {
        val fragmentArgs = bundleOf(
            USER_KEY to User(gender = "male")
        )

        launchFragmentInContainer<UserDetailsFragment>(fragmentArgs)

        onView(withTagValue(equalTo(R.drawable.male))).check(matches(isDisplayed()))
    }

}