package com.dtech.gmix;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;


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
                //context.startActivity(new Intent(context, AddSaldoActivity.class));

            }
        });

        if (message != null && !message.isEmpty() && message.contains(",")) {
            String[] messageArray = message.split(",");
            for (int i = 0; i < messageArray.length; i++) {
                inflateEditRow(context, messageArray[i]);
                arrayRuang.add(messageArray[i]);

            }
            String struang = TextUtils.join(",", arrayRuang);
            Log.d("arrayRuang ", struang);
        }

        final EditText eddialog = (EditText) dialogStatus.findViewById(R.id.edadddialog);
        ImageView img = (ImageView) dialogStatus.findViewById(R.id.imgadd);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflateEditRow(context, eddialog.getText().toString());
                arrayRuang.add(eddialog.getText().toString());
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
    }



    private void inflateEditRow(Context context, String name) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.items, null);
        ImageButton btnItem = (ImageButton) rowView.findViewById(R.id.imgdel);
        TextView txtdel = (TextView) rowView.findViewById(R.id.textDel);

        txtdel.setText(name);
        lineRow1.addView(rowView, lineRow1.getChildCount() - 1);


    }

}
