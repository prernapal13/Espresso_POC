package com.mytaxi.android_demo;

import android.content.Context;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.SimpleCountingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.IdlingRegistry.getInstance;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.close;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchDriverTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(
            MainActivity.class, false, false);

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    private MainActivity mainActivity = null;

    private static String username = "crazydog335";
    private static String password = "venture";
    private static String searchString = "sa";
    private static String driverName = "Sarah Scott";

    @Before
    public void setUp() {
        mainActivityTestRule.launchActivity(null);
        mainActivity = mainActivityTestRule.getActivity();
        getInstance().register(SimpleCountingIdlingResource.getIdlingResource());
    }

    @Test
    public void testSearchAndCallDriver() {

        Log.i("@LOGIN", "Login into myTaxi App, & verifying successful login.");
        onView(withId(R.id.edt_username)).perform(typeText(username));
        onView(withId(R.id.edt_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withId(R.id.nav_username)).check(matches(withText(username)));
        onView(withId(R.id.drawer_layout)).perform(close());

        Log.i("@SEARCH DRIVER", "Search driver by " + searchString + ", Select the 2nd result.");
        onView(withId(R.id.textSearch)).perform(typeText("sa"));
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mainActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed())).perform(click());

        Log.i("#DRIVER VERIFICATION",
                "Verify selected driver's (" + driverName + ") name on Driver Profile Page.");
        onView(withId(R.id.textViewDriverName)).check(matches(withText(driverName)));

        Log.i("#CALLING DRIVER", "Click on call button on Driver Profile Page.");
        onView(withId(R.id.fab)).check(matches(isClickable())).perform(ViewActions.click());

    }

    @After
    public void tearDown() {
        getInstance().unregister(SimpleCountingIdlingResource.getIdlingResource());
        resetApp();
    }

    private void resetApp() {
        Log.e("@RE-SETTING APP: ", "resetting app.");
        try {
            File root = getTargetContext().getFilesDir().getParentFile();
            String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
            for (String fileName : sharedPreferencesFileNames) {
                getTargetContext().getSharedPreferences(fileName.replace(".xml", ""),
                        Context.MODE_PRIVATE).edit().clear().commit();
            }
        } catch (Exception ex) {
            Log.e("@RE-SETTING APP: ", "Exception occurs at resetApp - " + ex.getMessage());
        }
    }
}
