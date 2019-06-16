package com.owenlejeune.preffered

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.lang.RuntimeException
import kotlin.collections.HashSet

class Preferred private constructor(context: Context, key: String){
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    companion object {

        private val TAG = "Preferred"
        private val DEFAULT_SUFFIX = "_preferences"
        private lateinit var instance: Preferred

        @JvmStatic
        fun getInstance(): Preferred {
            return if (::instance.isInitialized) instance
            else throw RuntimeException("Instance has not been initialized.  Please call Builder.build() to initialize")
        }
        
        @JvmStatic
        private fun init(context: Context, key: String) {
            if (!::instance.isInitialized) instance = Preferred(context, key)
            else Log.w(TAG, "Instance has already been initialize, skipping")
        }

        /**
         * Returns the underlying [SharedPreferences] instance
         *
         * @return an instance of [SharedPreferences]
         * @throws RuntimeException if [instance] has not been initialized
         * yet
         */
        @JvmStatic
        fun getPreferencesStatic() = getInstance().getPreferences()

        /**
         * A static implementation of [Preferred.getAllPrefs]
         *
         * @throws RuntimeException if [instance] has not been initialized
         * @return a [Map] containing all key/value pairs of preferences
         * @see [Preferred.getAllPrefs]
         */
        @JvmStatic
        fun getAllPrefsStatic() = getInstance().getAllPrefs()

        /**
         * Stores an integer value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see [Preferred.putInt]
         */
        @JvmStatic
        fun putIntStatic(key: String, value: Int) = getInstance().putInt(key, value)

        /**
         * Retrieves a stored int value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return the preference value, or defaultValue
         * @see [SharedPreferences.getInt]
         */
        @JvmStatic
        fun getIntStatic(key: String, defaultValue: Int) = getInstance().getInt(key, defaultValue)

        /**
         * Retrieves a stored int value from a static context
         *
         * @param key Name of the preference to retrieve
         * @return the preference value, or defaultValue
         * @see [SharedPreferences.getInt]
         */
        @JvmStatic
        fun getIntStatic(key: String) = getInstance().getInt(key)

        /**
         * Stores a boolean value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see [Preferred.putBoolean]
         */
        @JvmStatic
        fun putBooleanStatic(key: String, value: Boolean) = getInstance().putBoolean(key, value)

        /**
         * Retrieves a stored boolean value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see [Preferred.getBoolean]
         */
        @JvmStatic
        fun getBooleanStatic(key: String, defaultValue: Boolean) = getInstance().getBoolean(key, defaultValue)

        /**
         * Retrieves a stored boolean value from a static context
         *
         * @param key Name of the preference to retrieve
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see [Preferred.getBoolean]
         */
        @JvmStatic
        fun getBooleanStatic(key: String) = getInstance().getBoolean(key, false)

        /**
         * Stores a long value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see Preferred.putLong
         */
        @JvmStatic
        fun putLongStatic(key: String, value: Long): Boolean = getInstance().putLong(key, value)

        /**
         * Retrieves a stored long value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getLong
         */
        @JvmStatic
        fun getLongStatic(key: String, defaultValue: Long): Long = getInstance().getLong(key, defaultValue)

        /**
         * Retrieves a stored long value from a static context
         *
         * @param key Name of the preference to retrieve
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or 0L
         * @see Preferred.getLong
         */
        @JvmStatic
        fun getLongStatic(key: String): Long = getInstance().getLong(key)

        /**
         * Stores a float value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see Preferred.putFloat
         */
        @JvmStatic
        fun putFloatStatic(key: String, value: Float): Boolean = getInstance().putFloat(key, value)

        /**
         * Retrieves a stored float value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getFloat
         */
        @JvmStatic
        fun getFloatStatic(key: String, defaultValue: Float): Float = getInstance().getFloat(key, defaultValue)

        /**
         * Retrieves a stored float value from a static context
         *
         * @param key Name of the preference to retrieve
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getFloat
         */
        @JvmStatic
        fun getFloatStatic(key: String): Float = getInstance().getFloat(key)

        /**
         * Stores a String value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see Preferred.putString
         */
        @JvmStatic
        fun putStringStatic(key: String, value: String): Boolean = getInstance().putString(key, value)

        /**
         * Retrieves a stored String value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getString
         */
        @JvmStatic
        fun getStringStatic(key: String, defaultValue: String): String = getInstance().getString(key, defaultValue)

        /**
         * Retrieves a stored String value from a static context
         *
         * @param key Name of the preference to retrieve
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getString
         */
        @JvmStatic
        fun getStringStatic(key: String): String = getInstance().getString(key)

        /**
         * Stores a String set value from a static context
         *
         * @param key Name of the preference to store
         * @param value New value for this preference
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if the value was stored successfully, false otherwise
         * @see Preferred.putStringSet
         */
        @JvmStatic
        fun putStringSetStatic(key: String, value: Set<String>): Boolean = getInstance().putStringSet(key, value)

        /**
         * Retrieves a stored String set value from a static context
         *
         * @param key Name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getStringSet
         */
        @JvmStatic
        fun getStringSetStatic(key: String, defaultValue: Set<String>): Set<String> = getInstance().getStringSet(key, defaultValue)

        /**
         * Retrieves a stored String set value
         *
         * @param key Name of the preference to retrieve
         * @throws RuntimeException if [instance] has not been initialized
         * @return the preference value, or defaultValue
         * @see Preferred.getStringSet
         */
        @JvmStatic
        fun getStringSetStatic(key: String): Set<String> = getInstance().getStringSet(key)

        /**
         * Removes a preference value from a static context
         *
         * @param key Name of preference to remove
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if removal was successful, false otherwise
         * @see Preferred.removePreference
         */
        @JvmStatic
        fun removePreferenceStatic(key: String): Boolean = getInstance().removePreference(key)

        /**
         * Checks if a preference value currently exists from a static context
         *
         * @param key Name of preference to check for
         * @throws RuntimeException if [instance] has not been initialized
         * @return True if preference exists, false otherwise
         * @see Preferred.containsPreference
         */
        @JvmStatic
        fun containsPreferenceStatic(key: String): Boolean = getInstance().containsPreference(key)

        /**
         * Remove all stored preferences from a static context
         *
         * @throws RuntimeException if [instance] has not been initialized
         * @return the [android.content.SharedPreferences.Editor] for chaining
         * @see Preferred.removeAllPrefs
         */
        @JvmStatic
        fun removeAllPrefsStatic(): SharedPreferences.Editor = getInstance().removeAllPrefs()

        /**
         * A static implementation of [Preferred.edit]
         *
         * @throws RuntimeException if [instance] has not been initialized
         * @return the [android.content.SharedPreferences.Editor] of the underlying
         * [SharedPreferences] instance
         * @see Preferred.edit
         */
        @JvmStatic
        fun editStatic(): SharedPreferences.Editor = getInstance().edit()

    }

