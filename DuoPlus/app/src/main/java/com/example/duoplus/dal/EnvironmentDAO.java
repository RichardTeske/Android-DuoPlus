package com.example.duoplus.dal;

import android.content.Context;

import com.example.duoplus.model.Environment;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentDAO {

    public static ArrayList<String> getEnvironmentsTypes(Context context) {
        Banco banco = new Banco(context);
        return banco.getEnvironmentTypeList();
    }

    public static Environment getEnvironmentByName(Context context, String envName) {
        Banco banco = new Banco(context);
        return banco.getEnvironmentByName(envName);
    }

    public static Environment getEnvironmentById(Context context, int envId) {
        Banco banco = new Banco(context);
        return banco.getEnvironmentById(envId);
    }
}
