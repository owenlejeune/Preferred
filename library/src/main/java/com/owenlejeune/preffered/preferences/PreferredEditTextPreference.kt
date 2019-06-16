package com.owenlejeune.preffered.preferences

import android.content.Context
import android.preference.EditTextPreference
import android.util.AttributeSet
import com.owenlejeune.preffered.Preferred
import com.owenlejeune.preffered.listeners.PreferredChangeListener

/**
 * Implementation of [EditTextPreference] with the ability to add listeners for preference
 * changes, as well as auto-commit when the preference value changes
 */
open class PreferredEditTextPreference(context: Context, attrs: AttributeSet, defStyleAttr: Int)
    : EditTextPreference(context, attrs, defStyleAttr) {

    private lateinit var listeners: MutableList<PreferredChangeListener>

    init {
        key?.let {
            listeners = ArrayList()
            setOnPreferenceChangeListener { _, n ->  setPreference(n)}
        }
        ?: throw RuntimeException("No key defined for this preference.  Please set one in preferences.xml")
    }

    private fun setPreference(newVal: Any): Boolean {
        val result = Preferred.putStringStatic(key, newVal as String)
        firePreferenceChanged(newVal)
        return result
    }

    fun getPreference(): String = Preferred.getStringStatic(key)

    fun addPreferenceChangeListener(listener: PreferredChangeListener) {
        listeners.add(listener)
    }

    fun removePreferenceChangeListener(listener: PreferredChangeListener) {
        listeners.remove(listener)
    }

    private fun firePreferenceChanged(newVal: Any) {
        listeners.forEach { it.onPreferenceChange(newVal) }
    }

}