package com.example.duoplus.view;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.model.User;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;

    User userLogin;

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
                } else {
                    displayMensage("E-mail ou senha invalido");
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListPage();
            }
        });
    }

    private void goToListPage(){
        Intent intentList = new Intent(this, List.class);
        startActivity(intentList);
    }

    private void goToWelcomePage(){
        Intent intentWelcome = new Intent(this, Welcome.class);
        intentWelcome.putExtra("object", userLogin);
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
            userLogin = UserDAO.userLogin(this, edtEmail.getText().toString(), edtPassword.getText().toString());

            if (userLogin == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void displayMensage(String mensage) {
        Toast.makeText(MainActivity.this, mensage,
                Toast.LENGTH_LONG).show();
    }
}
