package com.owenlejeune.preffered

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import java.util.HashSet

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4ClassRunner::class)
class PreferredTest {

    private val prefsName = "testPrefs"
    private val defaultString = ""
    private val defaultInt = 0
    private val defaultBool = false
    private val defaultLong = 0L
    private val defaultFloat = 0f
    private val defaultStringSet = HashSet<String>()
    private val key = "key"

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().context

        Preferred.Builder()
                .setPrefsName(prefsName)
                .setContext(appContext)
                .setUseDefaultSharedPreferences(false)
                .build()
    }

    @After
    fun teardown() {
        Preferred.removeAllPrefsStatic()
    }

    @Test
    fun testWhenPutStringValueThenValueIsStored() {
        val value = "someString"
        Preferred.getInstance().putString(key, value)

        assertEquals(Preferred.getInstance().getString(key), value)
    }

    @Test
    fun testWhenGetStringValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getString(key), defaultString)
    }

    @Test
    fun testWhenPutIntValueThenValueIsStored() {
        val value = 150
        Preferred.getInstance().putInt(key, value)

        assertEquals(Preferred.getInstance().getInt(key).toLong(), value.toLong())
    }

    @Test
    fun testWhenGetIntValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getInt(key).toLong(), defaultInt.toLong())
    }

    @Test
    fun testWhenPutBooleanValueThenValueIsStored() {
        val value = true
        Preferred.getInstance().putBoolean(key, value)

        assertEquals(Preferred.getInstance().getBoolean(key), value)
    }

    @Test
    fun testWhenGetBooleanValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getBoolean(key), defaultBool)
    }

    @Test
    fun testWhenPutLongValueThenValueIsStored() {
        val value = 15L
        Preferred.getInstance().putLong(key, value)

        assertEquals(Preferred.getInstance().getLong(key), value)
    }

    @Test
    fun testWhenGetLongValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getLong(key), defaultLong)
    }

    @Test
    fun testWhenPutFloatValueThenValueIsStored() {
        val value = 15f
        Preferred.getInstance().putFloat(key, value)

        assertEquals(Preferred.getInstance().getFloat(key), value, 0f)
    }

    @Test
    fun testWhenGetFloatValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getFloat(key), defaultFloat, 0f)
    }

    @Test
    fun testWhenPutStringSetValueThenValueIsStored() {
        val value = HashSet<String>()
        value.add("String 1")
        value.add("String 2")
        Preferred.getInstance().putStringSet(key, value)

        assertEquals(Preferred.getInstance().getStringSet(key), value)
    }

    @Test
    fun testWhenGetStringSetValueThatDoesNotExistThenDefaultValueIsReturned() {
        assertEquals(Preferred.getInstance().getStringSet(key), defaultStringSet)
    }

    @Test
    fun testWhenGetAllPrefsThenAllPrefsAreReturned() {
        Preferred.getInstance().putString(key+"1", "someString")
        Preferred.getInstance().putInt(key+"2", 150)
        Preferred.getInstance().putBoolean(key+"3", true)
        Preferred.getInstance().putLong(key+"4", 15L)

        val map: MutableMap<String, Any> = HashMap()
        map[key+"1"] = "someString"
        map[key+"2"] = 150
        map[key+"3"] = true
        map[key+"4"] = 15L

        assertEquals(Preferred.getInstance().getAllPrefs(), map)
    }

    @Test
    fun testWhenPreferredContainsPreferenceThenContainsPreferenceReturnsTrue() {
        val value = "someString"
        Preferred.getInstance().putString(key, value)

        assertEquals(Preferred.getInstance().containsPreference(key), true)
    }

    @Test
    fun testWhenPreferredDoesNotContainsPreferenceThenContainsPreferenceReturnsFalse() {
        assertEquals(Preferred.getInstance().containsPreference(key), false)
    }

}
