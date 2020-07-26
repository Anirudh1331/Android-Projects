package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewBook extends AppCompatActivity {
TextView tv1,tv2,tv3;
Button bt;
String Book_Name,Book_Code,Return_Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        tv1=findViewById(R.id.view_tv1);
        tv2=findViewById(R.id.view_tv2);
        tv3=findViewById(R.id.view_tv3);
        bt=findViewById(R.id.view_close);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewBook.this.finish();
            }
        });
        Intent in=getIntent();
        Book_Name=in.getStringExtra("Book_Name");
        SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
        String query="select * from Books where Book_Name = '"+Book_Name+"'";
        Cursor c=db.rawQuery(query,null);
        c.moveToNext();
        Book_Code=c.getString(1);
        Return_Date=c.getString(2);
        tv1.setText(Book_Name);
        tv2.setText(Book_Code);
        tv3.setText(Return_Date);
    }
}
