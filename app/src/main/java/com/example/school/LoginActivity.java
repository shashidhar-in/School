package com.example.school;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail;
    EditText mPassword;
    TextView mForgotPassword;
    String email;
    String password;
    Button mLogin;

    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();


        mEmail = (EditText) findViewById(R.id.inputEmail);
        mPassword = (EditText) findViewById(R.id.inputPassword);
        mForgotPassword = (TextView) findViewById(R.id.forgot_password);
        mLogin=(Button)findViewById(R.id.Login_btn);







        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                if(!mEmail.getText().toString().isEmpty())
                {
                    if(!mPassword.getText().toString().isEmpty())
                    {
                        signInUser();
                    }
                    else mPassword.setError("Please enter a password");
                }
                else
                {
                    mEmail.setError("Please enter your email address");
                    if(mPassword.getText().toString().isEmpty())
                    {
                        mPassword.setError("Please enter a password");
                    }

                }


            }
        });

    }




    private void signInUser()
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            // Sign in success, update UI with the signed-in user's information





                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignIn", "signInWithEmail:failure", task.getException());
                            if(task.getException().getMessage().contains("password"))
                            {
                                mPassword.setError("The password is invalid");
                            }
                            Toast.makeText(LoginActivity.this, "Authentication failed : "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }

                        // ...
                    }


                });
    }

}