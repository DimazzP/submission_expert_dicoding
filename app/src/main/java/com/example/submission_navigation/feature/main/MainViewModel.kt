package com.example.submission_navigation.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.datastore.SettingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pref: SettingPreferences // Inject SettingPreferences dari Hilt
) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}
