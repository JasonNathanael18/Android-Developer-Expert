package id.jason.submission2.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import id.jason.submission2.R

class MyPreferenceFragment: PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var RELEASE: String
    private lateinit var DAILY: String

    private lateinit var releasePreference: SwitchPreference
    private lateinit var dailyPreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        releasePreference.isChecked = sh.getBoolean(RELEASE, false)
        dailyPreference.isChecked = sh.getBoolean(DAILY, false)
    }

    private fun init(){
        RELEASE = resources.getString(R.string.key_release)
        DAILY = resources.getString(R.string.key_daily)

        releasePreference = findPreference<SwitchPreference>(RELEASE) as SwitchPreference
        dailyPreference = findPreference<SwitchPreference>(DAILY) as SwitchPreference
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == RELEASE) {
            releasePreference.isChecked = sharedPreferences.getBoolean(RELEASE, false)
        }
        if (key == DAILY) {
            dailyPreference.isChecked = sharedPreferences.getBoolean(DAILY, false)
        }
    }
}