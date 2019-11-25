package com.hausung.hstuting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.BaseAdapter;

import androidx.preference.PreferenceManager;

public class SettingPreferenceFragment  extends PreferenceFragment {

    SharedPreferences prefs;//key/data 저장

    ListPreference rankPreference; //튜터링 수준
    EditTextPreference timePreference; //참여 가능 시간
    EditTextPreference namePreference;
    EditTextPreference numberPreference;
    PreferenceScreen myinfoPreference;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        rankPreference  = (ListPreference)findPreference("ranking_list");
        timePreference = (EditTextPreference)findPreference("time_setting");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("ranking_list", "").equals("")){
            rankPreference.setSummary(prefs.getString("ranking_list", "매우 낮음"));
        }

        if(!prefs.getString("time_setting", "").equals("")){
            timePreference.setSummary(prefs.getString("time_setting", "참여 가능 시간 설정"));
        }

        if(!prefs.getString("custom_info", "").equals("")){
            namePreference.setSummary(prefs.getString("custom_info", "유예은"));
        }

        if(!prefs.getString("custom_info_num", "").equals("")){
            namePreference.setSummary(prefs.getString("custom_info_num", "1771163"));
        }
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("ranking_list")){
                rankPreference.setSummary(prefs.getString("ranking_list", "매우 낮음"));
            }
            if(key.equals("time_setting")){
                timePreference.setSummary(prefs.getString("time_setting", "참여 가능 시간 설정"));
            }

            if(key.equals("my_info")){

                if(key.equals("custom_info")){
                    namePreference.setSummary(prefs.getString("custom_info", "유예은"));
                }

                if(key.equals("custom_info_name")){
                    namePreference.setSummary(prefs.getString("custom_info_name", "1771163"));
                }

                //2뎁스 PreferenceScreen 내부에서 발생한 환경설정 내용을 2뎁스 PreferenceScreen에 적용하기 위한 소스
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }

        }
    };
}
