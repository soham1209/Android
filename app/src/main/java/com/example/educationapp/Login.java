package com.example.educationapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {


    EditText mEmail,mPassword;
    Button mLoginButton;
    TextView mCreateButton;
    ProgressBar progressBar;

    FirebaseAuth faAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginButton=findViewById(R.id.loginbtn);
        mCreateButton=findViewById(R.id.cretext);
        progressBar=findViewById(R.id.progbar);


        faAuth= FirebaseAuth.getInstance();
        {
            mCreateButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Register.class)));
        }


        mLoginButton.setOnClickListener(v -> {


            String email=mEmail.getText().toString();
            String password=mPassword.getText().toString();




            if (TextUtils.isEmpty(email))
            {
                mEmail.setError("Email is Required");

            } else if (TextUtils.isEmpty(password))
            {
                mPassword.setError("Password is Required");

            }

            else if(password.length()<6)
            {
                mPassword.setError("password must be greater than 6");

            }
            //progressBar.setVisibility(View.VISIBLE);




            faAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {




                                startActivity(new Intent(Login.this, MainActivity.class));
                                Log.d("djcode", "signInWithEmail:success");


                                Toast.makeText(Login.this, "Login Success Full", Toast.LENGTH_SHORT).show();

                            } else {

                                Log.d("djcode", "signInWithEmail:failure", task.getException());
                                //Toast.makeText(this, "Authentication failed.",
                                //Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });


           /*faAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful())

                {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    Toast.makeText(getApplicationContext(),  "Login Successful", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Error"+ Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });*/
        });
    }
}
