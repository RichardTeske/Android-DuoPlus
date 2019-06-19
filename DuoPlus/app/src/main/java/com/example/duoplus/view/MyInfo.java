package com.example.duoplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

import org.w3c.dom.Text;

public class MyInfo extends AppCompatActivity {

    private TextView txtEmail;
    private TextView txtCPF;
    private TextView txtAddress;
    private TextView txtCEP;
    private TextView txtPhoneNumber;
    private TextView txtBornDate;
    private TextView txtName;
    private TextView txtActivePlan;
    private Button btnEdit, btnVoltarMyInfo;

    User user;
    Plan plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtCPF = (TextView) findViewById(R.id.txtCPF);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtCEP = (TextView) findViewById(R.id.txtCEP);
        txtPhoneNumber = (TextView) findViewById(R.id.txtPhoneNumber);
        txtBornDate = (TextView) findViewById(R.id.txtBornDate);
        txtName = (TextView) findViewById(R.id.txtName);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        txtActivePlan = (TextView) findViewById(R.id.txtActivePlan);

        btnVoltarMyInfo = (Button) findViewById(R.id.btnVoltarMyInfo);

        user = (User) getIntent().getSerializableExtra("object");

        txtEmail.setText(user.getUserEmail());
        txtCPF.setText(user.getUserCPF());
        txtAddress.setText(user.getUserAddress());
        txtCEP.setText(user.getUserCEP());
        txtPhoneNumber.setText(user.getUserPhoneNumber());
        txtBornDate.setText(user.getUserBornDate());
        txtName.setText(user.getUserName());

        if (!user.getIdPlan().equals("")) {
            plan = PlanDAO.planById(this, Integer.valueOf(user.getIdPlan()));
            txtActivePlan.setText(plan.getPlanName());
        } else {
            txtActivePlan.setText("Sem plano ativo!");
        }



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnVoltarMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcome();
            }
        });

    }

    private void goToWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("object", user);
        startActivity(intent);
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }

}
