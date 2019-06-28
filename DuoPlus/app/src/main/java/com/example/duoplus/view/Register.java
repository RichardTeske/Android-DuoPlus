package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duoplus.Mask;
import com.example.duoplus.R;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.model.User;

public class Register extends AppCompatActivity {

    private EditText edtName;
    private EditText edtCPF;
    private EditText edtBornDate;
    private EditText edtCEP;
    private EditText edtAddress;
    private EditText edtPhoneNumber;
    //private EditText edtTest;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;

    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = (EditText) findViewById(R.id.edtName);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtCPF.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCPF));
        edtBornDate = (EditText) findViewById(R.id.edtBornDate);
        edtBornDate.addTextChangedListener(Mask.insert(Mask.DATE_MASK, edtBornDate));
        edtCEP = (EditText) findViewById(R.id.edtCEP);
        edtCEP.addTextChangedListener(Mask.insert(Mask.CEP_MASK, edtCEP));
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        //edtTest = (EditText) findViewById(R.id.edtTest);
        edtPhoneNumber.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, edtPhoneNumber));
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmptyFields() && checkPassword() && validateFields()) {

                    User user = new User();
                    user.setUserName(edtName.getText().toString());
                    user.setUserCPF(edtCPF.getText().toString());
                    user.setUserBornDate(edtBornDate.getText().toString());
                    user.setUserCEP(edtCEP.getText().toString());
                    user.setUserAddress(edtAddress.getText().toString());
                    user.setUserTest("");
                    //user.setUserTest(edtTest.getText().toString());
                    user.setUserPhoneNumber(edtPhoneNumber.getText().toString());
                    user.setUserPassword(edtPassword.getText().toString());
                    user.setUserEmail(edtEmail.getText().toString());
                    user.setIdPlan("");

                    long id = UserDAO.userCreate(getBaseContext(), user);

                    displayMensage("Usuario criado com sucesso, ID: "+id);
                    goToMainPage();
                }
            }

        });
    }



    private void goToMainPage(){
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }

    private boolean validateFields() {

        if (edtCPF.getText().toString().length() != 14){
            displayMensage("CPF Invalido");
            return false;
        } else if (edtBornDate.getText().toString().length() != 10){
            displayMensage("Data de Nascimento Invalida");
            return false;
        } else if (edtCEP.getText().toString().length() != 9) {
            displayMensage("CEP invalido");
            return false;
        } else if (edtPhoneNumber.getText().toString().length() != 15) {
            displayMensage("Telefone ou Celular invalido");
            return false;
        } else if (edtPassword.getText().toString().length() <= 5) {
            displayMensage("Senha deve conter mais que 5 caracteres");
            return false;
        } else if (UserDAO.CheckRegisterExists(this, edtEmail.getText().toString())){
            displayMensage("Ja existe um usuario com este email");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEmptyFields(){
        if (edtName.getText().toString().equals("")) {
            displayMensage("Preencha o campo Nome");
            return false;
        } else if (edtCPF.getText().toString().equals("")) {
            displayMensage("Preencha o campo CPF");
            return false;
        } else  if (edtBornDate.getText().toString().equals("")) {
            displayMensage("Preencha o campo Data de Nascimento");
            return false;
        } else if (edtCEP.getText().toString().equals("")) {
            displayMensage("Preencha o campo CEP");
            return false;
        } else if (edtAddress.getText().toString().equals("")) {
            displayMensage("Preencha o campo Logradouro");
            return false;
        } else if (edtPhoneNumber.getText().toString().equals("")) {
            displayMensage("Preencha o campo Telefone");
            return false;
        //} else if (edtTest.getText().toString().equals("")) {
        //    displayMensage("Preencha o campo Teste");
        //    return false;
        } else if (edtEmail.getText().toString().equals("")) {
            displayMensage("Preencha o campo E-mail");
            return false;
        } else if (edtPassword.getText().toString().equals("")) {
            displayMensage("Preencha o campo Senha");
            return false;
        } else if (edtConfirmPassword.getText().toString().equals("")) {
            displayMensage("Preencha o campo Confirmar Senha");
            return false;
        } else if (!checkEmail()){
            return false;
        } else {
            return true;
        }

    }

    private boolean checkEmail(){
        if (!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".com")) {
            displayMensage("Email invalido");
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        if (!edtPassword.getText().toString().equals(
                edtConfirmPassword.getText().toString()
        )) {
            displayMensage("Senhas não são iguais");
            return false;
        } else {
            return true;
        }
    }

    private void displayMensage(String mensage) {
        Toast.makeText(Register.this, mensage,
                Toast.LENGTH_LONG).show();
    }
}
