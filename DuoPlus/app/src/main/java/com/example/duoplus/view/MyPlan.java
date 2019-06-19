package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duoplus.R;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

public class MyPlan extends AppCompatActivity {

    private User user;
    private Plan plan;

    private TextView txtVipNameUser, txtVipNamePlan, txtVipVlrPlan, txtVipQtdPlan;
    private ImageView imgVipPlan;

    private Button btnPerfilMyPlan, btnVoltarMyPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);

        user = (User) getIntent().getSerializableExtra("objectUser");
        plan = PlanDAO.planById(this, Integer.valueOf(user.getIdPlan()));

        txtVipNameUser = (TextView) findViewById(R.id.txtVipNameUser);
        txtVipNamePlan = (TextView) findViewById(R.id.txtVipNamePlan);
        txtVipVlrPlan = (TextView) findViewById(R.id.txtVipVlrPlan);
        txtVipQtdPlan = (TextView) findViewById(R.id.txtVipQtdPlan);

        btnPerfilMyPlan = (Button) findViewById(R.id.btnPerfilMyPlan);
        btnVoltarMyPlan = (Button) findViewById(R.id.btnVoltarMyPlan);

        imgVipPlan = (ImageView) findViewById(R.id.imgVipPlan);

        txtVipNamePlan.setText(plan.getPlanName());
        txtVipNameUser.setText(user.getUserName());
        txtVipVlrPlan.setText(Float.toString(plan.getPlanValue()));
        txtVipQtdPlan.setText(Integer.toString(plan.getPlanQuantity()));

        if (plan.getPlanId() == 1) {
            imgVipPlan.setImageResource(R.drawable.avancado);
        } else if (plan.getPlanId() == 2) {
            imgVipPlan.setImageResource(R.drawable.intermedio);
        } else if (plan.getPlanId() == 3) {
            imgVipPlan.setImageResource(R.drawable.basico);
        }

        btnPerfilMyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyInfo();
            }
        });

        btnVoltarMyPlan.setOnClickListener(new View.OnClickListener() {
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

    private void goToMyInfo(){
        Intent intent = new Intent(this, MyInfo.class);
        intent.putExtra("object", user);
        startActivity(intent);
    }
}
