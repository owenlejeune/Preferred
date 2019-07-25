package com.owenlejeune.preffered.preferences

import android.content.Context
import androidx.preference.SwitchPreference
import android.util.AttributeSet
import com.owenlejeune.preffered.Preferred
import com.owenlejeune.preffered.listeners.PreferredChangeListener

/**
 * Implementation of [SwitchPreference] with the ability to add listeners for preference
 * changes, as well as auto-commit when the preference value changes
 */
open class PreferredSwitchPreference(context: Context, attrs: AttributeSet, defStyleAttr: Int): SwitchPreference(context, attrs, defStyleAttr) {

    private lateinit var listeners: MutableList<PreferredChangeListener>

    init {
        key?.let {
            listeners = ArrayList()
            setOnPreferenceChangeListener { _, n ->  setPreference(n)}
        }
        ?: throw RuntimeException("No key defined for this preference.  Please set one in preferences.xml")
    }

    private fun setPreference(newVal: Any): Boolean {
        val result = Preferred.putBooleanStatic(key, newVal as Boolean)
        firePreferenceChanged(newVal)
        return result
    }

    fun getPreference(): Boolean = Preferred.getBooleanStatic(key)

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