package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class IntroActivity extends AppCompatActivity {
    private static int TIME_OUT = 3000;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.


        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        final FirebaseUser[] user = {mAuth.getCurrentUser()};

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user[0]!=null){
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, TIME_OUT);
    }
    }