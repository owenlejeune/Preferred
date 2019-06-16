package com.owenlejeune.preffered.preferences

import android.content.Context
import android.preference.MultiSelectListPreference
import android.util.AttributeSet
import com.owenlejeune.preffered.Preferred
import com.owenlejeune.preffered.listeners.PreferredChangeListener

/**
 * Implementation of [MultiSelectListPreference] with the ability to add listeners for preference
 * changes, as well as auto-commit when the preference value changes
 */
@Suppress("UNCHECKED_CAST")
open class PreferredMultiSelectListPreference(context: Context, attrs: AttributeSet, defStyleAttr: Int)
    : MultiSelectListPreference(context, attrs, defStyleAttr) {

    private lateinit var listeners: MutableList<PreferredChangeListener>

    init {
        key?.let {
            listeners = ArrayList()
            setOnPreferenceChangeListener { _, n ->  setPreference(n)}
        }
        ?: throw RuntimeException("No key defined for this preference.  Please set one in preferences.xml")
    }

    private fun setPreference(newVal: Any): Boolean {
        val result = Preferred.putStringSetStatic(key, newVal as Set<String>)
        firePreferenceChanged(newVal)
        return result
    }

    fun getPreference(): Set<String> = Preferred.getStringSetStatic(key)

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