    /**
     * Returns the underlying [SharedPreferences] instance from a static context
     *
     * @return an instance of [SharedPreferences]
     */
    fun getPreferences() = sharedPreferences

    /**
     * @return a [Map] containing all key/value pairs of preferences
     * @see [SharedPreferences.getAll]
     */
    fun getAllPrefs(): Map<String, *> = sharedPreferences.all

    /**
     * Stores an integer value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see [SharedPreferences.Editor.putInt]
     */
    fun putInt(key: String, value: Int): Boolean {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored int value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see [SharedPreferences.getInt]
     */
    fun getInt(key: String, defaultValue: Int) = sharedPreferences.getInt(key, defaultValue)

    /**
     * Retrieves a stored int value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or 0
     * @see [SharedPreferences.getInt]
     */
    fun getInt(key: String) = getInt(key, 0)

    /**
     * Stores a boolean value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see [SharedPreferences.Editor.putBoolean]
     */
    fun putBoolean(key: String, value: Boolean): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored boolean value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see [SharedPreferences.getBoolean]
     */
    fun getBoolean(key: String, defaultValue: Boolean) = sharedPreferences.getBoolean(key, defaultValue)

    /**
     * Retrieves a stored boolean value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or false
     * @see [Preferred.getBoolean]
     */
    fun getBoolean(key: String) = getBoolean(key, false)

    /**
     * Stores a long value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor.putLong
     */
    fun putLong(key: String, value: Long): Boolean {
        val editor = getPreferences().edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored long value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences.getLong
     */
    fun getLong(key: String, defaultValue: Long): Long = sharedPreferences.getLong(key, defaultValue)

    /**
     * Retrieves a stored long value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or 0L
     * @see Preferred.getLong
     */
    fun getLong(key: String): Long = getLong(key, 0L)

    /**
     * Stores a float value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor.putFloat
     */
    fun putFloat(key: String, value: Float): Boolean {
        val editor = getPreferences().edit()
        editor.putFloat(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored float value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences.getFloat
     */
    fun getFloat(key: String, defaultValue: Float): Float = sharedPreferences.getFloat(key, defaultValue)

    /**
     * Retrieves a stored float value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred.getFloat
     */
    fun getFloat(key: String): Float = getFloat(key, 0f)

    /**
     * Stores a String value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor.putString
     */
    fun putString(key: String, value: String): Boolean {
        val editor = getPreferences().edit()
        editor.putString(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored String value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences.getString
     */
    fun getString(key: String, defaultValue: String): String = sharedPreferences.getString(key, defaultValue)!!

    /**
     * Retrieves a stored String value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred.getString
     */
    fun getString(key: String): String = getString(key, "")

    /**
     * Stores a String set value
     *
     * @param key Name of the preference to store
     * @param value New value for this preference
     * @return True if the value was stored successfully, false otherwise
     * @see android.content.SharedPreferences.Editor.putStringSet
     */
    fun putStringSet(key: String, value: Set<String>): Boolean {
        val editor = getPreferences().edit()
        editor.putStringSet(key, value)
        return editor.commit()
    }

    /**
     * Retrieves a stored String set value
     *
     * @param key Name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return the preference value, or defaultValue
     * @see SharedPreferences.getStringSet
     */
    fun getStringSet(key: String, defaultValue: Set<String>): Set<String> = sharedPreferences.getStringSet(key, defaultValue)!!

    /**
     * Retrieves a stored String set value
     *
     * @param key Name of the preference to retrieve
     * @return the preference value, or defaultValue
     * @see Preferred.getStringSet
     */
    fun getStringSet(key: String): Set<String> = getStringSet(key, HashSet())

    /**
     * Removes a preference value
     *
     * @param key Name of preference to remove
     * @return True if removal was successful, false otherwise
     * @see android.content.SharedPreferences.Editor.remove
     */
    fun removePreference(key: String): Boolean {
        val preferences = getPreferences()
        val editor = preferences.edit()
        editor.remove(key)
        return editor.commit()
    }

    /**
     * Checks if a preference value currently exists
     *
     * @param key Name of preference to check for
     * @return True if preference exists, false otherwise
     * @see SharedPreferences.contains
     */
    fun containsPreference(key: String): Boolean = sharedPreferences.contains(key)

    /**
     * Remove all stored preferences
     *
     * @return the [android.content.SharedPreferences.Editor] for chaining
     * @see SharedPreferences.Editor.clear
     */
    fun removeAllPrefs(): SharedPreferences.Editor {
        val editor = getPreferences().edit()
        editor.clear()
        editor.apply()
        return editor
    }

    /**
     * @return the [android.content.SharedPreferences.Editor] of the underlying
     * [SharedPreferences] instance
     * @see SharedPreferences.edit
     */
    fun edit(): SharedPreferences.Editor = getPreferences().edit()

    /**
     * Builder class for Preferred.  This needs only be called from [Application.onCreate],
     * all other methods can be accessed via Preferred.methodName()
     */
    class Builder {
        
        private var key: String? = null
        private var context: Context? = null
        private var useDefault = false

        /**
         * Set the filename for the [SharedPreferences] instance.  If this method is not called,
         * the application package name will be used
         *
         * @param prefsName Filename to use for [Preferred.sharedPreferences]
         * @return the [Builder] for chaining
         */
        fun setPrefsName(prefsName: String): Builder {
            this.key = prefsName
            return this
        }

        /**
         * Set the [Context] used to instantiate the SharedPreferences
         *
         * @param context Application context
         * @return the [Builder] for chaining
         */
        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }

        /**
         * Set whether to use the default SharedPreference file name. This filename is usually
         * the application package name, but if a [android.preference.PreferenceActivity]
         * or [android.preference.PreferenceFragment] is used, the system will append that
         * with _preferences
         *
         * @param useDefault True if the default file name should be used, false otherwise
         * @return the [Builder] for chaining
         */
        fun setUseDefaultSharedPreferences(useDefault: Boolean): Builder {
            this.useDefault = useDefault
            return this
        }

        /**
         * Initialize the SharedPreferences instance for use in the application
         *
         * @throws RuntimeException if Context has not been set
         */
        fun build() {
            context ?: throw RuntimeException("No context set, please set context before building")

            key ?: context!!.packageName

            key += if (useDefault) DEFAULT_SUFFIX else ""

            init(context!!, key!!)
        }
    }

}