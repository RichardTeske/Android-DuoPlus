package com.example.duoplus.view;

import android.content.Intent;
import android.media.Image;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

public class Welcome extends AppCompatActivity {


    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private TextView txtName;
    private ImageButton ibtMeusDados;
    private ImageButton ibtMeuPlano;
    private ImageButton ibtPlanos;
    private ImageButton ibtMyEnvironment;
    private ImageButton btnLogout;
    private ImageButton ibtConfig;
    private User user;
    private Plan plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtName = (TextView) findViewById(R.id.txtName);
        ibtMeuPlano = (ImageButton) findViewById(R.id.ibtMeuPlano);
        ibtPlanos = (ImageButton) findViewById(R.id.ibtPlanos);
        ibtMeusDados = (ImageButton) findViewById(R.id.ibtMeusDados);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);
        ibtConfig = (ImageButton) findViewById(R.id.ibtConfig);
        ibtMyEnvironment = (ImageButton) findViewById(R.id.ibtMyEnvironment);

        user = (User) getIntent().getSerializableExtra("object");

        txtName.setText(user.getUserName());

        ibtMeuPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyPlan();
            }
        });

        ibtMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyInfo();
            }
        });

        ibtPlanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlans();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        ibtConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToConfig();
            }
        });

        ibtMyEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMyEnvironment();
            }
        });

    }

    private void goToMyEnvironment() {
        Intent intent = new Intent(this, MyEnvironment.class);
        intent.putExtra("objectUser", user);
        startActivity(intent);
    }

    private void goToConfig(){

        if (!user.getIdPlan().equals("")) {
            Intent intent = new Intent(this, ConfigEnvironment.class);
            intent.putExtra("objectUser", user);
            startActivity(intent);
        } else {
            displayMensage("Você não tem plano");
        }


    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToMyPlan() {

        if (!user.getIdPlan().equals("")) {

            Intent intentInfo = new Intent(this, MyPlan.class);
            intentInfo.putExtra("objectUser", user);
            startActivity(intentInfo);
        } else {
            displayMensage(user.getUserName()+", você não tem plano ativo!");
        }
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }

    private void goToMyInfo(){
        Intent intentInfo = new Intent(this, MyInfo.class);
        intentInfo.putExtra("object", user);
        startActivity(intentInfo);
    }


    private void goToPlans(){
        Intent intent = new Intent(this, PlanPage.class);
        intent.putExtra("object", user);
        startActivity(intent);
    }

}
