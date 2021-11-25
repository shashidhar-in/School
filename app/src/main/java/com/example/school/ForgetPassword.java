package com.example.school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    EditText mEmailAddress;
    Button mReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_forget_password);
        mEmailAddress = findViewById(R.id.inputEmail);
        mReset = findViewById(R.id.Forget_btn);
        mAuth = FirebaseAuth.getInstance();

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mEmailAddress.getText().toString().isEmpty()) mEmailAddress.setError("Please enter an email address");
                else {
                    mAuth.sendPasswordResetEmail(mEmailAddress.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Password reset email successfully sent",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgetPassword.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                if(task.getException().getMessage().contains("There is no user record")) mEmailAddress.setError("Please enter an existing email address");
                                if(task.getException().getMessage().contains("email address is badly formatted")) mEmailAddress.setError("Please enter a valid email address");

                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}