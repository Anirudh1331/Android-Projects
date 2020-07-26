package com.example.pb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContact extends AppCompatActivity {
EditText txt_search;
LinearLayout ll;
TextView t;
void fill()
{
    ll.removeAllViews();
    SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
    db.execSQL("create table if not exists contacts(name varchar,pno varchar,grp varchar)");
    String query="select * from contacts order by name";
    Cursor c=db.rawQuery(query,null);
    if(c.moveToFirst()){
        do{
            TextView tv=new TextView(SearchContact.this);
            tv.setText(c.getString(0));
            tv.setTextSize(24);
            registerForContextMenu(tv);
            ll.addView(tv);
        }while (c.moveToNext());
    }
    else
    {
        Toast.makeText(this, "sorry there is no contact right now", Toast.LENGTH_SHORT).show();
    }
}
    void fill(String name)
    {
        ll.removeAllViews();
        SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists contacts(name varchar,pno varchar,grp varchar)");
        String query="select * from contacts where name like '%"+name+"%' order by name";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst()){
            do{
                TextView tv=new TextView(SearchContact.this);
                tv.setText(c.getString(0));
                tv.setTextSize(24);
                registerForContextMenu(tv);
                ll.addView(tv);
            }while (c.moveToNext());
        }
        else
        {
            Toast.makeText(this, "sorry there is no such contact right now", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
    super.onStart();
        fill();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        txt_search=findViewById(R.id.seach_txt_search);
        ll=findViewById(R.id.ll);
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fill(txt_search.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mi=this.getMenuInflater();
        mi.inflate(R.menu.contextmenu,menu);
    super.onCreateContextMenu(menu, v, menuInfo);
    t=(TextView)v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    if(item.getItemId()==R.id.cm_m1){
        //Edit contact
        String name=t.getText().toString();
        Intent in=new Intent(SearchContact.this,EditContact.class);
        in.putExtra("name",name);
        Toast.makeText(this, "You clicked on Edit Contact ", Toast.LENGTH_SHORT).show();
        startActivity(in);
    }
    else if(item.getItemId()==R.id.cm_m2){
        //Delete contact
        AlertDialog.Builder a=new AlertDialog.Builder(SearchContact.this);
        a.setTitle("Alert");
        a.setMessage("Would u like to delete this contact");
        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
                db.execSQL("create table if not exists contacts(name varchar,pno varchar,grp varchar)");
                String query="delete from contacts where name='"+t.getText().toString()+"'";
                db.execSQL(query);
                db.close();
                fill();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(SearchContact.this, "Opening Contacts", Toast.LENGTH_SHORT).show();
            }
        });
        a.show();
    }
    else if(item.getItemId()==R.id.cm_m3){
        //Sms
        Toast.makeText(this, "You clicked on send message", Toast.LENGTH_SHORT).show();
        Intent in1=new Intent(SearchContact.this,sms.class);
        SQLiteDatabase db=openOrCreateDatabase("pb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists contacts(name varchar,pno varchar,grp varchar)");
        String query="select * from contacts where name='"+t.getText().toString()+"'";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        in1.putExtra("pno",c.getString(1));
        startActivity(in1);
    }
    return super.onContextItemSelected(item);
    }
}
