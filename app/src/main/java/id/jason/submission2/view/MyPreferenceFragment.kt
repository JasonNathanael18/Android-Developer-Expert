package id.jason.submission2.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import id.jason.submission2.R
import id.jason.submission2.R.string
import id.jason.submission2.service.AlarmReceiver

class MyPreferenceFragment: PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var RELEASE: String
    private lateinit var DAILY: String
    private lateinit var mcontext: Context
    private lateinit var releasePreference: SwitchPreference
    private lateinit var dailyPreference: SwitchPreference
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
        alarmReceiver = AlarmReceiver()
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == RELEASE) {
            releasePreference.isChecked = sharedPreferences.getBoolean(RELEASE, false)
            if (releasePreference.isChecked){
                alarmReceiver.setRepeatingAlarm(mcontext,"14:44" , AlarmReceiver.TYPE_RELEASE, "")
            }else{
                alarmReceiver.cancelAlarm(mcontext, AlarmReceiver.TYPE_RELEASE)
            }
        }
        if (key == DAILY) {
            dailyPreference.isChecked = sharedPreferences.getBoolean(DAILY, false)
            if (dailyPreference.isChecked){
                alarmReceiver.setRepeatingAlarm(mcontext,"07:00" , AlarmReceiver.TYPE_DAILY, getString(string.notif_release_alarm))
            }else{
                alarmReceiver.cancelAlarm(mcontext, AlarmReceiver.TYPE_DAILY)
            }
        }
    }
}