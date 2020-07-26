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

public class Give extends AppCompatActivity {
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
                                        a=0;
                                        d=amt;
                                    }
                                    else if(advance==0 && due>0){
                                        d+=amt;
                                    }
                                    else if(advance>0 && advance>=amt){
                                        a=advance-amt;
                                    }
                                    else if(advance>0 && advance<amt){
                                        d=d+amt-a;
                                        a=0;
                                    }
                                    DocumentReference ref=db.collection(name).document(id);
                                    ref.update("advance",a);
                                    ref.update("due",d);
                                    Intent in=new Intent(Give.this,Account.class);
                                    startActivity(in);
                                    Give.this.finish();
                                    Toast.makeText(Give.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(Give.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
        et=findViewById(R.id.give_et);
        bt=findViewById(R.id.give_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fill();
            }
        });
    }
}
