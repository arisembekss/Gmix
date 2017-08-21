package com.dtech.gmix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dtech.gmix.R;
import com.dtech.gmix.preference.Config;
import com.dtech.gmix.preference.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by aris on 21/08/17.
 */

public class FragmentCola extends Fragment implements View.OnClickListener {

    View view;

    CardView cardroot;
    RelativeLayout rel1;
    TextView tblok;
    Switch switch1;
    ImageView img1;
    EditText ed1, ed2;
    ImageButton imgbtn1, imgbtn2;

    PrefManager prefManager;
    DatabaseReference myRef;
    FirebaseDatabase database;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_cola, container, false);

        database = FirebaseDatabase.getInstance();
        initUi();
        initRealtimeDbase();

        prefManager = new PrefManager(getActivity());
        return view;
    }

    private void initRealtimeDbase() {
        myRef = database.getReference(Config.REF_DB+Config.REF_1);
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
    }

    private void initUi() {
        cardroot = (CardView) view.findViewById(R.id.cardroot);
        rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
        rel1.setBackgroundResource(R.color.blue2);
        tblok = (TextView) view.findViewById(R.id.textblok);
        tblok.setText("BLOK A");
        switch1 = (Switch) view.findViewById(R.id.switch1);
        img1 = (ImageView) view.findViewById(R.id.img1);
        ed1 = (EditText) view.findViewById(R.id.ed1);
        imgbtn1 = (ImageButton) view.findViewById(R.id.imgbtn1);
        ed2 = (EditText) view.findViewById(R.id.ed2);
        imgbtn2 = (ImageButton) view.findViewById(R.id.imgbtn2);

        ed1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgbtn1.setVisibility(View.VISIBLE);
                        ed1.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        ed2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        imgbtn2.setVisibility(View.VISIBLE);
                        ed2.setFocusableInTouchMode(true);
                }
                return false;
            }
        });

        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn1:
                prefManager.setRuanga(ed1.getText().toString());
                ed1.setFocusable(false);
                imgbtn1.setVisibility(View.GONE);
                break;
            case R.id.imgbtn2:
                prefManager.setRuangb(ed2.getText().toString());
                ed2.setFocusable(false);
                imgbtn2.setVisibility(View.GONE);
                break;
        }
    }
}
