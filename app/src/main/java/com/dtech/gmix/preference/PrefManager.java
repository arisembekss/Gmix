package com.dtech.gmix.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aris on 16/08/17.
 */

public class PrefManager {

    SharedPreferences pref;
    public SharedPreferences.Editor editor;

    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "app-welcome";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(Config.PREF_NAME, Config.PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDigitalaBloka(String displayNumber) {
        editor.putString(Config.DIGITAL_A_BLOCK_A, displayNumber);
        editor.commit();
    }

    public void setBlokb(String displayNumber) {
        editor.putString(Config.DIGITAL_A_BLOCK_B, displayNumber);
        editor.commit();
    }

    public void setRuangBloka(String ruang) {
        editor.putString(Config.RUANG_A, ruang);
        editor.commit();
    }

    public void setRuangBlokb(String ruang) {
        editor.putString(Config.RUANG_B, ruang);
        editor.commit();
    }

    public void setRuanga(String ruang) {
        editor.putString(Config.RUANGA1, ruang);
        editor.commit();
    }

    public void setRuangb(String ruang) {
        editor.putString(Config.RUANGA2, ruang);
        editor.commit();
    }

    public void setRuangc(String ruang) {
        editor.putString(Config.RUANGA3, ruang);
        editor.commit();
    }

    public void setRuangd(String ruang) {
        editor.putString(Config.RUANGA4, ruang);
        editor.commit();
    }

    public void setRuange(String ruang) {
        editor.putString(Config.RUANGA5, ruang);
        editor.commit();
    }

    public void setRuangf(String ruang) {
        editor.putString(Config.RUANGA6, ruang);
        editor.commit();
    }

    public void setRuangg(String ruang) {
        editor.putString(Config.RUANGA7, ruang);
        editor.commit();
    }

    public void setRuangh(String ruang) {
        editor.putString(Config.RUANGA8, ruang);
        editor.commit();
    }

    public void setRuangi(String ruang) {
        editor.putString(Config.RUANGA9, ruang);
        editor.commit();
    }

    public void setRuangj(String ruang) {
        editor.putString(Config.RUANGA10, ruang);
        editor.commit();
    }

    public void setRuangk(String ruang) {
        editor.putString(Config.RUANGA11, ruang);
        editor.commit();
    }

    public void setRuangl(String ruang) {
        editor.putString(Config.RUANGA12, ruang);
        editor.commit();
    }

    public void setLogin(String login) {
        editor.putString(Config.LOGIN, login);
        editor.commit();
    }
}
