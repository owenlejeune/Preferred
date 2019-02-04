package com.owenlejeune.preffered;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PreferredTest {

    private final String prefsName = "testPrefs";
    private final String defaultString = "";
    private final int defaultInt = 0;
    private final boolean defaultBool = false;
    private final long defaultLong = 0L;
    private final float defaultFloat = 0f;
    private final Set<String> defaultStringSet = new HashSet<>();
    private final String key = "key";

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getContext();

        new Preferred.Builder().setPrefsName(prefsName).setContext(appContext).setUseDefaultSharedPreferences(false).build();
    }

    @After
    public void teardown() {
        Preferred.removeAllPrefsStatic();
    }

    @Test
    public void testWhenPutStringValueThenValueIsStored() {
        final String value = "someString";
        Preferred.getInstance().putString(key, value);

        assertEquals(Preferred.getInstance().getString(key), value);
    }

    @Test
    public void testWhenGetStringValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getString(key), defaultString);
    }

    @Test
    public void testWhenPutIntValueThenValueIsStored() {
        final int value = 150;
        Preferred.getInstance().putInt(key, value);

        assertEquals(Preferred.getInstance().getInt(key), value);
    }

    @Test
    public void testWhenGetIntValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getInt(key), defaultInt);
    }

    @Test
    public void testWhenPutBooleanValueThenValueIsStored() {
        final boolean value = true;
        Preferred.getInstance().putBoolean(key, value);

        assertEquals(Preferred.getInstance().getBoolean(key), value);
    }

    @Test
    public void testWhenGetBooleanValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getBoolean(key), defaultBool);
    }

    @Test
    public void testWhenPutLongValueThenValueIsStored() {
        final long value = 15L;
        Preferred.getInstance().putLong(key, value);

        assertEquals(Preferred.getInstance().getLong(key), value);
    }

    @Test
    public void testWhenGetLongValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getLong(key), defaultLong);
    }

    @Test
    public void testWhenPutFloatValueThenValueIsStored() {
        final float value = 15F;
        Preferred.getInstance().putFloat(key, value);

        assertEquals(Preferred.getInstance().getFloat(key), value, 0);
    }

    @Test
    public void testWhenGetFloatValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getFloat(key), defaultFloat, 0);
    }

    @Test
    public void testWhenPutStringSetValueThenValueIsStored() {
        final Set<String> value = new HashSet<>();
        value.add("String 1");
        value.add("String 2");
        Preferred.getInstance().putStringSet(key, value);

        assertEquals(Preferred.getInstance().getStringSet(key), value);
    }

    @Test
    public void testWhenGetStringSetValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getStringSet(key), defaultStringSet);
    }

}
