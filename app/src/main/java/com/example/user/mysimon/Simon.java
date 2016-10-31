package com.example.user.mysimon;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;


public class Simon extends AppCompatActivity implements AlertDialog.OnClickListener {

    ArrayList<Integer> Application_Choice = new ArrayList<Integer>();
    ArrayList<Integer> User_Choice = new ArrayList<Integer>();
    Context context;
    TextView steppic;
    private int Num;
    public int Step = -1;
    public int App_Step = -1;
    public int User_Step = -1;
    ArrayList<Button> buttons = new ArrayList<Button>();
    Button but1, but2, but3, but4;
    Button button_light;
    final Timer T = new Timer();
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        but1 = (Button) findViewById(R.id.btn_1);
        but2 = (Button) findViewById(R.id.btn_2);
        but3 = (Button) findViewById(R.id.btn_3);
        but4 = (Button) findViewById(R.id.btn_4);
        steppic= (TextView) findViewById(R.id.step);

        context = this;
        App_Choose_Num();




    }




    private void App_Choose_Num() {
        Step++;
        steppic.setText(String.valueOf(Step));
        Random r = new Random();
        int Num = r.nextInt(5 - 1) + 1;
        Application_Choice.add(Num);
        LightButton(Application_Choice.get(0));
    }


    private void LightButton(int SelectedNum) {

        App_Step++;

        if (SelectedNum == 1) button_light = but1;
        if (SelectedNum == 2) button_light = but2;
        if (SelectedNum == 3) button_light = but3;
        if (SelectedNum == 4) button_light = but4;


        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        final Button btn = button_light;
        btn.startAnimation(animation);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //button_light.setAlpha(1);
                btn.clearAnimation();
                if (App_Step < Application_Choice.size() - 1) {
                    LightButton(Application_Choice.get(App_Step + 1));
                } else {
                    App_Step = -1;

                }
            }
        }, 1000);


    }

    public void Convert_View_Num(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Num = 1;
                break;
            case R.id.btn_2:
                Num = 2;
                break;
            case R.id.btn_3:
                Num = 3;
                break;
            case R.id.btn_4:
                Num = 4;
                break;
        }
        User_Step++;
        User_Choice.add(Num);
        if (User_Choice.size() == Application_Choice.size()) Compare_Choices();


    }

    private void Compare_Choices() {

        int correct = 1;

        for (int i = 0; i <= Step; i++) {

            if (Application_Choice.get(i) != User_Choice.get(i)) {
                correct = 0;
                Toast.makeText(Simon.this, "Step="+ Step, Toast.LENGTH_SHORT).show();
            }

        }

        if (correct == 0) {
            Game_End();

        } else {

            User_Choice = new ArrayList<Integer>();
            App_Choose_Num();


        }


    }


    public void Game_End() {
        dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("GAME-OVER!!!!");
        dialog.setMessage(" Do you want new game????");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", this);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", this);
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case DialogInterface.BUTTON_NEGATIVE:
                    Intent in = new Intent(this, MainActivity.class);
                    startActivity(in);

                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    Application_Choice.clear();
                    User_Choice.clear();

                    break;
            }

    }

    @Override
    protected void onResume() {
        super.onResume();
        App_Choose_Num();


    }
}