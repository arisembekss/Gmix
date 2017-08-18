package com.dtech.gmix;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    TextView tv2, tvruang1;
    Button btnAdd1, btnAdd2;

    PrefManager prefManager;
    SharedPreferences sharedPreferences;

    String digital1blocka, ruangA, blockb;

    DatabaseReference myRef, myRef2;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(this);

        database = FirebaseDatabase.getInstance();
        initRealtimeDbase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initUi();
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
                prefManager.setDigitalaBloka(bloka);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //int digital1blocka = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                String blokb = String.valueOf(dataSnapshot.getValue());
                Log.d("Value blok-b ", blokb);
                prefManager.setBlokb(blokb);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initUi() {
        sharedPreferences = getSharedPreferences(Config.PREF_NAME, Config.PRIVATE_MODE);
        digital1blocka = (sharedPreferences.getString(Config.DIGITAL_A_BLOCK_A, ""));
        blockb = (sharedPreferences.getString(Config.DIGITAL_A_BLOCK_B, ""));
        ruangA = (sharedPreferences).getString(Config.RUANG_A, "");

        //lineRow1 = (RelativeLayout) findViewById(R.id.linerow1);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        tv2 = (TextView) findViewById(R.id.textView2);
        tvruang1 = (TextView) findViewById(R.id.tvruang1);
        tvruang1.setText(ruangA);
        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch1.setOnCheckedChangeListener(this);
        switch2.setOnCheckedChangeListener(this);
        tv2.setText(digital1blocka);
        btnAdd1 = (Button) findViewById(R.id.btnAdd1);
        btnAdd1.setOnClickListener(this);

        if (digital1blocka.equals("0")) {
            switch1.setChecked(false);
        } else{
            switch1.setChecked(true);
        }

        if (blockb.equals("0")) {
            switch2.setChecked(false);
        } else{
            switch2.setChecked(true);
        }


        //switch1.isChecked()

    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.switch1:
                if (b) {
                    myRef.setValue(1);
                    prefManager.setDigitalaBloka("1");
                    img1.setImageResource(R.mipmap.ic_launcher);

                } else {
                    myRef.setValue(0);
                    prefManager.setDigitalaBloka("0");
                    img1.setImageResource(R.mipmap.ic_launcher_round);
                }
                break;
            case R.id.switch2:
                if (b) {
                    myRef2.setValue(1);
                    prefManager.setBlokb("1");
                    img2.setImageResource(R.mipmap.ic_launcher);

                } else {
                    myRef2.setValue(0);
                    prefManager.setBlokb("0");
                    img2.setImageResource(R.mipmap.ic_launcher_round);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd1:
                /*edAdd1.setVisibility(View.VISIBLE);
                btnAdd1.setVisibility(View.GONE);*/

                CustomDialog custom = new CustomDialog().makeDialog(this, "Edit Ruangan", tvruang1.getText().toString(), "");
                custom.setClickListener(this);
        }
    }


    //@Override
    public void onClick(View view, String data) {
        String ndata = data;/*.replace(",", ", ");*/
        Log.d("value itemclick", ndata);
        Toast.makeText(ControllActivity.this, ndata, Toast.LENGTH_LONG).show();
        tvruang1.setText(ndata);

    }
}
