package com.practice.bmi;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefTheme {
    private static final String PREF_NAME = "MODE";
    private static final String NIGHT_MODE_KEY = "night";
    private final SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SharedPrefTheme(Context context) {
      sp = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
      editor = sp.edit();
    }

    public void setNightModeState(boolean state) {
        editor.putBoolean(NIGHT_MODE_KEY, state);
        editor.commit();
    }

    public boolean loadNightModeState() {
        return sp.getBoolean(NIGHT_MODE_KEY, false);
    }
}
