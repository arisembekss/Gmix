package com.dtech.gmix;

import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dtech.gmix.preference.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button bon, boff;
    TextView tv;

    DatabaseReference myRef1, myRef2;
    FirebaseDatabase database;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefManager = new PrefManager(this);

        //3fc00309d48d2da1bd61a00ad6bff547 -> 2 block
        //e9f60718e67952c7830fdb6f6c62d3ad -> 4 block
        database = FirebaseDatabase.getInstance();
        /*myRef1 = database.getReference("e9f60718e67952c7830fdb6f6c62d3ad/digital1");
        myRef1.keepSynced(true);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //int digital1blocka = Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                String digital1blocka = String.valueOf(dataSnapshot.getValue());
                //Log.d("Value: ", digital1blocka);
                prefManager.setDigitalaBloka(digital1blocka);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        bon = (Button) findViewById(R.id.on);
        boff = (Button) findViewById(R.id.off);
        tv = (TextView) findViewById(R.id.tv);

        bon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ControllActivity.class);
                startActivity(intent);
            }
        });

        boff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLampu(0);

            }
        });
    }

    private void setLampu(int status) {
        //myRef1.setValue(status);
        if (status == 1) {
            tv.setText("Status : on");
        } else {
            tv.setText("Status : off");
        }

    }
}
