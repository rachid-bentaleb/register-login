package com.example.loginragister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    TextInputEditText inputEditTextFullname,inputEditTextUsername,inputEditTextPassword,inputEditTextemail;
    Button buton_sign;
    TextView text_login;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEditTextFullname=findViewById(R.id.fullname);
        inputEditTextPassword = findViewById(R.id.password);
        inputEditTextemail = findViewById(R.id.email);
        inputEditTextUsername = findViewById(R.id.username);
        buton_sign= findViewById(R.id.buttonSignUp);
        text_login = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);


       buton_sign.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String fullname, username, password, email;
               fullname = String.valueOf(inputEditTextFullname.getText());
               username = String.valueOf(inputEditTextUsername.getText());
               password = String.valueOf(inputEditTextPassword.getText());
               email = String.valueOf(inputEditTextemail.getText());

               if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                   progressBar.setVisibility(View.VISIBLE);
                   Handler handler = new Handler();
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           String[] field = new String[4];
                           field[0] = "fullname";
                           field[1] = "username";
                           field[2] = "password";
                           field[3] = "email";
                           String[] data = new String[4];
                           data[0] = fullname;
                           data[1] = username;
                           data[2] = password;
                           data[3] = email;
                           PutData putData = new PutData("http:// 192.168.8.122/LoginRegister/signup.php", "POST", field, data);
                           if (putData.startPut()) {
                               if (putData.onComplete()) {
                                   progressBar.setVisibility(View.GONE);
                                   String result = putData.getResult();
                                   if (result.equals("Sign Up Success")) {
                                       Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(), Login.class);
                                       startActivity(intent);
                                       finish();
                                   } else {
                                       Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                   }
                               }
                           }
                       }
                   });
               } else {
                   Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }
}