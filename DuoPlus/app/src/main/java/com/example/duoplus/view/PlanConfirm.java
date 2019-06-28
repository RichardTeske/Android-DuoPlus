package com.example.duoplus.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

public class PlanConfirm extends AppCompatActivity {

    Plan plan;
    User user;

    TextView txtNamePlan, txtValuePlan, txtQtdPlan, txtNameConfirmPlan;
    Button btnCancel, btnConfirm;
    ImageView imgPlan;

    public static final String NOTIFICATION_CHANNEL_ID = "channel_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_confirm);

        plan = (Plan) getIntent().getSerializableExtra("object1");
        user = (User) getIntent().getSerializableExtra("object2");

        txtNamePlan = (TextView) findViewById(R.id.txtNamePlan);
        txtValuePlan = (TextView) findViewById(R.id.txtValuePlan);
        txtQtdPlan = (TextView) findViewById(R.id.txtQtdPlan);
        txtNameConfirmPlan = (TextView) findViewById(R.id.txtNameConfirmPlan);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        imgPlan = (ImageView) findViewById(R.id.imgPlan);

        txtNamePlan.setText(plan.getPlanName());
        txtValuePlan.setText(Float.toString(plan.getPlanValue()));
        txtQtdPlan.setText(Integer.toString(plan.getPlanQuantity()));
        txtNameConfirmPlan.setText(plan.getPlanName());

        if (plan.getPlanId() == 1) {
            imgPlan.setImageResource(R.drawable.avancado);
        } else if (plan.getPlanId() == 2) {
            imgPlan.setImageResource(R.drawable.intermedio);
        } else if (plan.getPlanId() == 3) {
            imgPlan.setImageResource(R.drawable.basico);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlan()) {
                    sendNotification();
                    goToCongratsPage();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcome();
            }
        });


    }

    private void sendNotification(){

        NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.emot)
                        .setContentTitle("Parabéns!")
                        .setContentText("Você adquiriu o plano "+plan.getPlanName());
        Intent resultIntent = new Intent(this, MyPlan.class);
        resultIntent.putExtra("objectUser", user);
        PendingIntent resultPendingIntent = PendingIntent
                .getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotifyManager.notify(1, notification);

    }

    public void goToWelcome() {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("object", user);
        startActivity(intent);
    }

    public boolean checkPlan(){
        if (plan.getPlanId() == 1 && user.getIdPlan().equals("1")) {
            goToPlanPage();
            displayMensage("Você já possui o plano "+plan.getPlanName());
            return false;
        } else if (plan.getPlanId() == 2 && user.getIdPlan().equals("2")) {
            goToPlanPage();
            displayMensage("Você já possui o plano "+plan.getPlanName());
            return false;
        } else if (plan.getPlanId() == 3 && user.getIdPlan().equals("3")) {
            goToPlanPage();
            displayMensage("Você já possui o plano "+plan.getPlanName());
            return false;
        } else {
            return true;
        }
    }

    public void goToPlanPage(){
        Intent intent = new Intent(this, PlanPage.class);
        intent.putExtra("object",user);
        startActivity(intent);
    }

    public void goToCongratsPage(){
        User userPlan = UserDAO.setUserPlan(this, String.valueOf(plan.getPlanId()), user.getUserId());

        Intent intent = new Intent(this, Congrats.class);
        intent.putExtra("objectPlan",plan);
        intent.putExtra("objectUser", userPlan);
        startActivity(intent);
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }
}
