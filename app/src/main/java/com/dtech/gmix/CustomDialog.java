package com.dtech.gmix;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by aris on 17/08/17.
 */

public class CustomDialog {
    Context context;
    LinearLayout lineRow1;
    public void makeDialog(final Context context, String title, String message, final String tag) {
        final Dialog dialogStatus = new Dialog(context);
        dialogStatus.setTitle(title);
        dialogStatus.setContentView(R.layout.custom_dialog_keterangan);
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
                //context.startActivity(new Intent(context, AddSaldoActivity.class));

            }
        });

        if (message!=null && !message.isEmpty()) {
            String[] messageArray = message.split(",");
            for (int i = 0; i<messageArray.length; i++) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.items, null);
                Button btnItem = (Button) rowView.findViewById(R.id.btnitem);


                btnItem.setText(messageArray[i]);



                lineRow1.addView(rowView, lineRow1.getChildCount() - 1);
            }
            /**/
            //inflateEditRow(message);
        }
        dialogStatus.show();
    }

    private void inflateEditRow(String name) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.items, null);
        Button btnItem = (Button) rowView.findViewById(R.id.btnitem);

        if (name != null && !name.isEmpty()) {
            btnItem.setText(name);
        } else {

        }



        lineRow1.addView(rowView, lineRow1.getChildCount() - 1);
    }

}
