package com.example.myintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class Welcome extends AppCompatActivity {
TextView tv;
Button b1,b2;
ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv=findViewById(R.id.welcome_tv_message);
        Intent in=getIntent();
        String uname=in.getStringExtra("username");
        tv.setText("welcome "+uname);
        b1=findViewById(R.id.w_b1);
        b2=findViewById(R.id.w_b2);
        iv=findViewById(R.id.iv);
        ActivityCompat.requestPermissions(Welcome.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Intent.ACTION_GET_CONTENT);
                in.setType("image/*");
                startActivityForResult(in,1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Intent.ACTION_GET_CONTENT);
                in.setType("audio/*");
                startActivityForResult(in,2);
            }
        });

    }
        protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1 && resultCode== Activity.RESULT_OK)
        {
            iv.setImageURI(data.getData());
        }
        if(requestCode==2 && resultCode==Activity.RESULT_OK)
        {
            MediaPlayer mp=new MediaPlayer();
            try {
                mp.setDataSource(Welcome.this,data.getData());
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
