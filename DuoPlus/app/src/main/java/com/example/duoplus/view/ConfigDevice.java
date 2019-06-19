package com.example.duoplus.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.DevicesDAO;
import com.example.duoplus.dal.EnvironmentDAO;
import com.example.duoplus.dal.UserEnvironmentDAO;
import com.example.duoplus.model.Devices;
import com.example.duoplus.model.Environment;
import com.example.duoplus.model.User;
import com.example.duoplus.model.UserEnvironment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ConfigDevice extends AppCompatActivity {

    private TextView txtNameEnv;
    private EditText edtApelidoEnv, edtQtdDisp;
    private ImageView imgEnv;
    private ListView lstDisp;
    private Button btnContinuarDisp;

    private User user;
    private Environment env;
    private String environmentName;
    private String deviceName;
    private Devices dev;
    private UserEnvironment userEnv;

    private ArrayList<String> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_device);

        txtNameEnv = (TextView) findViewById(R.id.txtNameEnv);
        edtApelidoEnv = (EditText) findViewById(R.id.edtApelidoEnv);
        edtQtdDisp = (EditText) findViewById(R.id.edtQtdDisp);
        imgEnv = (ImageView) findViewById(R.id.imgEnv);
        lstDisp = (ListView) findViewById(R.id.lstDisp);
        btnContinuarDisp = (Button) findViewById(R.id.btnContinuarDisp);

        user  = (User) getIntent().getSerializableExtra("objectUser");
        environmentName  = (String) getIntent().getSerializableExtra("stringEnv");
        env = EnvironmentDAO.getEnvironmentByName(this, environmentName);

        txtNameEnv.setText(environmentName);
        setImage();

        devices = (ArrayList<String>) DevicesDAO.getDeviceTypes(this);

        final String[] itemname = new String[devices.size()];

        for (int i=0; i< devices.size(); i++) {
            itemname[i] = devices.get(i).toString();
        }

        final Integer[] imgid = getImgId();

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid, R.layout.listview_environment, "environment");
        lstDisp.setAdapter(adapter);

        lstDisp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lstDisp.setItemChecked(position, true);
            }
        });

        btnContinuarDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = lstDisp.getCheckedItemPosition();
                deviceName = itemname[pos];
                getDevice(deviceName);
                userEnv = setUserEnv();
                insertAllEntities();

                displayMensage("Ambientes cadastrados com sucesso");
                goToWelcome();
            }
        });
    }

    public void goToWelcome() {
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("object",user);
        startActivity(intent);
    }

    public void insertAllEntities() {
        UserEnvironmentDAO.insertAllUserEnv(this, userEnv);
    }

    public void getDevice(String devName) {
        dev = DevicesDAO.getDeviceByName(this, devName);
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }

    private UserEnvironment setUserEnv(){
        UserEnvironment userEnvironment = new UserEnvironment();

        userEnvironment.setUserId(Integer.toString(user.getUserId()));
        userEnvironment.setEnvironmentId(Integer.toString(env.getIdEnvironment()));
        userEnvironment.setEnvironmentName(edtApelidoEnv.getText().toString());
        userEnvironment.setQuantityDevices(Integer.parseInt(edtQtdDisp.getText().toString()));
        userEnvironment.setDeviceId(Integer.toString(dev.getIdDevice()));

        return userEnvironment;
    }

    private Integer[] getImgId(){
        Integer[] imgid = {
                R.drawable.lampada
        };
        return imgid;
    }

    private void setImage(){

        switch (environmentName) {
            case "Cozinha": imgEnv.setImageResource(R.drawable.cozinha);
                break;
            case "Sala de Estar": imgEnv.setImageResource(R.drawable.sala_estar);
                break;
            case "Sala de Jantar": imgEnv.setImageResource(R.drawable.sala_jantar);
                break;
            case "Quarto": imgEnv.setImageResource(R.drawable.quarto);
                break;
            case "Banheiro": imgEnv.setImageResource(R.drawable.banheiro);
                break;
            case "Garagem": imgEnv.setImageResource(R.drawable.garagem);
                break;
            case "Escritório": imgEnv.setImageResource(R.drawable.escritorio);
                break;
            case "Jardim": imgEnv.setImageResource(R.drawable.jardim);
                break;
            case "Varanda": imgEnv.setImageResource(R.drawable.varanda);
                break;
            case "Lavanderia": imgEnv.setImageResource(R.drawable.lavanderia);
                break;
        }

    }
}
