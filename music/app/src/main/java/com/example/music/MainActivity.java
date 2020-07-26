package com.example.music;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button bt;
TextView tv;
SeekBar sb;
MediaPlayer mp;
int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.bt1);
        tv=findViewById(R.id.tv1);
        sb=findViewById(R.id.sb1);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b==true)
                {
                    mp.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Intent.ACTION_GET_CONTENT);
                in.setType("audio/*");
                startActivityForResult(in,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== Activity.RESULT_OK)
        {
            try {
                if(mp!=null)
                    mp.stop();
                mp = new MediaPlayer();
                mp.setDataSource(MainActivity.this, data.getData());
                mp.prepare();
                mp.start();
                sb.setMax(mp.getDuration());
                CountDownTimer ct=new CountDownTimer(mp.getDuration(),1000) {
                    @Override
                    public void onTick(long l) {
                        sb.setProgress(mp.getCurrentPosition());
                        i++;
                        tv.setText(""+i);
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                ct.start();
            }
            catch(Exception e)
            {
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
