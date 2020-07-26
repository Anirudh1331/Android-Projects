package com.example.creditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Nav extends AppCompatActivity {
TextView tv;
LinearLayout ll;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

void fill(){
    SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
    String name=sp.getString("name","NA");
    ll.removeAllViews();
    db.collection(name)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            final String name1=document.getData().get("name").toString();
                            TextView tv1=new TextView(Nav.this);
                            tv1.setText(name1);
                            tv1.setTextSize(30);
                            tv1.setPadding(0,2,0,0);
                            tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    SharedPreferences sp1=getSharedPreferences("mysp1",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp1.edit();
                                    ed.putString("name1",name1);
                                    ed.commit();
                                    Intent in=new Intent(Nav.this,Account.class);
                                    startActivity(in);
                                }
                            });
                            ll.addView(tv1);
                        }
                    } else {
                        Toast.makeText(Nav.this, "Error", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_nav);
        SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
        tv=findViewById(R.id.tv);
        ll=findViewById(R.id.ll);
        String name=sp.getString("name","NA");
        tv.setText(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=this.getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.main_m1){
            Intent in=new Intent(Nav.this,AddCustomer.class);
            startActivity(in);
        }
        else if(item.getItemId()==R.id.main_m2){
            Intent in=new Intent(Nav.this,DeleteCustomer.class);
            startActivity(in);
        }
        else if(item.getItemId()==R.id.main_m3){
            SharedPreferences sp=getSharedPreferences("mysp",MODE_PRIVATE);
            SharedPreferences.Editor ed=sp.edit();
            ed.putString("name","NA");
            ed.commit();
            Intent in=new Intent(Nav.this,MainActivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

}
