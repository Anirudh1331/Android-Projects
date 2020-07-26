package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText et1,et2;
Button bt1,bt2,bt3;
LinearLayout ll;
ProgressDialog dialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        bt1=findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        bt3=findViewById(R.id.bt3);
        ll=findViewById(R.id.ll);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Create a new user with a first and last name
                Map<String, Object> contacts = new HashMap<>();
                contacts.put("name", et1.getText().toString());
                contacts.put("pno", et2.getText().toString());

// Add a new document with a generated ID
                db.collection("contacts")
                        .add(contacts)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this, "Contacts Added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(MainActivity.this);
                dialog.setTitle("Please wait");
                dialog.setMessage("Your data is being fetched");
                dialog.show();
                db.collection("contacts")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    ll.removeAllViews();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        TextView tv1=new TextView(MainActivity.this);
                                        tv1.setText(document.getData().get("name").toString());
                                        TextView tv2=new TextView(MainActivity.this);
                                        tv2.setText(document.getData().get("pno").toString());
                                        tv1.setTextSize(32);
                                        tv2.setTextSize(14);
                                        tv2.setPadding(15,0,0,0);
                                        ll.addView(tv1);
                                        ll.addView(tv2);
                                    }
                                    dialog.cancel();
                                } else {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db1=openOrCreateDatabase("contacts1",MODE_PRIVATE,null);
                String name=et1.getText().toString();
                String pno=et2.getText().toString();
                db1.execSQL("create table if not exists contacts1(name varchar,pno varchar)");
                String query="insert into contacts1 values('"+name+"','"+pno+"')";
                db1.execSQL(query);
                db1.close();
                Toast.makeText(MainActivity.this, "Your data is saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=this.getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_m1)
        {
            SQLiteDatabase db1=openOrCreateDatabase("contacts1",MODE_PRIVATE,null);
            db1.execSQL("create table if not exists contacts1(name varchar,pno varchar)");
            String query="select * from contacts1 ";
            Cursor c=db1.rawQuery(query,null);
            if(c.moveToFirst()){
                do{
                    // Create a new user with a first and last name
                    Map<String, Object> contacts = new HashMap<>();
                    contacts.put("name",c.getString(0));
                    contacts.put("pno", c.getString(1));

// Add a new document with a generated ID
                    db.collection("contacts")
                            .add(contacts)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(MainActivity.this, "Contacts Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                }while (c.moveToNext());
            }
            else
            {
                Toast.makeText(this, "sorry there is no contact to transfer", Toast.LENGTH_SHORT).show();
            }
        }
        else if(R.id.menu_m2==item.getItemId())
        {
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please wait");
            dialog.setMessage("Your data is being fetched");
            dialog.show();
            db.collection("contacts")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ll.removeAllViews();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    TextView tv1=new TextView(MainActivity.this);
                                    tv1.setText(document.getData().get("name").toString());
                                    TextView tv2=new TextView(MainActivity.this);
                                    tv2.setText(document.getData().get("pno").toString());
                                    tv1.setTextSize(32);
                                    tv2.setTextSize(14);
                                    tv2.setPadding(15,0,0,0);
                                    SQLiteDatabase db1=openOrCreateDatabase("contacts1",MODE_PRIVATE,null);
                                    String name=tv1.getText().toString();
                                    String pno=tv2.getText().toString();
                                    db1.execSQL("create table if not exists contacts1(name varchar,pno varchar)");
                                    String query="insert into contacts1 values('"+name+"','"+pno+"')";
                                    db1.execSQL(query);
                                    db1.close();
                                }
                                dialog.cancel();
                            } else {
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else if(R.id.menu_m3==item.getItemId())
        {
            Intent in=new Intent(MainActivity.this,Show.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}
