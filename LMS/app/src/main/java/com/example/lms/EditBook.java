package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditBook extends AppCompatActivity {
EditText edit_et1,edit_et2,edit_et3;
Button btn_save;
String Book_Name,Book_Code,Return_Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        edit_et1=findViewById(R.id.edit_et1);
        edit_et2=findViewById(R.id.edit_et2);
        edit_et3=findViewById(R.id.edit_et3);
        btn_save=findViewById(R.id.edit_bt1);
        Intent in=getIntent();
        Book_Name=in.getStringExtra("Book_Name");
        SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
        String query="select * from Books where Book_Name = '"+Book_Name+"'";
        Cursor c=db.rawQuery(query,null);
        c.moveToNext();
        Book_Code=c.getString(1);
        Return_Date=c.getString(2);
        edit_et1.setText(Book_Name);
        edit_et2.setText(Book_Code);
        edit_et3.setText(Return_Date);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
                String query="update Books set Book_Name='"+edit_et1.getText().toString()+"',Book_Code='"+edit_et2.getText().toString()+"',Return_Date='"+edit_et3.getText().toString()+"' where Book_Name= '"+Book_Name+"'";
                db.execSQL(query);
                db.close();
                Toast.makeText(EditBook.this, "Info Updated", Toast.LENGTH_SHORT).show();
                EditBook.this.finish();
            }
        });
    }
}
