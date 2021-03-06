# Preferred
[![Build Status](https://travis-ci.com/owenlejeune/Preferred.svg?branch=master)](https://travis-ci.com/owenlejeune/Preferred)

A simple and powerful Android library for handling Shared Preferences

Simply initialize the **Preferred** instance in you Application class `onCreate()` method and you're ready to go
```Kotlin
class PreferredApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // initialize Preferred
        Preferred.Builder()
            .setContext(this)
            .setPrefsName(getPackageName())
            .setUseDefaultSharedPreferences(true)
            .build()
    }
}
```

## Usage
Once **Preferred** has been initialized, there are two ways you can handle shared Preferences

#### Methods
**Preferred** offers both static and member functions for all shared preference value types.  Storing a value is as simple as
```Kotlin
// Static Methods
Preferred.putStringStatic(key, string)
Preferred.putFloatStatic(key, float)
// Member functions
Preferred.getInstance().putString(key, string)
Preferred.getInstance().putLong(key, long)
```
Retrieving a value is also simple.  Providing a key will return the **Preferred** default value if that preference does not exist, but an option default value can also be provided.
```Kotlin
// Static
val staticData = Preferred.getStringStatic(key, string)
val staticNumber = Preferred.getFloatStatic(key)
// Member
val data = Preferred.getInstance().getString(key)
val number = Preferred.getInstance().getFloat(key, float)
```

#### Preferences
**Preferred** also contains custom implementations of all Android preference classes
* PreferredCheckBoxPreference
* PreferredEditTextPreference
* PreferredListPreference
* PreferredMultiSelectListPreference
* PreferredSwitchPreference

These classes contain all the functionality of the normal Android preference implementation, with added functionality for automatically handling commits through **Preferred**.  Simply use these classes in your `preferences.xml`, then inflate your preferences like you would normally.
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.preference.PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
    <com.owenlejeune.preffered.preferences.PreferredCheckBoxPreference
        ...
        android:key="checkBoxPref" />
    <com.owenlejeune.preffered.preferences.PreferredDropDownPreference
        ...
        android:key="dropDownPref" />
    <com.owenlejeune.preffered.preferences.PreferredEditTextPreference
        ...
        android:key="editTextPref" />
    <com.owenlejeune.preffered.preferences.PreferredListPreference
        ...
        android:key="listPref" />
    <com.owenlejeune.preffered.preferences.PreferredMultiSelectListPreference
        ...
        android:key="multiListPref" />
    <com.owenlejeune.preffered.preferences.PreferredSeekBarPreference
            ...
            android:key="seekBarPref" />
    <com.owenlejeune.preffered.preferences.PreferredSwitchPreference
        ...
        android:key="switchPref" />
</androidx.preference.PreferenceScreen>
```
You must specify a `key` attribute for each preference because that is the value that will be used for all **Preferred** get/put methods.

## Adding To Your Project
Grab the latest release through Gradle:
```groovy
dependencies {
    implementation 'com.github.owenlejeune:Preferred:2.1.2'
}
```

## Releases

#### 2.1.2
* support for JvmOverloads constructors

#### 2.1
* **NEW** written with androidx!
* also increases support up to API 29 (Q) and down to API 19 (KitKat)
* new preference additions:
    * PreferredDropDownPreference
    * PreferredSeekBarPreference

#### 2.0
* **NEW** Completely re-written in Kotlin!

#### 1.0
* Initial release

## License
```
Copyright 2019 Owen LeJeune

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
