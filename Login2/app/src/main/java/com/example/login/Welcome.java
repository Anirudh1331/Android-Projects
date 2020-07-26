package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Welcome extends AppCompatActivity {
EditText et3;
Button bt3,bt4;
ImageView img;
Intent data;
ProgressDialog dialog;
    StorageReference riversRef;
    private StorageReference mStorageRef;
    String email;

    void dispProfile() {
        try {
            final File localFile = File.createTempFile("images", "jpg");
            riversRef = mStorageRef.child(email+"/pp.jpg");
            riversRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            img.setImageURI(Uri.parse(localFile.getAbsolutePath()));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(Welcome.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        et3=findViewById(R.id.et3);
        bt3=findViewById(R.id.bt3);
        bt4=findViewById(R.id.bt4);
        img=findViewById(R.id.img);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        SharedPreferences preferences=getSharedPreferences("mysp",MODE_PRIVATE);
        email=preferences.getString("email","default");
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog=new ProgressDialog(Welcome.this);
                dialog.show();
                riversRef = mStorageRef.child(email+"/pp.jpg");

                riversRef.putFile(data.getData())
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                dialog.cancel();
                                Toast.makeText(Welcome.this, "File uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(Welcome.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        dispProfile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        this.data=data;
        if(requestCode==1 && resultCode==RESULT_OK){
            img.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
