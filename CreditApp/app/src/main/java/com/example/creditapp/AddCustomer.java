package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

public class AddCustomer extends AppCompatActivity {
EditText et1,et2,et3;
Button bt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        et1=findViewById(R.id.add_et1);
        et2=findViewById(R.id.add_et2);
        et3=findViewById(R.id.add_et3);
        bt=findViewById(R.id.add_bt);
        final SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        final String name=sp.getString("name","NA");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(AddCustomer.this);
                dialog.setTitle("Please wait");
                dialog.setMessage("data is being saved");
                dialog.show();
                // Create a new user with a first and last name
                Map<String, Object> customers = new HashMap<>();
                customers.put("name", et1.getText().toString());
                customers.put("pno", et2.getText().toString());
                customers.put("email", et3.getText().toString());
                customers.put("advance", "0");
                customers.put("due", "0");

// Add a new document with a generated ID
                db.collection(name)
                        .add(customers)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddCustomer.this, "Customers's data is saved", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                AddCustomer.this.finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddCustomer.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
