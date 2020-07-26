package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
EditText et2,et3,et4;
Button bt;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
        et2=findViewById(R.id.signUp_email);
        et3=findViewById(R.id.Pass_SignUp);
        et4=findViewById(R.id.ConPass_SignUp);
        bt=findViewById(R.id.button_SignUp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et2.getText().toString();
                String password = et3.getText().toString();
                String check = et4.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(check)) {
                    if (password.equals(check)) {
                        dialog=new ProgressDialog(SignUp.this);
                        dialog.setTitle("Please Wait");
                        dialog.setMessage("Your data is being saved");
                        dialog.show();
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            dialog.cancel();
                                            Toast.makeText(SignUp.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                                            Intent in=new Intent(SignUp.this,MainActivity.class);
                                            startActivity(in);
                                        } else {
                                            dialog.cancel();
                                            Toast.makeText(SignUp.this, "Email Id already exists or password length is small", Toast.LENGTH_SHORT).show();
                                        }

                                        // ...
                                    }
                                });
                    }
                    else{
                        Toast.makeText(SignUp.this, "Passwords don't match!! Please Try again", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignUp.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}