package com.example.user.mysimon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AlertDialog.OnClickListener {
    private AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.butStart).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            dialog1 = new AlertDialog.Builder(this).create();
            dialog1.setTitle("Start Game!!");
            dialog1.setMessage(" Do you want start game????");
            dialog1.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", this);
            dialog1.setButton(DialogInterface.BUTTON_NEGATIVE, "No", this);
            dialog1.show();



    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_NEGATIVE:
                break;
            case DialogInterface.BUTTON_POSITIVE:
                Intent intent=new Intent(this,Simon.class);
                startActivity(intent);
                break;

        }

    }
}
