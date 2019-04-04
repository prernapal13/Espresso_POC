package com.mytaxi.android_demo.utils;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

public class SimpleCountingIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        try {
            countingIdlingResource.increment();
        } catch (Exception ex) {
            Log.e("@IDLING-RESOURCE", "Exception occurs at increment" + ex.getMessage());
        }
    }

    public static void decrement() {
        try {
            countingIdlingResource.decrement();
        } catch (Exception ex) {
            Log.e("@IDLING-RESOURCE", "Exception occurs at decrement" + ex.getMessage());
        }
    }

    public static IdlingResource getIdlingResource() {
        return countingIdlingResource;
    }
}
