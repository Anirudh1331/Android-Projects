package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText et1,et2;
Button bt;
TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        bt=findViewById(R.id.b1);
        tv1=findViewById(R.id.main_tv1);
        tv2=findViewById(R.id.main_tv2);
        registerForContextMenu(tv1);
        registerForContextMenu(tv2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("admin") && et2.getText().toString().equals("india"))
                {
                    Intent in=new Intent(MainActivity.this,Welcome.class);
                    in.putExtra("username",et1.getText().toString());
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid id or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        TextView t=(TextView)v;
        if(t==tv1) {
            MenuInflater mi = this.getMenuInflater();
            mi.inflate(R.menu.contextmenu1, menu);
        }
        else{
            MenuInflater mi = this.getMenuInflater();
            mi.inflate(R.menu.contextmenu2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.cm1_m1)
            Toast.makeText(this, "Clicked on tv1 and menu 1", Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.cm1_m2)
            Toast.makeText(this, "Clicked on tv1 and menu 2", Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.cm1_m3)
            Toast.makeText(this, "Clicked on tv1 and menu 3", Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.cm1_m4)
            Toast.makeText(this, "Clicked on tv2 and menu 4", Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.cm1_m5)
            Toast.makeText(this, "Clicked on tv2 and menu 5", Toast.LENGTH_SHORT).show();
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
        if(item.getItemId()==R.id.m1)
            Toast.makeText(this, "you have clicked on add contact", Toast.LENGTH_SHORT).show();
        if(item.getItemId()==R.id.m2)
            Toast.makeText(this, "you have clicked on search contact", Toast.LENGTH_SHORT).show();
        if(item.getItemId()==R.id.m3)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
}
