package com.comp3350_group10.bookstore;

import android.app.Activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import java.util.concurrent.atomic.AtomicReference;

public class GetActivity {
    public static <T extends Activity> T getActivity(ActivityScenarioRule<T> activityScenarioRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityScenarioRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }
}
