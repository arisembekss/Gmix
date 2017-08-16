package com.dtech.gmix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button bon, boff;
    TextView tv;

    DatabaseReference myRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //3fc00309d48d2da1bd61a00ad6bff547 -> 2 block
        //e9f60718e67952c7830fdb6f6c62d3ad -> 4 block
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("e9f60718e67952c7830fdb6f6c62d3ad/digital1");//2 block
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
        myRef.setValue(status);
        if (status == 1) {
            tv.setText("Status : on");
        } else {
            tv.setText("Status : off");
        }

    }
}
