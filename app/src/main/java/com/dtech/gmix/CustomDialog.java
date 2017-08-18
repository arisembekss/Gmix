package com.dtech.gmix;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dtech.gmix.preference.CustomDialogClickListener;
import com.dtech.gmix.preference.PrefManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aris on 17/08/17.
 */

public class CustomDialog  {
    Context context;
    LinearLayout lineRow1;

    PrefManager prefManager;
    SharedPreferences sharedPreferences;
    public static CustomDialogClickListener clickListener;

    public void setClickListener(CustomDialogClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

     Dialog dialogStatus;
    public CustomDialog makeDialog(final Context context, String title, String message, final String tag) {
        prefManager = new PrefManager(context);
         dialogStatus = new Dialog(context);
        dialogStatus.setTitle(title);
        dialogStatus.setContentView(R.layout.custom_dialog_keterangan);
        final List<String> arrayRuang = new ArrayList<>();
        TextView tv = (TextView) dialogStatus.findViewById(R.id.msgDialogKet);
        lineRow1 = (LinearLayout) dialogStatus.findViewById(R.id.linerowdialog);
        //tv.setText(message);
        Button btnadd = (Button) dialogStatus.findViewById(R.id.addBtn);
        if (tag.matches("saldo")) {
            btnadd.setText("Tambah Saldo");
        } else {
            btnadd.setText("Close");
        }
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sruang ;
                if (arrayRuang.size() > 1) {
                    sruang = TextUtils.join(", ", arrayRuang);
                    prefManager.setRuangBloka(sruang);
                    //prefManager.setRuangBloka(TextUtils.join(", ", arrayproses));
                } else if (arrayRuang.size() == 1) {
                    sruang = TextUtils.join("", arrayRuang);
                    prefManager.setRuangBloka(sruang);
                } else {
                    sruang = "";
                    prefManager.setRuangBloka(sruang);
                }
                //prefManager.setRuangBloka(sruang);
                if (clickListener != null) clickListener.onClick(view, sruang);
                dialogStatus.dismiss();
            }
        });

        if (message != null && !message.isEmpty() || message.contains(",")) {
            String nmessage = message.replaceAll("\\s", "");
            String[] messageArray = nmessage.split(",");
            for (int i = 0; i < messageArray.length; i++) {
                //inflateEditRow(context, messageArray[i]);
                arrayRuang.add(messageArray[i]);
                inflateEditRow(context, messageArray[i],arrayRuang);
            }
            //prosesArray(context, arrayRuang);
            //inflateEditRow(context, arrayRuang);
            String struang = TextUtils.join(",", arrayRuang);
            Log.d("arrayRuang ", struang);
        }

        final EditText eddialog = (EditText) dialogStatus.findViewById(R.id.edadddialog);
        ImageView img = (ImageView) dialogStatus.findViewById(R.id.imgadd);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayRuang.add(eddialog.getText().toString());
                //prosesArray(context, arrayRuang);
                inflateEditRow(context, eddialog.getText().toString(), arrayRuang);
                //inflateEditRow(context, arrayRuang);
                eddialog.setText("");
                String struang = TextUtils.join(",", arrayRuang);
                Log.d("arrayRuang ", struang);
            }
        });


        //if (message!=null && !message.isEmpty()) {

            /**/
            //;
        //}
        dialogStatus.show();
        return this;
    }

    public void prosesArray(View v) {
        /*for (int i=0; i<arrayRuang.size();i++) {
            inflateEditRow(context, arrayRuang.get(i));

        }*/
        lineRow1.removeView((View) v.getParent());
    }


    private void inflateEditRow(Context context, final String name, final List<String> arrayproses) {

        prefManager = new PrefManager(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = null;

        View rowView = inflater.inflate(R.layout.items, null);

        final TextView txtdel = (TextView) rowView.findViewById(R.id.textDel);
        txtdel.setText(name.replaceAll("\\s",""));

        ImageButton btnItem = (ImageButton) rowView.findViewById(R.id.imgdel);
        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lineRow1.removeView((View) rowView.getParent());
                Log.d("current array", String.valueOf(arrayproses.size()));
                //prosesArray(rowView);
                String currentText = txtdel.getText().toString();
                arrayproses.remove(currentText);
                String savetopref;
                if (arrayproses.size() > 1) {
                    savetopref = TextUtils.join(", ", arrayproses);
                    prefManager.setRuangBloka(savetopref);
                    //prefManager.setRuangBloka(TextUtils.join(", ", arrayproses));
                } else if (arrayproses.size() == 1) {
                    savetopref = TextUtils.join("", arrayproses);
                    prefManager.setRuangBloka(savetopref);
                } else {
                    prefManager.setRuangBloka("");
                }

                if (clickListener != null) clickListener.onClick(view, TextUtils.join(",", arrayproses));
                dialogStatus.dismiss();

            }
        });
        lineRow1.addView(rowView, lineRow1.getChildCount() - 1);
    }



/*    @Override
    public void onClick(View view) {
        String sruang = TextUtils.join(",", arrayRuang);
        if (clickListener != null) clickListener.onClick(view, sruang);
        dialogStatus.dismiss();
    }*/
}
