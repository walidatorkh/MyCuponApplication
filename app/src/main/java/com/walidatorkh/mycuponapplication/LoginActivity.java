package com.walidatorkh.mycuponapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
    }
    public void buttonLogin_onclick(View view){
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        if(username.equals("abcd") && password.equals("1234")){
            Credentialls.Username = username;
            Credentialls.Password = password;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_LONG).show();
        }
    }
}
