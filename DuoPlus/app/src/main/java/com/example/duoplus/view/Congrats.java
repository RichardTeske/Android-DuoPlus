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
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

public class Congrats extends AppCompatActivity {

    private TextView txtNameCongratsUser, txtNameCongratsPlan;
    private ImageView imgCongratsPlan;
    private Button btnCongratsContinuar;

    private User user;
    private Plan plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);

        txtNameCongratsPlan = (TextView) findViewById(R.id.txtNameCongratsPlan);
        txtNameCongratsUser = (TextView) findViewById(R.id.txtNameCongratsUser);
        imgCongratsPlan = (ImageView) findViewById(R.id.imgCongratsPlan);
        btnCongratsContinuar = (Button) findViewById(R.id.btnCongratsContinuar);

        plan = (Plan) getIntent().getSerializableExtra("objectPlan");
        user = (User) getIntent().getSerializableExtra("objectUser");

        txtNameCongratsUser.setText(user.getUserName());
        txtNameCongratsPlan.setText(plan.getPlanName());

        if (plan.getPlanId() == 1) {
            imgCongratsPlan.setImageResource(R.drawable.avancado);
        } else if (plan.getPlanId() == 2) {
            imgCongratsPlan.setImageResource(R.drawable.intermedio);
        } else if (plan.getPlanId() == 3) {
            imgCongratsPlan.setImageResource(R.drawable.basico);
        }

        btnCongratsContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcome();
            }
        });
    }

    public void goToWelcome() {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("object", user);
        startActivity(intent);
    }
}
