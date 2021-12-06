package com.example.school;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class IntroActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_intro);
        //this will bind your MainActivity.class file with activity_main.

        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        final FirebaseUser[] user = {mAuth.getCurrentUser()};
        if (user[0]!=null){
            checkUserAccessLevel(mAuth.getCurrentUser().getUid());


        }
        else{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }


        int TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                finish();
            }
        }, TIME_OUT);
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df=fStore.collection("users").document(uid);
        Log.d("Document","referance is ");

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess"+documentSnapshot.getData());
                //checking the user accesslevel of user
                String isRole = (String) documentSnapshot.getString("IsRole");
                Log.d(TAG,"Role is:"+isRole);
                if (documentSnapshot.getString("IsRole").equals("Admin")) {
                    Toast.makeText(getApplicationContext(), "Admin Login Successful..", Toast.LENGTH_SHORT).show();
                    Log.d("SignIn", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                } else if (documentSnapshot.getString("IsRole").equals("Student")) {
                    Toast.makeText(getApplicationContext(), "Student Login Successful..", Toast.LENGTH_SHORT).show();
                    Log.d("SignIn", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user);
                    Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Teacher Login Successful..", Toast.LENGTH_SHORT).show();
                    Log.d("SignIn", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    System.out.println(user);
                    Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
    }