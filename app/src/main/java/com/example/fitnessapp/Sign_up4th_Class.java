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
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class Sign_up4th_Class extends AppCompatActivity {


    //variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up4th_class);

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.signup_phone_number);


    }

    public void callVeriyOTPScreen(View view){


        if (!validatePhoneNumber()){
            return;
        }


        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");
        String _weight = getIntent().getStringExtra("weight");
        String _height = getIntent().getStringExtra("height");
        String _diseases = getIntent().getStringExtra("diseases");

        String _gerUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_gerUserEnteredPhoneNumber;

        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);


        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("weight", _weight);
        intent.putExtra("height", _height);
        intent.putExtra("diseases", _diseases);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(next, "transition_OTP_screen");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,options.toBundle());


    }

    public void callBackScreen(View view){

        Intent intent = new Intent(getApplicationContext(), Sign_up3th_Class.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callNextSignupScreen(View view){

        Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Sign_up4th_Class.this, pairs);
        startActivity(intent, options.toBundle());

    }

    private boolean validatePhoneNumber(){
        String val = phoneNumber.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            phoneNumber.setError("Field can not be empty");
            return false;
        }
        else{
            return true;
        }
    }

}