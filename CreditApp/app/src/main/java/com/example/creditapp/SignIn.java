package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignIn extends AppCompatActivity {
EditText et1,et2;
Button bt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        et1=findViewById(R.id.in_et1);
        et2=findViewById(R.id.in_et2);
        bt=findViewById(R.id.in_bt);
        SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        if(sp.getString("name","NA").equals("NA")){

        }
        else
        {
            Intent in1=new Intent(SignIn.this,Nav.class);
            startActivity(in1);
            this.finish();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(SignIn.this);
                dialog.setTitle("Please wait");
                dialog.setMessage("data is being fetched");
                dialog.show();
                db.collection("MyUsers")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String name=document.getData().get("name").toString();
                                        String pno=document.getData().get("pno").toString();
                                        String pass=document.getData().get("password").toString();
                                        if(pass.equals(et2.getText().toString()) && pno.equals(et1.getText().toString())){
                                            SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
                                            SharedPreferences.Editor ed=sp.edit();
                                            ed.putString("name",name);
                                            ed.commit();
                                            Intent in1=new Intent(SignIn.this,Nav.class);
                                            startActivity(in1);
                                            SignIn.this.finish();
                                        }
                                    }
                                    dialog.cancel();
                                } else {
                                    Toast.makeText(SignIn.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
