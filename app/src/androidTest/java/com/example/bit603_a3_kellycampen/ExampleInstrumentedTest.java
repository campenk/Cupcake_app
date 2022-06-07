package com.example.bit603_a3_kellycampen;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.services.storage.ExperimentalTestStorage;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LogInActivity> mActivityTestRule = new ActivityScenarioRule<>(LogInActivity.class);

    //  Tests that the User Menu is displayed after login with admin details
    @Test
    public void LogIn_Admin() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.editTextTextLogin_username)).perform(click(), replaceText("admin"));
        onView(withId(R.id.editTextLogin_password)).perform(click(), replaceText("CookieManagement84"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin_submit)).perform(click());
        onView(withId(R.id.buttonManageUsers_viewUsers)).check(matches(isDisplayed()));
    }

    //  Tests that the Inventory Menu is displayed after login with admin details
    @Test
    public void LogIn_Valid_NotAdmin() {
        onView(withId(R.id.editTextTextLogin_username)).perform(click(), replaceText("sarah"));
        onView(withId(R.id.editTextLogin_password)).perform(click(), replaceText("password"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin_submit)).perform(click());
        onView(withId(R.id.buttonManageInventory_status)).check(matches(isDisplayed()));
    }

    // Tests that the error message is displayed after login with incorrect details
    @Test
    public void LogIn_Invalid() {
        onView(withId(R.id.editTextTextLogin_username)).perform(click(), replaceText("wrongusername"));
        onView(withId(R.id.editTextLogin_password)).perform(click(), replaceText("wrongpassword"), closeSoftKeyboard());
        onView(withId(R.id.buttonLogin_submit)).perform(click());
        onView(withId(R.id.buttonManageInventory_status)).check(matches(isDisplayed()));
    }



}
