package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.duoplus.Mask;
import com.example.duoplus.R;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.model.User;

public class EditUser extends AppCompatActivity {

    private EditText edtName;
    private EditText edtCPF;
    private EditText edtBornDate;
    private EditText edtCEP;
    private EditText edtAddress;
    private EditText edtPhoneNumber;
    private EditText edtTest;
    private EditText edtEmail;
    private EditText edtPassword;
    private User user;

    private Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtName = (EditText) findViewById(R.id.edtName);
        edtCPF = (EditText) findViewById(R.id.edtCPF);
        edtCPF.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCPF));
        edtBornDate = (EditText) findViewById(R.id.edtBornDate);
        edtBornDate.addTextChangedListener(Mask.insert(Mask.DATE_MASK, edtBornDate));
        edtCEP = (EditText) findViewById(R.id.edtCEP);
        edtCEP.addTextChangedListener(Mask.insert(Mask.CEP_MASK, edtCEP));
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        edtPhoneNumber.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, edtPhoneNumber));
        //edtTest = (EditText) findViewById(R.id.edtTest);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnEditar = (Button) findViewById(R.id.btnEditar);


        user = (User) getIntent().getSerializableExtra("object");

        edtName.setText(user.getUserName());
        edtCPF.setText(user.getUserCPF());
        edtBornDate.setText(user.getUserBornDate());
        edtCEP.setText(user.getUserCEP());
//        edtTest.setText(user.getUserTest());
        edtPhoneNumber.setText(user.getUserPhoneNumber());
        edtEmail.setText(user.getUserEmail());
        edtPassword.setText(user.getUserPassword());
        edtAddress.setText(user.getUserAddress());



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setUserName(edtName.getText().toString());
                user.setUserCPF(edtCPF.getText().toString());
                user.setUserBornDate(edtBornDate.getText().toString());
                user.setUserCEP(edtCEP.getText().toString());
                user.setUserAddress(edtAddress.getText().toString());
                user.setUserPhoneNumber(edtPhoneNumber.getText().toString());
                user.setUserTest("");
                user.setUserPassword(edtPassword.getText().toString());
                user.setUserEmail(edtEmail.getText().toString());
                user.setIdPlan(user.getIdPlan());

                save();

                goToMyInfo();

            }
        });

    }

    private void goToMyInfo(){
        Intent intentInfo = new Intent(this, MyInfo.class);
        intentInfo.putExtra("object", user);
        startActivity(intentInfo);
    }

    private void save() {
        int num = UserDAO.editUser(this, user);
    }
}
