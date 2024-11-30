package com.example.chatapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class registration extends AppCompatActivity {
    TextView loginbut;
    EditText rg_username, rg_email,rg_password,rg_repassword;
    Button rg_signup;
   // CircleImageView rg_profileImg;
    FirebaseAuth auth;
    Uri imageURI;
    String imageuri;
    String emailPattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        database=FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        loginbut = findViewById(R.id.loginbut);
        rg_username = findViewById(R.id.rgusername);
        rg_email = findViewById(R.id.rgemail);
        rg_password = findViewById(R.id.rgpassword);
        rg_repassword = findViewById(R.id.rgrepassword);
        rg_signup = findViewById(R.id.signupbutton);


        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
                finish();

            }
        });

        rg_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = rg_username.getText().toString();
                String email = rg_email.getText().toString();
                String password = rg_password.getText().toString();
                String cPassword = rg_repassword.getText().toString();
                String status = "Hey I'm Using This Application";

//                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword)) {
//                    Toast.makeText(registration.this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
//                } else if (!email.matches(emailPattern)) {
//                    rg_email.setError("Type A Valid Email Here");
//                } else if (password.length() < 6) {
//                    rg_password.setError("Password Must Be 6 Characters Or More ");
//                } else if (!password.equals(cPassword)) {
//                    rg_password.setError("The password Do not Match");
//                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = task.getResult().getUser().getUid();
                                DatabaseReference reference = database.getReference().child("User").child(id);
                                StorageReference storageReference = storage.getReference().child("User").child(id);
                                Intent intent = new Intent(registration.this, login.class);
                                startActivity(intent);


//                                if (imageURI != null) {
//                                    storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                            if (task.isSuccessful()) {
//                                                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Uri> task) {
//                                                        imageuri = imageuri.toString();
//                                                        Users users = new Users(id, name, email, password, cPassword, imageuri);
//                                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                            @Override
//                                                            public void onComplete(@NonNull Task<Void> task) {
//                                                                if (task.isSuccessful()) {
////                                                                    Intent intent = new Intent(registration.this, MainActivity.class);
////                                                                    startActivity(intent);
//                                                                    finish();
//                                                                } else {
//                                                                    Toast.makeText(registration.this, "Enter in creating the user", Toast.LENGTH_SHORT).show();
//                                                                    Toast.makeText(registration.this, "First to second work", Toast.LENGTH_SHORT).show();
//                                                                }
//                                                            }
//                                                        });
//                                                    }
//                                                });
//                                            }
//
//                                        }
//                                    });
//                                } else {
//                                    String status = "Hey I'm Using This Application";
//                                    Users users = new Users(id, name, email, password, imageuri, status);
//                                    reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Intent intent = new Intent(registration.this, MainActivity.class);
//                                                startActivity(intent);
//                                                finish();
//                                            } else {
//                                                Toast.makeText(registration.this, "Enter in creating the user", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
//                                }
                            }else {
                                Toast.makeText(registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                        
                    });
                }


        });
}
}












