package com.example.duoplus;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkLoginFields()) {
                    goToWelcomePage();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterPage();
            }
        });



    }

    private void goToWelcomePage(){
        Intent intentWelcome = new Intent(this, Welcome.class);
        startActivity(intentWelcome);
    }

    private void goToRegisterPage() {
        Intent intentRegister = new Intent(this, Register.class);
        startActivity(intentRegister);
    }

    private void saveUser() {
        User user = new User();


        user.setUserEmail(edtEmail.toString());
        user.setUserPassword(edtPassword.toString());

    }

    private boolean checkLoginFields() {
        if (edtEmail.getText().toString().equals("")) {
            displayMensage("Por favor preencha o campo E-mail");
            return false;
        } else if (edtPassword.getText().toString().equals("")) {
            displayMensage("Por favor preencha o campo Senha");
            return false;
        } else if (!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".com")) {
            displayMensage("Email inválido");
            return false;
        } else {
            return true;
        }
    }

    private void displayMensage(String mensage) {
        Toast.makeText(MainActivity.this, mensage,
                Toast.LENGTH_LONG).show();
    }
}
