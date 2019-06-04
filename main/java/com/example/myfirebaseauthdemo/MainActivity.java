package com.example.myfirebaseauthdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_reg;
    private EditText et_email;
    private EditText et_pass;
    private TextView tv_signin;
    private ProgressDialog progressdialoag;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        progressdialoag = new ProgressDialog(this);
        btn_reg = findViewById(R.id.Reg_btn);
        et_email = findViewById(R.id.ET_email);
        et_pass = findViewById(R.id.ET_pass);
        tv_signin = findViewById(R.id.TV_signin);

        btn_reg.setOnClickListener(this);
        tv_signin.setOnClickListener(this);
    }

    private void registeruser(){
        String email = et_email.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email empty
            Toast.makeText(this, "Pls Enter Email!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            //pass empty
            Toast.makeText(this, "Pls Enter Password!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressdialoag.setMessage("Registering user......");
        progressdialoag.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {

                        if(task.isSuccessful()){
                            //user successful register and login
                            progressdialoag.cancel();
                            Toast.makeText(MainActivity.this, "Registration Successfully....", Toast.LENGTH_SHORT).show();
                        }else {
                            progressdialoag.cancel();
                            Toast.makeText(MainActivity.this, "Could  not Register....try again!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if(v == btn_reg){
            registeruser();
        }
        if(v == tv_signin){
            //login page in here

        }
    }
}
