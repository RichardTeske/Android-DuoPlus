package com.example.duoplus.view;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duoplus.R;
import com.example.duoplus.dal.DevicesDAO;
import com.example.duoplus.dal.EnvironmentDAO;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.dal.UserEnvironmentDAO;
import com.example.duoplus.model.Devices;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.Environment;
import com.example.duoplus.model.User;
import com.example.duoplus.model.UserEnvironment;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyEnvironment extends AppCompatActivity {

    private User user;
    private Plan plan;
    private Environment env;
    private Devices dev;
    private ArrayList<UserEnvironment> userEnvironments;
    private String[] itemname;
    private Integer[] imgid;
    private String[] apelido;
    private String[] tipoDisp;
    private Integer[] quantidadeDisp;
    private Integer[] envId;

    private ListView lstMyEnv;

    private ImageButton btnLampada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_environment);

        lstMyEnv = (ListView) findViewById(R.id.lstMyEnv);
        btnLampada = (ImageButton) findViewById(R.id.btnLampada);

        init();

        final CustomListAdapter adapter = creatingViewList();
        lstMyEnv.setAdapter(adapter);

        btnLampada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    URL url = new URL("192.168.0.1/ReleOn");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        });

        lstMyEnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MyEnvironment.this);

                adb.setTitle("Excluir");
                adb.setMessage("Tem certeza que deseja excluir o Ambiente?");
                final int positionToRemove = position;
                adb.setNegativeButton("Não",null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (UserEnvironmentDAO.removeUserEnv(MyEnvironment.this, envId[position])) {
                            displayMensage("Ambiente deletado");
                        } else {
                            displayMensage("Erro ao deletar");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

                adb.show();
            }
        });

    }

    private CustomListAdapter creatingViewList(){

        itemname = new String[userEnvironments.size()];
        imgid = new Integer[userEnvironments.size()];
        apelido = new String[userEnvironments.size()];
        tipoDisp = new String[userEnvironments.size()];
        quantidadeDisp = new Integer[userEnvironments.size()];
        envId = new Integer[userEnvironments.size()];

        int i = 0;
        for (UserEnvironment ue: userEnvironments) {
            env = EnvironmentDAO.getEnvironmentById(this, Integer.parseInt(ue.getEnvironmentId()));
            dev = DevicesDAO.getDeviceById(this, Integer.parseInt(ue.getDeviceId()));

            itemname[i] = env.getTypeEnvironment();
            apelido[i] = ue.getEnvironmentName();
            tipoDisp[i] = dev.getTypeDevice();
            quantidadeDisp[i] = ue.getQuantityDevices();
            imgid = getImage(imgid, env.getTypeEnvironment(), i);
            envId[i] = ue.getUserEnvironmentId();

            i+= 1;
        }

        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid, apelido, tipoDisp, quantidadeDisp, R.layout.listview_myenvironment, "myenvironment");

        return adapter;
    }

    public void displayMensage(String mensage) {
        Toast.makeText(this, mensage,
                Toast.LENGTH_LONG).show();
    }

    private Integer[] getImage(Integer[] imgid, String imageName, int pos){

        switch (imageName) {
            case "Cozinha": imgid[pos] = R.drawable.cozinha;
                break;
            case "Sala de Estar": imgid[pos] = R.drawable.sala_estar;
                break;
            case "Sala de Jantar": imgid[pos] = R.drawable.sala_jantar;
                break;
            case "Quarto": imgid[pos] = R.drawable.quarto;
                break;
            case "Banheiro": imgid[pos] = R.drawable.banheiro;
                break;
            case "Garagem": imgid[pos] = R.drawable.garagem;
                break;
            case "Escritório": imgid[pos] = R.drawable.escritorio;
                break;
            case "Jardim": imgid[pos] = R.drawable.jardim;
                break;
            case "Varanda": imgid[pos] = R.drawable.varanda;
                break;
            case "Lavanderia": imgid[pos] = R.drawable.lavanderia;
                break;
        }
        return imgid;
    }

    private void init(){
        user = (User) getIntent().getSerializableExtra("objectUser");
        plan = (Plan) PlanDAO.planById(this, Integer.parseInt(user.getIdPlan()));
        userEnvironments = (ArrayList<UserEnvironment>) UserEnvironmentDAO.userEnvByIdUser(this, user.getUserId());
    }
}
