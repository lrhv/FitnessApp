package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;

public class Sign_up3th_Class extends AppCompatActivity {


    ImageView backBtn;
    Button next, login;
    TextView titleText, diseases;

    //Get Data Variables
    TextInputLayout height, weight;
    MaterialCardView selectCard;

    boolean[] selectedDiseases;
    ArrayList<Integer> diseasesList = new ArrayList<>();
    String[] diseasesArray = {"Hastalık1","Hastalık2","Hastalık3","Hastalık4","Hastalık5","Hastalık6","Hastalık7","Hastalık8"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3th_class);

        //Hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);


        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        //Initialize all views
        selectCard = findViewById(R.id.selectCard);
        diseases = findViewById(R.id.diseases);
        selectedDiseases = new boolean[diseasesArray.length];

        selectCard.setOnClickListener(v -> {
            showDiseasesDialog();
        });

    }


    public void callBackScreen(View view){

        Intent intent = new Intent(getApplicationContext(), Sign_up2nd_Class.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callNextSignupScreen(View view){

        if (!validateWeight() | !validateHeight()) {
            return;
        }

        Intent intent = new Intent(getApplicationContext(),Sign_up4th_Class.class);

        //Add Transition
        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Sign_up3th_Class.this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callLoginScreen(View view){

        Intent intent = new Intent(getApplicationContext(),Login.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(login,"transition_login");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
        startActivity(intent,options.toBundle());
    }

    private boolean validateWeight(){
        String val1 = weight.getEditText().getText().toString().trim();
        int val = Integer.parseInt(val1.replaceAll("[\\D]", "")); //-->string to int

        if(val<40) {
            weight.setError("Enter a valid weight!");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateHeight(){
        String val1 = height.getEditText().getText().toString().trim();
        int val = Integer.parseInt(val1.replaceAll("[\\D]", "")); //-->string to int

        if (val<80){
            height.setError("Enter a valid height!");
            return false;
        }
        else {
            return true;
        }
    }

    private void showDiseasesDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Diseases!");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(diseasesArray, selectedDiseases, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    diseasesList.add(which);
                    Collections.sort(diseasesList);
                }else{
                    diseasesList.remove(which);
                }
            }
        });
        builder.setPositiveButton("Clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //clearing all selected courses on clear all click
                for (int i=0; i < selectedDiseases.length; i++){

                    selectedDiseases[i] = false;

                    diseasesList.clear();

                    diseases.setText("");
                }
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j=0; j<diseasesList.size(); j++){

                            stringBuilder.append(diseasesArray[diseasesList.get(j)]);

                            if (j !=diseasesList.size()-1){

                                stringBuilder.append(", ");
                            }
                        }

                        diseases.setText(stringBuilder.toString());
                    }
                });



        /*builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        */


        builder.show();
    }

}