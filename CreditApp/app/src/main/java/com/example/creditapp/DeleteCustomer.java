package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DeleteCustomer extends AppCompatActivity {
LinearLayout ll;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
void fill(){
    SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
    final String name=sp.getString("name","NA");
    db.collection(name)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            final String name1=document.getData().get("name").toString();
                            TextView tv1=new TextView(DeleteCustomer.this);
                            tv1.setText(name1);
                            tv1.setTextSize(30);
                            tv1.setPadding(0,2,0,0);
                            final String id=document.getId();
                            tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final DocumentReference ref=db.collection(name).document(id);
                                    AlertDialog.Builder a=new AlertDialog.Builder(DeleteCustomer.this);
                                    a.setTitle("Alert");
                                    a.setMessage("Would you like to delete the contact");
                                    a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ref.delete();
                                            Intent in=new Intent(DeleteCustomer.this,Nav.class);
                                            startActivity(in);
                                        }
                                    });
                                    a.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(DeleteCustomer.this, "Opening page", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    a.show();
                                }
                            });
                            ll.addView(tv1);
                        }
                    } else {
                        Toast.makeText(DeleteCustomer.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
}

    @Override
    protected void onStart() {
    fill();
    super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);
        ll=findViewById(R.id.Delete_ll);
    }
}
