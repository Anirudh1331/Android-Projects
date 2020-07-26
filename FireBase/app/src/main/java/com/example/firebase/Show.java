package com.example.firebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Show extends AppCompatActivity {
LinearLayout ll;
TextView t;
void fill()
{
    ll.removeAllViews();
    SQLiteDatabase db1=openOrCreateDatabase("contacts1",MODE_PRIVATE,null);
    db1.execSQL("create table if not exists contacts1(name varchar,pno varchar)");
    String query="select * from contacts1";
    Cursor c=db1.rawQuery(query,null);
    if(c.moveToFirst()){
        do{
            TextView tv1=new TextView(Show.this);
            TextView tv2=new TextView(Show.this);
            tv1.setText(c.getString(0));
            tv1.setTextSize(32);
            tv2.setText(c.getString(1));
            tv2.setTextSize(14);
            registerForContextMenu(tv1);
            ll.addView(tv1);
            ll.addView(tv2);
        }while (c.moveToNext());
    }
    else
    {
        Toast.makeText(this, "sorry there is no contact right now", Toast.LENGTH_SHORT).show();
    }
}
    protected void onStart() {
        super.onStart();
        fill();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ll=findViewById(R.id.ll1);

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
    if(R.id.cmenu_m1==item.getItemId())
    {
        AlertDialog.Builder a=new AlertDialog.Builder(Show.this);
        a.setTitle("Alert");
        a.setMessage("Would u like to delete this contact");
        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteDatabase db1=openOrCreateDatabase("contacts1",MODE_PRIVATE,null);
                db1.execSQL("create table if not exists contacts1(name varchar,pno varchar)");
                String query="delete from contacts1 where name='"+t.getText().toString()+"'";
                db1.execSQL(query);
                db1.close();
                fill();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Show.this, "Opening Contacts", Toast.LENGTH_SHORT).show();
            }
        });
        a.show();
    }
    return super.onContextItemSelected(item);
    }
}
