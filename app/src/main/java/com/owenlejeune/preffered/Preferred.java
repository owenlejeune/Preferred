package com.owenlejeune.preffered;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Wrapper library for handling storage and retrieval of Shared Preferences in an easy way
 */
@SuppressWarnings("unused")
public class Preferred {

    private static final String DEFAULT_SUFFIX = "_preferences";
    private static Preferred INSTANCE;
//    private static SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences;

    public static Preferred getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("Instance has not been initialized.  Please call Builder.build() to initialize");
//            synchronized (Preferred.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Preferred();
//                }
//            }
        }
        return INSTANCE;
    }

    private Preferred() {
        // nothing to init
    }

    private Preferred(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    private static void init(Context context, String key) {
        INSTANCE = new Preferred(context, key);
//        sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

    }

    /**
     * Returns the underlying {@link SharedPreferences} instance
     *
     * @return an instance of {@link SharedPreferences}
     * @throws RuntimeException if {@link this#sharedPreferences} has not been initialized
     * yet
     */
    public /*static*/ SharedPreferences getPreferences() {
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        throw new RuntimeException("Shared preferences not initialized.  Please call Builder.setContext().build()");
    }

    /**
     * Returns the underlying {@link SharedPreferences} instance from a static context
     *
     * @return an instance of {@link SharedPreferences}
     * @throws RuntimeException if {@link this#sharedPreferences} has not been initialized
     * yet
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     */
    public static SharedPreferences getPreferencesStatic() {
        return getInstance().getPreferences();
    }

    /**
     * @return a {@link Map} containing all key/value pairs of preferences
     * @see SharedPreferences#getAll()
     */
    public /*static*/ Map<String, ?> getAllPrefs() {
        return getPreferences().getAll();
    }

    /**
     * A static implementation of {@link Preferred#getAllPrefs()}
     *
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return a {@link Map} containing all key/value pairs of preferences
     * @see Preferred#getAllPrefs()
     */
    public static Map<String, ?> getAllPrefsStatic() {
        return getInstance().getAllPrefs();
    }

    /**
     * Stores an integer value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putInt(String, int)
     */
    public /*static*/ boolean putInt(final String key, final int value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * Stores an integer value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putInt(String, int)
     */
    public static boolean putIntStatic(final String key, final int value) {
        return getInstance().putInt(key, value);
    }

    /**
     * Retrieves a stored int value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getInt(String, int)
     */
    public /*static*/ int getInt(final String key, final int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    /**
     * Retrieves a stored int value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getInt(String, int)
     */
    public static int getIntStatic(final String key, final int defaultValue) {
        return getInstance().getInt(key, defaultValue);
    }

    /**
     * Retrieves a stored int value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or 0
     * @see Preferred#getInt(String, int)
     */
    public /*static*/ int getInt(final String key) {
        return getInt(key, 0);
    }

    /**
     * Retrieves a stored int value from a static context
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or 0
     * @see Preferred#getInt(String)
     */
    public static int getIntStatic(final String key) {
        return getInstance().getInt(key);
    }

    /**
     * Stores a boolean value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putBoolean(String, boolean)
     */
    public /*static*/ boolean putBoolean(final String key, final boolean value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * Stores a boolean value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putBoolean(String, boolean)
     */
    public static boolean putBooleanStatic(final String key, final boolean value) {
        return getInstance().putBoolean(key, value);
    }

    /**
     * Retrieves a stored boolean value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getBoolean(String, boolean)
     */
    public /*static*/ boolean getBoolean(final String key, final boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    /**
     * Retrieves a stored boolean value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getBoolean(String, boolean)
     */
    public static boolean getBooleanStatic(final String key, final boolean defaultValue) {
        return getInstance().getBoolean(key, defaultValue);
    }

    /**
     * Retrieves a stored boolean value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or false
     * @see Preferred#getBoolean(String, boolean)
     */
    public /*static*/ boolean getBoolean(final String key) {
        return getBoolean(key, false);
    }

    /**
     * Retrieves a stored boolean value from a static context
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or false
     * @see Preferred#getBoolean(String)
     */
    public static boolean getBooleanStatic(final String key) {
        return getInstance().getBoolean(key);
    }

    /**
     * Stores a long value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putLong(String, long)
     */
    public /*static*/ boolean putLong(final String key, final long value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * Stores a long value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putLong(String, long)
     */
    public static boolean putLongStatic(final String key, final long value) {
        return getInstance().putLong(key, value);
    }

    /**
     * Retrieves a stored long value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getLong(String, long)
     */
    public /*static*/ long getLong(final String key, final long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    /**
     * Retrieves a stored long value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getLong(String, long)
     */
    public static long getLongStatic(final String key, final long defaultValue) {
        return getInstance().getLong(key, defaultValue);
    }

    /**
     * Retrieves a stored long value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or 0L
     * @see Preferred#getLong(String, long)
     */
    public /*static*/ long getLong(final String key) {
        return getLong(key, 0L);
    }

    /**
     * Retrieves a stored long value from a static context
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or 0L
     * @see Preferred#getLong(String)
     */
    public static long getLongStatic(final String key) {
        return getInstance().getLong(key);
    }

    /**
     * Stores a float value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putFloat(String, float) nt)
     */
    public /*static*/ boolean putFloat(final String key, final float value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * Stores a float value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putFloat(String, float)
     */
    public static boolean putFloatStatic(final String key, final float value) {
        return getInstance().putFloat(key, value);
    }

    /**
     * Retrieves a stored float value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getFloat(String, float)
     */
    public /*static*/ float getFloat(final String key, final float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    /**
     * Retrieves a stored float value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getFloat(String, float)
     */
    public static float getFloatStatic(final String key, final float defaultValue) {
        return getInstance().getFloat(key, defaultValue);
    }

    /**
     * Retrieves a stored float value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred#getFloat(String, float)
     */
    public /*static*/ float getFloat(final String key) {
        return getFloat(key, 0f);
    }

    /**
     * Retrieves a stored float value from a static context
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getFloat(String)
     */
    public static float getFloatStatic(final String key) {
        return getInstance().getFloat(key);
    }

    /**
     * Stores a String value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putString(String, String)
     */
    public /*static*/ boolean putString(final String key, final String value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * Stores a String value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putString(String, String)
     */
    public static boolean putStringStatic(final String key, final String value) {
        return getInstance().putString(key, value);
    }

    /**
     * Retrieves a stored String value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getString(String, String)
     */
    public /*static*/ String getString(final String key, final String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    /**
     * Retrieves a stored String value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getString(String, String)
     */
    public static String getStringStatic(final String key, final String defaultValue) {
        return getInstance().getString(key, defaultValue);
    }

    /**
     * Retrieves a stored String value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred#getString(String, String)
     */
    public /*static*/ String getString(final String key) {
        return getString(key, "");
    }

    /**
     * Retrieves a stored String value from a static context
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getString(String)
     */
    public static String getStringStatic(final String key) {
        return getInstance().getString(key);
    }

    /**
     * Stores a String set value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor#putStringSet(String, Set)
     */
    public /*static*/ boolean putStringSet(final String key, final Set<String> value) {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }

    /**
     * Stores a String set value from a static context
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if the value was stored successfully, false otherwise
     * @see Preferred#putStringSet(String, Set)
     */
    public static boolean putStringSetStatic(final String key, final Set<String> value) {
        return getInstance().putStringSet(key, value);
    }

    /**
     * Retrieves a stored String set value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences#getStringSet(String, Set)
     */
    public /*static*/ Set<String> getStringSet(final String key, final Set<String> defaultValue) {
        return getPreferences().getStringSet(key, defaultValue);
    }

    /**
     * Retrieves a stored String set value from a static context
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getStringSet(String, Set)
     */
    public static Set<String> getStringSetStatic(final String key, final Set<String> defaultValue) {
        return getInstance().getStringSet(key, defaultValue);
    }

    /**
     * Retrieves a stored String set value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred#getStringSet(String, Set)
     */
    public /*static*/ Set<String> getStringSet(final String key) {
        return getStringSet(key, new HashSet<>());
    }

    /**
     * Retrieves a stored String set value
     *
     * @param key Name of the preference to retrieve
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the preference value, or defaultValue
     * @see Preferred#getStringSet(String)
     */
    public static Set<String> getStringSetStatic(final String key) {
        return getInstance().getStringSet(key);
    }

    /**
     * Removes a preference value
     *
     * @param key Name of preference to remove
     * @return True if removal was successful, false otherwise
     * @see android.content.SharedPreferences.Editor#remove(String)
     */
    public /*static*/ boolean removePreference(final String key) {
        SharedPreferences preferences = getPreferences();
        final SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    /**
     * Removes a preference value from a static context
     *
     * @param key Name of preference to remove
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if removal was successful, false otherwise
     * @see Preferred#removePreference(String)
     */
    public static boolean removePreferenceStatic(final String key) {
        return getInstance().removePreference(key);
    }

    /**
     * Checks if a preference value currently exists
     *
     * @param key Name of preference to check for
     * @return True if preference exists, false otherwise
     * @see SharedPreferences#contains(String)
     */
    public /*static*/ boolean containsPreference(final String key) {
        return getPreferences().contains(key);
    }

    /**
     * Checks if a preference value currently exists from a static context
     *
     * @param key Name of preference to check for
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return True if preference exists, false otherwise
     * @see Preferred#containsPreference(String)
     */
    public static boolean containsPreferenceStatic(final String key) {
        return getInstance().containsPreference(key);
    }

    /**
     * Remove all stored preferences
     *
     * @return the {@link android.content.SharedPreferences.Editor} for chaining
     * @see SharedPreferences.Editor#clear()
     */
    public /*static*/ SharedPreferences.Editor removeAllPrefs() {
        final SharedPreferences.Editor editor = getPreferences().edit();
        editor.clear();
        editor.apply();
        return editor;
    }

    /**
     * Remove all stored preferences from a static context
     *
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the {@link android.content.SharedPreferences.Editor} for chaining
     * @see Preferred#removeAllPrefs()
     */
    public static SharedPreferences.Editor removeAllPrefsStatic() {
        return getInstance().removeAllPrefs();
    }

    /**
     * @return the {@link android.content.SharedPreferences.Editor} of the underlying
     * {@link SharedPreferences} instance
     * @see SharedPreferences#edit()
     */
    public /*static*/ SharedPreferences.Editor edit() {
        return getPreferences().edit();
    }

    /**
     * A static implementation of {@link Preferred#edit()}
     *
     * @throws RuntimeException if {@link Preferred#INSTANCE} has not been initialized
     * @return the {@link android.content.SharedPreferences.Editor} of the underlying
     * {@link SharedPreferences} instance
     * @see Preferred#edit()
     */
    public static SharedPreferences.Editor editStatic() {
        return getInstance().edit();
    }

    /**
     * Builder class for Preferred.  This needs only be called from {@link Application#onCreate()},
     * all other methods can be accessed via Preferred.methodName()
     */
    public final static class Builder {

        private String key;
        private Context context;
        private boolean useDefault = false;

        /**
         * Set the filename for the {@link SharedPreferences} instance.  If this method is not called,
         * the application package name will be used
         *
         * @param prefsName Filename to use for {@link Preferred#sharedPreferences}
         * @return the {@link Builder} for chaining
         */
        public Builder setPrefsName(final String prefsName) {
            this.key = prefsName;
            return this;
        }

        /**
         * Set the {@link Context} used to instantiate the SharedPreferences
         *
         * @param context Application context
         * @return the {@link Builder} for chaining
         */
        public Builder setContext(final  Context context) {
            this.context = context;
            return this;
        }

        /**
         * Set whether to use the default SharedPreference file name. This filename is usually
         * the application package name, but if a {@link android.preference.PreferenceActivity}
         * or {@link android.preference.PreferenceFragment} is used, the system will append that
         * with _preferences
         *
         * @param useDefault True if the default file name should be used, false otherwise
         * @return the {@link Builder} for chaining
         */
        public Builder setUseDefaultSharedPreferences(boolean useDefault) {
            this.useDefault = useDefault;
            return this;
        }

        /**
         * Initialize the SharedPreferences instance for use in the application
         *
         * @throws RuntimeException if Context has not been set
         */
        public void build() {
            if (Objects.isNull(context)) {
                throw new RuntimeException("No context set, please set context before building");
            }

            if (Objects.isNull(key) || key.isEmpty()) {
                key = context.getPackageName();
            }

            key += useDefault ? DEFAULT_SUFFIX : "";

            Preferred.init(context, key);
        }

    }

}
