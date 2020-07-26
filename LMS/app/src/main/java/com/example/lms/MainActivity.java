package com.example.lms;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
LinearLayout ll;
TextView t;
EditText txt_search;
void fill(){
    ll.removeAllViews();
    SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
    db.execSQL("create table if not exists Books(Book_Name varchar,Book_Code varchar,Return_Date varchar)");
    String query="select * from Books order by Return_Date ";
    Cursor c=db.rawQuery(query,null);
    if(c.moveToNext()){
        do{
            TextView tv=new TextView(MainActivity.this);
            tv.setText(c.getString(0));
            tv.setTextSize(25);
            registerForContextMenu(tv);
            ll.addView(tv);
        }while(c.moveToNext());
    }
    else{
        Toast.makeText(this, "No Books right now", Toast.LENGTH_SHORT).show();
    }
}
    void fill(String s){
        ll.removeAllViews();
        SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Books(Book_Name varchar,Book_Code varchar,Return_Date varchar)");
        String query="select * from Books where Book_Name like '%"+s+"%'or Book_Code like '%"+s+"%' order by Return_Date";
        Cursor c=db.rawQuery(query,null);
        if(c.moveToNext()){
            do{
                TextView tv=new TextView(MainActivity.this);
                tv.setText(c.getString(0));
                tv.setTextSize(25);
                registerForContextMenu(tv);
                ll.addView(tv);
            }while(c.moveToNext());
        }
        else{
            Toast.makeText(this, "No Books right now", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        fill();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll=findViewById(R.id.ll);
        txt_search=findViewById(R.id.main_et1);
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
        mi.inflate(R.menu.forcontextmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
        t=(TextView)v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
    if(item.getItemId()==R.id.cmenu_m1)
    {
        String Book_Name=t.getText().toString();
        Intent in=new Intent(MainActivity.this,EditBook.class);
        in.putExtra("Book_Name",Book_Name);
        Toast.makeText(this, "You clicked on Edit Book info", Toast.LENGTH_SHORT).show();
        startActivity(in);
    }
    else if(item.getItemId()==R.id.cmenu_m2)
    {
        AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
        a.setTitle("Alert");
        a.setMessage("Would you like to delete this books data");
        a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SQLiteDatabase db=openOrCreateDatabase("LMS",MODE_PRIVATE,null);
                db.execSQL("create table if not exists Books(Book_Name varchar,Book_Code varchar,Return_Date varchar)");
                String query="delete from Books where Book_Name='"+t.getText().toString()+"'";
                db.execSQL(query);
                db.close();
                fill();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Opening Books info", Toast.LENGTH_SHORT).show();
            }
        });
        a.show();
    }
    else if(item.getItemId()==R.id.cmenu_m3)
    {
        String Book_Name=t.getText().toString();
        Intent in=new Intent(MainActivity.this,ViewBook.class);
        in.putExtra("Book_Name",Book_Name);
        Toast.makeText(this, "You clicked on View Book info", Toast.LENGTH_SHORT).show();
        startActivity(in);
    }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=this.getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.mainmenu_m1==item.getItemId()){
            Intent in=new Intent(MainActivity.this,AddBook.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}
