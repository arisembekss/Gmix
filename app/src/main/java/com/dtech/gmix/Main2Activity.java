package com.dtech.gmix;


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

import com.dtech.gmix.fragment.FragmentCola;

public class Main2Activity extends AppCompatActivity {

    EditText edcol1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_card);


        FragmentCola frcola = new FragmentCola();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frcola, frcola);
        fragmentTransaction.commit();

        edcol1 = (EditText) findViewById(R.id.ed1row1);

        edcol1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        edcol1.setFocusableInTouchMode(true);

                        //return true;

                }
                return false;
            }
        });
        /*edcol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edcol1.setFocusableInTouchMode(true);
            }
        });*/
    }

}
