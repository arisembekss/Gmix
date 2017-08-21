package com.dtech.gmix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dtech.gmix.R;
import com.dtech.gmix.preference.Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by aris on 21/08/17.
 */

public class FragmentColb extends Fragment {

    View view;

    CardView cardroot;
    RelativeLayout rel1;
    TextView tblok;
    Switch switch1;
    ImageView img1;

    DatabaseReference myRef;
    FirebaseDatabase database;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_cola, container, false);

        database = FirebaseDatabase.getInstance();
        initUi();
        initRealtimeDbase();

        return view;
    }

    private void initRealtimeDbase() {
        myRef = database.getReference(Config.REF_DB+Config.REF_2);
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
        cardroot = (CardView) view.findViewById(R.id.cardroot);
        rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
        rel1.setBackgroundResource(R.color.blue2);
        tblok = (TextView) view.findViewById(R.id.textblok);
        tblok.setText("BLOK B");
        switch1 = (Switch) view.findViewById(R.id.switch1);
        img1 = (ImageView) view.findViewById(R.id.img1);
    }
}
