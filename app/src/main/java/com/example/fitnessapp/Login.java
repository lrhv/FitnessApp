package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView backBtn;
    Button new_user, forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        backBtn = findViewById(R.id.backarrow);
        new_user = findViewById(R.id.new_user);
        forgetPassword = findViewById(R.id.forgetPassword);

    }

    public void callForgetPassword(View view){
        Intent intent = new Intent(getApplicationContext(),ForgetPassword.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(forgetPassword,"transition_forget");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,options.toBundle());
    }

    public void callSignScreen(View view){

        Intent intent = new Intent(getApplicationContext(),SignUp.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(new_user,"transition_sign");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,options.toBundle());
    }



    public void callBackScreen(View view){

        Intent intent = new Intent(getApplicationContext(), Startup.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
        startActivity(intent, options.toBundle());

    }
}