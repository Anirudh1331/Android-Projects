package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Accept extends AppCompatActivity {
EditText et;
Button bt;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    void fill(){
        final int amt=Integer.parseInt(et.getText().toString());
        SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        final String name=sp.getString("name","NA");
        SharedPreferences sp1=getSharedPreferences("mysp1",MODE_PRIVATE);
        final String name1=sp1.getString("name1","NA");
        db.collection(name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nm=document.getData().get("name").toString();
                                if(nm.equals(name1)){
                                    int due=Integer.parseInt(document.getData().get("due").toString());
                                    int advance=Integer.parseInt(document.getData().get("advance").toString());
                                    String id=document.getId();
                                    int a=advance,d=due;
                                    if(due==0 && advance==0){
                                        a=amt;
                                        d=0;
                                    }
                                    else if(advance>0 && due==0){
                                        a+=amt;
                                    }
                                    else if(due>0 && due>=amt){
                                        d=due-amt;
                                    }
                                    else if(due>0 && due<amt){
                                        a=a+amt-d;
                                        d=0;
                                    }
                                    DocumentReference ref=db.collection(name).document(id);
                                    ref.update("advance",a);
                                    ref.update("due",d);
                                    Intent in=new Intent(Accept.this,Account.class);
                                    startActivity(in);
                                    Accept.this.finish();
                                    Toast.makeText(Accept.this, "Payment Accepted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(Accept.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);
        et=findViewById(R.id.accept_et);
        bt=findViewById(R.id.accept_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fill();
            }
        });
    }
}
