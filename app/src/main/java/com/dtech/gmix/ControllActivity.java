package com.dtech.gmix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dtech.gmix.preference.Config;
import com.dtech.gmix.preference.CustomDialogClickListener;
import com.dtech.gmix.preference.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControllActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, CustomDialogClickListener {

    ImageView img1, img2;
    Switch switch1, switch2;
    TextView tv2, tvruang1, tvruang2, tit1, tit2;
    ImageButton btnAdd1, btnAdd2;

    PrefManager prefManager;
    SharedPreferences sharedPreferences;
    Typeface plagiata, mars;

    String digital1blocka, ruangA, blockb, ruangb;

    DatabaseReference myRef, myRef2;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controll);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        prefManager = new PrefManager(this);
        sharedPreferences = getSharedPreferences(Config.PREF_NAME, Config.PRIVATE_MODE);
        String login = (sharedPreferences.getString(Config.LOGIN, ""));
        if (login == "") {
            launchLogin();
        } else {
            database = FirebaseDatabase.getInstance();
            initRealtimeDbase();

            initUi();
        }
    }

    private void launchLogin() {
        Intent intent = new Intent(ControllActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initRealtimeDbase() {
        myRef = database.getReference("3fc00309d48d2da1bd61a00ad6bff547/digital1");
        myRef2 = database.getReference("3fc00309d48d2da1bd61a00ad6bff547/digital2");

        //myRef.keepSynced(true);
        //myRef2.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bloka = String.valueOf(dataSnapshot.getValue());
                Log.d("Value blok-a ", bloka);
                //prefManager.setDigitalaBloka(bloka);
                if (bloka == "0") {
                    switch1.setChecked(false);
                    img1.setImageResource(R.drawable.energy_off);
                } else{
                    switch1.setChecked(true);
                    img1.setImageResource(R.drawable.energy_on);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //int digital1blocka = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                String sblokb = String.valueOf(dataSnapshot.getValue());
                Log.d("Value blok-b ", sblokb);
                //prefManager.setBlokb(blokb);
                if (sblokb == "0") {
                    switch2.setChecked(false);
                    img2.setImageResource(R.drawable.energy_off);
                } else{
                    switch2.setChecked(true);
                    img2.setImageResource(R.drawable.energy_on);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initUi() {
        mars = Typeface.createFromAsset(getAssets(), "fonts/marsmonster.ttf");
        plagiata = Typeface.createFromAsset(getAssets(), "fonts/plagiata.otf");
        digital1blocka = (sharedPreferences.getString(Config.DIGITAL_A_BLOCK_A, ""));
        blockb = (sharedPreferences.getString(Config.DIGITAL_A_BLOCK_B, ""));
        ruangA = (sharedPreferences).getString(Config.RUANG_A, "");
        ruangb = (sharedPreferences.getString(Config.RUANG_B, ""));

        //lineRow1 = (RelativeLayout) findViewById(R.id.linerow1);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        tv2 = (TextView) findViewById(R.id.textView2);
        tvruang1 = (TextView) findViewById(R.id.tvruang1);
        tvruang1.setText(ruangA);
        tvruang2 = (TextView) findViewById(R.id.tvruang2);
        tvruang2.setText(ruangb);
        tit1 = (TextView) findViewById(R.id.tit1);
        tit2 = (TextView) findViewById(R.id.tit2);
        tit1.setTypeface(plagiata);
        tit2.setTypeface(mars);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        tv2.setText(digital1blocka);
        btnAdd1 = (ImageButton) findViewById(R.id.btnAdd1);
        btnAdd2 = (ImageButton) findViewById(R.id.btnAdd2);
        btnAdd1.setOnClickListener(this);
        btnAdd2.setOnClickListener(this);






        //switch1.isChecked()

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.switch1:
                if (b) {
                    myRef.setValue(1);
                    prefManager.setDigitalaBloka("1");
                    img1.setImageResource(R.drawable.energy_on);

                } else {
                    myRef.setValue(0);
                    prefManager.setDigitalaBloka("0");
                    img1.setImageResource(R.drawable.energy_off);
                }
                break;
            case R.id.switch2:
                if (b) {
                    myRef2.setValue(1);
                    prefManager.setBlokb("1");
                    img2.setImageResource(R.drawable.energy_on);

                } else {
                    myRef2.setValue(0);
                    prefManager.setBlokb("0");
                    img2.setImageResource(R.drawable.energy_off);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd1:
                CustomDialog custom = new CustomDialog().makeDialog(this, "Edit Ruangan", tvruang1.getText().toString(), "1");
                custom.setClickListener(this);
                break;
            case R.id.btnAdd2:
                CustomDialog custom2 = new CustomDialog().makeDialog(this, "Edit Ruangan", tvruang2.getText().toString(), "2");
                custom2.setClickListener(this);
        }
    }


    //@Override
    public void onClick(View view, String data, String tag) {

        Log.d("value itemclick", data);
        Toast.makeText(ControllActivity.this, data+", tag :"+tag, Toast.LENGTH_LONG).show();
        if (tag.matches("2")) {
            tvruang2.setText(data);
        } else {
            tvruang1.setText(data);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
