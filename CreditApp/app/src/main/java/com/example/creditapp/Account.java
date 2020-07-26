package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Account extends AppCompatActivity {
TextView tv1,tv2,tv3;
Button bt1,bt2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
void fill(){
    SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
    String name=sp.getString("name","NA");
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
                                tv2.setText(document.getData().get("due").toString());
                                tv3.setText(document.getData().get("advance").toString());
                            }
                        }
                    } else {
                        Toast.makeText(Account.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}
    protected void onStart() {
        super.onStart();
        fill();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        tv1=findViewById(R.id.account_tv1);
        tv2=findViewById(R.id.account_tv2);
        tv3=findViewById(R.id.account_tv3);
        bt1=findViewById(R.id.account_bt1);
        bt2=findViewById(R.id.account_bt2);
        SharedPreferences sp1=getSharedPreferences("mysp1",MODE_PRIVATE);
        String name1=sp1.getString("name1","NA");
        tv1.setText(name1+"'s Account");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Account.this,Give.class);
                startActivity(in);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Account.this,Accept.class);
                startActivity(in);
            }
        });
    }
}
