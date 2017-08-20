package com.dtech.gmix;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dtech.gmix.preference.CustomDialogClickListener;
import com.dtech.gmix.preference.PrefManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aris on 17/08/17.
 */

public class CustomDialog  {

    LinearLayout lineRow1;

    PrefManager prefManager;

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

        lineRow1 = (LinearLayout) dialogStatus.findViewById(R.id.linerowdialog);

        Button btnadd = (Button) dialogStatus.findViewById(R.id.addBtn);
        btnadd.setText("Done");
        /*if (tag.matches("saldo")) {
            btnadd.setText("Tambah Saldo");
        } else {
            btnadd.setText("Done");
        }*/
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sruang ;
                if (arrayRuang.size() > 1) {
                    sruang = TextUtils.join(", ", arrayRuang);
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka(sruang);
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb(sruang);
                    }
                } else if (arrayRuang.size() == 1) {
                    sruang = TextUtils.join("", arrayRuang);
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka(sruang);
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb(sruang);
                    }
                    //prefManager.setRuangBloka(sruang);
                } else {
                    sruang = "";
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka(sruang);
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb(sruang);
                    }
                    //prefManager.setRuangBloka(sruang);
                }

                if (clickListener != null) clickListener.onClick(view, sruang, tag);
                dialogStatus.dismiss();
            }
        });

        if (message != null && !message.isEmpty() || message.contains(",")) {
            String nmessage = message.replaceAll(",\\s", ",");
            String[] messageArray = nmessage.split(",");
            for (int i = 0; i < messageArray.length; i++) {

                arrayRuang.add(messageArray[i]);
                inflateEditRow(context, messageArray[i],arrayRuang, tag);
            }

            String struang = TextUtils.join(",", arrayRuang);
            Log.d("arrayRuang ", struang);
        }

        final EditText eddialog = (EditText) dialogStatus.findViewById(R.id.edadddialog);
        ImageView img = (ImageView) dialogStatus.findViewById(R.id.imgadd);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayRuang.add(eddialog.getText().toString());

                inflateEditRow(context, eddialog.getText().toString(), arrayRuang, tag);

                eddialog.setText("");
                String struang = TextUtils.join(",", arrayRuang);
                Log.d("arrayRuang ", struang);
            }
        });

        dialogStatus.show();
        return this;
    }

    private void inflateEditRow(Context context, final String name, final List<String> arrayproses, final String tag) {

        prefManager = new PrefManager(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.items, null);

        final TextView txtdel = (TextView) rowView.findViewById(R.id.textDel);
        txtdel.setText(name/*.replaceAll("\\s","")*/);

        ImageButton btnItem = (ImageButton) rowView.findViewById(R.id.imgdel);
        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("current array", String.valueOf(arrayproses.size()));

                String currentText = txtdel.getText().toString();
                arrayproses.remove(currentText);
                String savetopref;
                if (arrayproses.size() > 1) {
                    savetopref = TextUtils.join(", ", arrayproses);
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka(savetopref);
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb(savetopref);
                    }
                    //prefManager.setRuangBloka(savetopref);
                } else if (arrayproses.size() == 1) {
                    savetopref = TextUtils.join("", arrayproses);
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka(savetopref);
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb(savetopref);
                    }
                    //prefManager.setRuangBloka(savetopref);
                } else {
                    if (tag.matches("1")) {
                        prefManager.setRuangBloka("");
                    }
                    if (tag.matches("2")) {
                        prefManager.setRuangBlokb("");
                    }
                    //prefManager.setRuangBloka("");
                }

                if (clickListener != null) clickListener.onClick(view, TextUtils.join(",", arrayproses), tag);
                dialogStatus.dismiss();

            }
        });
        lineRow1.addView(rowView, lineRow1.getChildCount() - 1);
    }

}
