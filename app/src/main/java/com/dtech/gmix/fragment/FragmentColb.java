package com.dtech.gmix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dtech.gmix.R;

/**
 * Created by aris on 21/08/17.
 */

public class FragmentColb extends Fragment {

    View view;

    CardView cardroot;
    RelativeLayout rel1;
    TextView tblok;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_cola, container, false);
        initUi();
        return view;
    }

    private void initUi() {
        cardroot = (CardView) view.findViewById(R.id.cardroot);
        rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
        rel1.setBackgroundResource(R.color.blue2);
        tblok = (TextView) view.findViewById(R.id.textblok);
        tblok.setText("BLOK B");
    }
}
