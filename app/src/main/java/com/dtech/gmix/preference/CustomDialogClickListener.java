package com.dtech.gmix.preference;

import android.view.View;

/**
 * Created by aris on 18/08/17.
 */

public interface CustomDialogClickListener {
    //public interface ItemClickListener {
        //void onClick(View view, int position);
        void onClick(View view, String data, String tag);
    //}
}
