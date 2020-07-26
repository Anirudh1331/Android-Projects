package com.example.pb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {
    EditText txt_name,txt_pno,txt_grp;
    Button b;
    String name,pno,grp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        txt_name=findViewById(R.id.txt_edit_name);
        txt_pno=findViewById(R.id.txt_edit_pno);
        b=findViewById(R.id.btn_edit_save);
        txt_grp=findViewById(R.id.txt_edit_grp);
        Intent in=getIntent();
        name=in.getStringExtra("name");
        SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
        String query="select * from contacts where name = '"+name+"'";
        Cursor c=db.rawQuery(query,null);
        c.moveToNext();
        pno=c.getString(1);
        grp=c.getString(2);
        txt_name.setText(name);
        txt_pno.setText(pno);
        txt_grp.setText(grp);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p,n,g;
                n=txt_name.getText().toString();
                p=txt_pno.getText().toString();
                g=txt_grp.getText().toString();
                SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
                String query="update contacts set name='"+n+"',pno='"+p+"',grp='"+g+"' where name='"+name+"'";
                db.execSQL(query);
                db.close();
                Toast.makeText(EditContact.this, "Your data is saved", Toast.LENGTH_SHORT).show();
                EditContact.this.finish();
            }
        });
    }
}
