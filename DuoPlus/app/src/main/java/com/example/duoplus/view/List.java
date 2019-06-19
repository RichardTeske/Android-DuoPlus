package com.example.duoplus.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.duoplus.R;
import com.example.duoplus.dal.PlanDAO;
import com.example.duoplus.dal.UserDAO;
import com.example.duoplus.dal.UserEnvironmentDAO;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;
import com.example.duoplus.model.UserEnvironment;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemList = (ListView) findViewById(R.id.itemList);

        ArrayList<UserEnvironment> list = (ArrayList<UserEnvironment>) UserEnvironmentDAO.userEnvList(this);

        ArrayAdapter<UserEnvironment> adapter = new ArrayAdapter<UserEnvironment>(this,
                android.R.layout.simple_list_item_1, list);

        itemList.setAdapter(adapter);

    }
}
