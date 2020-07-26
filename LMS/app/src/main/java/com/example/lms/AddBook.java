package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {
EditText et1,et2,et3;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        et1=findViewById(R.id.Add_et1);
        et2=findViewById(R.id.Add_et2);
        et3=findViewById(R.id.Add_et3);
        bt=findViewById(R.id.Add_Save);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
                String Book_Name=et1.getText().toString();
                String Book_Code=et2.getText().toString();
                String Return_Date=et3.getText().toString();
                db.execSQL("create table if not exists Books(Book_Name varchar,Book_Code varchar,Return_Date varchar)");
                String query="insert into Books values('"+Book_Name+"','"+Book_Code+"','"+Return_Date+"')";
                db.execSQL(query);
                db.close();
                Toast.makeText(AddBook.this, "Your data is saved", Toast.LENGTH_SHORT).show();
                AddBook.this.finish();
            }
        });

    }
}
