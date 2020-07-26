package com.example.pb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
EditText txt_name,txt_pno,txt_group;
Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        txt_name=findViewById(R.id.txt_main_name);
        txt_pno=findViewById(R.id.txt_main_pno);
        txt_group=findViewById(R.id.txt_main_group);
        btn_save=findViewById(R.id.btn_main_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
                String name=txt_name.getText().toString();
                String pno=txt_pno.getText().toString();
                String grp=txt_group.getText().toString();
                db.execSQL("create table if not exists contacts(name varchar,pno varchar,grp varchar)");
                String query="insert into contacts values('"+name+"','"+pno+"','"+grp+"')";
                db.execSQL(query);
                db.close();
                Toast.makeText(AddContact.this, "Your data is saved", Toast.LENGTH_SHORT).show();
                AddContact.this.finish();
            }
        });
    }
}
