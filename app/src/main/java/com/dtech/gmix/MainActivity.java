package com.dtech.gmix;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dtech.gmix.preference.PrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button blogin;
    EditText eduser, edpass;

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefManager = new PrefManager(this);

        //3fc00309d48d2da1bd61a00ad6bff547 -> 2 block
        //e9f60718e67952c7830fdb6f6c62d3ad -> 4 block

        eduser = (EditText) findViewById(R.id.eduser);
        edpass = (EditText) findViewById(R.id.edpass);
        blogin = (Button) findViewById(R.id.btnlogin);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = eduser.getText().toString();
                String password = edpass.getText().toString();

                if (username.matches("user") && password.matches("user123")) {
                    prefManager.setLogin("Logged in");
                    Intent intent = new Intent(MainActivity.this, ControllActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    final AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Halaman Login");
                    builder.setMessage("Username atau password salah");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //alertDialog.dismiss();
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.show();

                }
            }
        });
    }


}
