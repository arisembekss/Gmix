package com.dtech.gmix;


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

public class Main2Activity extends AppCompatActivity {

    EditText edcol1;
    Typeface plagiata, mars;
    TextView tit1, tit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_card);


        FragmentCola frcola = new FragmentCola();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frcola, frcola);
        fragmentTransaction.commit();

        mars = Typeface.createFromAsset(getAssets(), "fonts/marsmonster.ttf");
        plagiata = Typeface.createFromAsset(getAssets(), "fonts/plagiata.otf");
        tit1 = (TextView) findViewById(R.id.tit1);
        tit2 = (TextView) findViewById(R.id.tit2);
        tit1.setTypeface(plagiata);
        tit2.setTypeface(mars);
        /*edcol1 = (EditText) findViewById(R.id.ed1row1);

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
        });*/
        /*edcol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edcol1.setFocusableInTouchMode(true);
            }
        });*/
    }

}
