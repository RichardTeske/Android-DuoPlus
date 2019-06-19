package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.duoplus.R;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

public class PlanPage extends AppCompatActivity {

    private TextView txtNameAvancado, txtVlrAvancado, txtQtdAvancado,
    txtNameIntermedio, txtVlrIntermedio, txtQtdIntermedio,
    txtNameBasico, txtVlrBasico, txtQtdBasico;

    private ImageButton ibtPlan1, ibtPlan2, ibtPlan3;
    private Button btnVoltarPlan;

    private Plan basico, intermedio, avancado;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        user = (User) getIntent().getSerializableExtra("object");

        txtNameAvancado = (TextView) findViewById(R.id.txtNameAvancado);
        txtVlrAvancado = (TextView) findViewById(R.id.txtVlrAvancado);
        txtQtdAvancado = (TextView) findViewById(R.id.txtQtdAvancado);

        txtNameIntermedio = (TextView) findViewById(R.id.txtNameIntermedio);
        txtVlrIntermedio = (TextView) findViewById(R.id.txtVlrIntermedio);
        txtQtdIntermedio = (TextView) findViewById(R.id.txtQtdIntermedio);

        txtNameBasico = (TextView) findViewById(R.id.txtNameBasico);
        txtVlrBasico = (TextView) findViewById(R.id.txtVlrBasico);
        txtQtdBasico = (TextView) findViewById(R.id.txtQtdBasico);

        ibtPlan1 = (ImageButton) findViewById(R.id.ibtPlan1);
        ibtPlan2 = (ImageButton) findViewById(R.id.ibtPlan2);
        ibtPlan3 = (ImageButton) findViewById(R.id.ibtPlan3);

        btnVoltarPlan = (Button) findViewById(R.id.btnVoltarPlan);

        putFieldInfo();

        ibtPlan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlanConfirm(avancado);
            }
        });

        ibtPlan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlanConfirm(intermedio);
            }
        });

        ibtPlan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlanConfirm(basico);
            }
        });

        btnVoltarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcome();
            }
        });

    }

    public void goToWelcome() {
        Intent intentInfo = new Intent(this, Welcome.class);
        intentInfo.putExtra("object", user);
        startActivity(intentInfo);
    }

    public void goToPlanConfirm(Plan plan) {
        Intent intentInfo = new Intent(this, PlanConfirm.class);
        intentInfo.putExtra("object1", plan);
        intentInfo.putExtra("object2", user);
        startActivity(intentInfo);
    }

    public void putFieldInfo() {
        basico = PlanDAO.planById(this,3);
        intermedio = PlanDAO.planById(this,2);
        avancado = PlanDAO.planById(this,1);

        txtNameAvancado.setText(avancado.getPlanName());
        txtVlrAvancado.setText(Float.toString(avancado.getPlanValue()));
        txtQtdAvancado.setText(Integer.toString(avancado.getPlanQuantity()));

        txtNameIntermedio.setText(intermedio.getPlanName());
        txtVlrIntermedio.setText(Float.toString(intermedio.getPlanValue()));
        txtQtdIntermedio.setText(Integer.toString(intermedio.getPlanQuantity()));

        txtNameBasico.setText(basico.getPlanName());
        txtVlrBasico.setText(Float.toString(basico.getPlanValue()));
        txtQtdBasico.setText(Integer.toString(basico.getPlanQuantity()));
    }
}
