package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.EnvironmentDAO;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;

import java.util.ArrayList;

public class ConfigEnvironment extends AppCompatActivity {

    User user;
    Plan plan;

    ArrayList environments;
    TextView environmentName;
    ListView lstEnvironment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_environment);

        user  = (User) getIntent().getSerializableExtra("objectUser");
        plan = PlanDAO.planById(this, Integer.valueOf(user.getIdPlan()));

        lstEnvironment = (ListView) findViewById(R.id.lstEnvironment);
        environments = (ArrayList<String>) EnvironmentDAO.getEnvironmentsTypes(this);

        final String[] itemname = new String[environments.size()];

        for (int i=0; i< environments.size(); i++) {
            itemname[i] = environments.get(i).toString();
        }
        
        final Integer[] imgid = getImgId();
        
        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid, R.layout.listview_environment, "environment");
        lstEnvironment.setAdapter(adapter);

        lstEnvironment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selItem = itemname[+position];
                int pos = imgid[+position];
                goToConfigDevice(selItem);
            }
        });
    }
    
    private Integer[] getImgId(){
        Integer[] imgid = {
                R.drawable.cozinha,
                R.drawable.sala_estar,
                R.drawable.sala_jantar,
                R.drawable.quarto,
                R.drawable.banheiro,
                R.drawable.garagem,
                R.drawable.escritorio,
                R.drawable.jardim,
                R.drawable.varanda,
                R.drawable.lavanderia
        };
        return imgid;
    }

    public void goToConfigDevice(String envName) {
        Intent intent = new Intent(this, ConfigDevice.class);
        intent.putExtra("stringEnv", envName);
        intent.putExtra("objectUser", user);
        startActivity(intent);
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }
}
