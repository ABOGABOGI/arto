package com.example.cyber_net.apps.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    //key
    public static final String SP_KEY = "Login";

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    //buat sharefpref dari class lain
    public Session(Context context) {
        //dengan key sampahku
        sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        this.context = context;
        spEditor = sp.edit();
    }

    public void saveSessionString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSessionBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSessionBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public String getSessionString(String key) {
        return sp.getString(key, "");
    }

}