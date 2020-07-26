package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
EditText et1,et2,et3,et4;
Button bt;
    ProgressDialog dialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        et1=findViewById(R.id.up_et1);
        et2=findViewById(R.id.up_et2);
        et3=findViewById(R.id.up_et3);
        et4=findViewById(R.id.up_et4);
        bt=findViewById(R.id.up_bt1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(SignUp.this);
                dialog.setTitle("Please wait");
                dialog.setMessage("data is being saved");
                dialog.show();
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("name", et1.getText().toString());
                user.put("pno", et2.getText().toString());
                user.put("password", et3.getText().toString());
                user.put("email", et4.getText().toString());

// Add a new document with a generated ID
                db.collection("MyUsers")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(SignUp.this, "Your data is saved", Toast.LENGTH_SHORT).show();
                                SignUp.this.finish();
                                dialog.cancel();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
