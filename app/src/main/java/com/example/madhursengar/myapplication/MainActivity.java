package com.example.madhursengar.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etName,etEmail,etPassword,etConfirmpassword;
    private Button btnRegister;
    private String name,email,password,confirmpassword;
    public TinyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new TinyDB(this);
       initView();
       btnRegister.setOnClickListener(this);
    }
    //we are initialinsing the UI componenets here
    private void  initView(){
        etName= findViewById(R.id.etName);
        etEmail= findViewById(R.id.etEmail);
        etPassword= findViewById(R.id.etPassword);
        etConfirmpassword= findViewById(R.id.etConfirmpassword);
        btnRegister= findViewById(R.id.btnRegister);
    }

    @Override
    public void onClick(View v){
        switch(v.getId())
        {
            case R.id.btnRegister:
                getInfo();
                register();
                break;
        }
    }
    private void getInfo(){
        name= etName.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString();
        confirmpassword=etConfirmpassword.getText().toString();
    }
    private void register()
    {
        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()) {
            Toast.makeText(this, "one or more field is empty", Toast.LENGTH_LONG).show();
        }
        else{
            if(password.equals(confirmpassword)){
                db.putString("name",name);
                db.putString("email",email);
                db.putString("password",password);
                Toast.makeText(this, "registered", Toast.LENGTH_LONG).show();
                Intent i= new Intent(this,LoginActivity.class);
                startActivity(i);
                finish();

            }
            else{
                Toast.makeText(this, "password didn't match", Toast.LENGTH_LONG).show();
            }
        }
    }
}
