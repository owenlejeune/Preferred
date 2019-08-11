package com.owenlejeune.preffered.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.preference.SeekBarPreference
import com.owenlejeune.preffered.Preferred
import com.owenlejeune.preffered.listeners.PreferredChangeListener
import java.lang.RuntimeException

/**
 * Implementation of [SeekBarPreference] with the ability to add listeners for preference
 * changes, as well as auto-commit when the preference value changes
 */
open class PreferredSeekBarPreference(context: Context?, attrs: AttributeSet?) : SeekBarPreference(context, attrs) {

    private lateinit var listeners: MutableList<PreferredChangeListener>

    init {
        key?.let {
            listeners = ArrayList()
            setOnPreferenceChangeListener { _, n -> setPreference(n) }
        } ?: throw RuntimeException("No key defined for this preference.  Please set one in preferences.xml")
    }

    private fun setPreference(newVal: Any): Boolean {
        val result = Preferred.putIntStatic(key, newVal as Int)
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