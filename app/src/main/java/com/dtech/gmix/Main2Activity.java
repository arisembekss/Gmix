package com.dtech.gmix;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dtech.gmix.fragment.FragmentCola;
import com.dtech.gmix.preference.Config;
import com.dtech.gmix.preference.PrefManager;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText edcol1;
    Typeface plagiata, mars;
    TextView tit1, tit2;

    PrefManager prefManager;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_card);

        prefManager = new PrefManager(this);
        sharedPreferences = getSharedPreferences(Config.PREF_NAME, Config.PRIVATE_MODE);
        String login = (sharedPreferences.getString(Config.LOGIN, ""));
        if (login == "") {
            launchLogin();
        } else {
            FragmentCola frcola = new FragmentCola();
            FragmentManager fragmentManager = getSupportFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.add(R.id.frcola, frcola);
            //fragmentTransaction.commit();
            //fragmentManager = getSupportFragmentManager();
            if (savedInstanceState == null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frcola, frcola);

                fragmentTransaction.commit();
            }
            initUi();
        }

    }

    private void initUi() {

        mars = Typeface.createFromAsset(getAssets(), "fonts/marsmonster.ttf");
        plagiata = Typeface.createFromAsset(getAssets(), "fonts/plagiata.otf");
        tit1 = (TextView) findViewById(R.id.tit1);
        tit2 = (TextView) findViewById(R.id.tit2);
        tit1.setTypeface(plagiata);
        tit2.setTypeface(mars);
    }

    private void launchLogin() {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
