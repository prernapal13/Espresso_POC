package com.mytaxi.android_demo.base;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.SimpleCountingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.IdlingRegistry.getInstance;

public class BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(
            MainActivity.class, false, false);

    @Rule
    public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    private MainActivity mainActivity = null;

    protected static String username = "crazydog335";
    protected static String password = "venture";
    protected static String searchString = "sa";
    protected static String driverName = "Sarah Scott";

    @Before
    public void setUp() {
        mainActivityTestRule.launchActivity(null);
        mainActivity = mainActivityTestRule.getActivity();
        getInstance().register(SimpleCountingIdlingResource.getIdlingResource());
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

    @After
    public void tearDown() {
        getInstance().unregister(SimpleCountingIdlingResource.getIdlingResource());
        resetApp();
    }

}
