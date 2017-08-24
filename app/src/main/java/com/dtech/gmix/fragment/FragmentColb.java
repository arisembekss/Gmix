package com.dtech.gmix.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
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

public class FragmentColb extends Fragment implements View.OnClickListener {

    View view;

    CardView cardroot;
    RelativeLayout rel1, lay1, lay2;
    TextView tblok;
    Switch switch1;
    ImageView img1;
    EditText ed1, ed2;
    ImageButton imgbtn1, imgbtn2;

    String ruanga, ruangb;

    PrefManager prefManager;
    SharedPreferences sharedPreferences;
    DatabaseReference myRef;
    FirebaseDatabase database;
    InputMethodManager imm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_cola, container, false);

        prefManager = new PrefManager(getActivity());
        sharedPreferences = getActivity().getSharedPreferences(Config.PREF_NAME, Config.PRIVATE_MODE);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Config.REF_DB+Config.REF_2);
        imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        initUi();
        initRealtimeDbase();

        return view;
    }

    private void initRealtimeDbase() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bloka = String.valueOf(dataSnapshot.getValue());
                Log.d("Value blok-b ", bloka);
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
        ruanga = (sharedPreferences.getString(Config.RUANGA3, ""));//TODO change preference
        ruangb = (sharedPreferences.getString(Config.RUANGA4, ""));//TODO change preference
        cardroot = (CardView) view.findViewById(R.id.cardroot);
        rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
        rel1.setBackgroundResource(R.color.blue2);//TODO change bgcolor
        lay1 = (RelativeLayout) view.findViewById(R.id.lay1);
        lay2 = (RelativeLayout) view.findViewById(R.id.lay2);
        lay1.setBackgroundResource(R.color.lime2);
        lay2.setBackgroundResource(R.color.lime1);

        tblok = (TextView) view.findViewById(R.id.textblok);
        tblok.setText("BLOK B");//TODO change blok text
        switch1 = (Switch) view.findViewById(R.id.switch1);
        img1 = (ImageView) view.findViewById(R.id.img1);
        ed1 = (EditText) view.findViewById(R.id.ed1);
        if (ruanga == "") {
            ed1.setText("");
            ed1.setHint("Edit Lokasi");
        } else {
            ed1.setText(ruanga);
        }

        imgbtn1 = (ImageButton) view.findViewById(R.id.imgbtn1);
        ed2 = (EditText) view.findViewById(R.id.ed2);
        if (ruangb == "") {
            ed2.setText("");
            ed2.setHint("Edit Lokasi");
        } else {
            ed2.setText(ruangb);
        }
        imgbtn2 = (ImageButton) view.findViewById(R.id.imgbtn2);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    myRef.setValue(1);
                    //prefManager.setDigitalaBloka("1");
                    img1.setImageResource(R.drawable.energy_on);

                } else {
                    myRef.setValue(0);
                    //prefManager.setDigitalaBloka("0");
                    img1.setImageResource(R.drawable.energy_off);
                }
            }
        });

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
                prefManager.setRuangc(ed1.getText().toString());//TODO change ruang preference
                ed1.setFocusable(false);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                imgbtn1.setVisibility(View.GONE);
                break;
            case R.id.imgbtn2:
                prefManager.setRuangd(ed2.getText().toString());//TODO change ruang preference
                ed2.setFocusable(false);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                imgbtn2.setVisibility(View.GONE);
                break;
        }
    }
}
