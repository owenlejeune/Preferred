package com.owenlejeune.preffered.preferences;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

import com.owenlejeune.preffered.Preferred;
import com.owenlejeune.preffered.listeners.PreferredChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of {@link EditTextPreference} with the ability to add listeners for preference
 * changes, as well as auto-commit when the preference value changes
 */
@SuppressWarnings("unused")
public class PreferredEditTextPreference extends EditTextPreference {

    private List<PreferredChangeListener> listeners;

    public PreferredEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public PreferredEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public PreferredEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (Objects.isNull(getKey()) || getKey().isEmpty()) {
            throw new RuntimeException("No key defined for this preference.  Please set one in preferences.xml");
        }

        listeners = new ArrayList<>();

        setOnPreferenceChangeListener((p, o) -> setPreference(o));
    }

    private boolean setPreference(Object newVal) {
        boolean result = Preferred.putString(getKey(), (String)newVal);
        firePreferenceChanged(newVal);
        return result;
    }

    public String getPreference() {
        return Preferred.getString(getKey());
    }

    public void addPreferenceChangeListener(PreferredChangeListener listener) {
        listeners.add(listener);
    }

    public void removePreferenceChangeListener(PreferredChangeListener listener) {
        listeners.remove(listener);
    }

    private void firePreferenceChanged(final Object newValue) {
        listeners.forEach(l -> l.onPreferenceChange(newValue));
    }

}
