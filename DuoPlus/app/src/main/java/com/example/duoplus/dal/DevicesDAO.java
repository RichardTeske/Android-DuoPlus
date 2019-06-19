package com.example.duoplus.dal;

import android.content.Context;

import com.example.duoplus.model.Devices;

import java.util.ArrayList;

public class DevicesDAO {

    public static ArrayList<String> getDeviceTypes(Context context) {
        Banco banco = new Banco(context);
        return banco.getDeviceTypeList();
    }

    public static Devices getDeviceByName(Context context, String devName) {
        Banco banco = new Banco(context);
        return banco.getDeviceByName(devName);
    }

    public static Devices getDeviceById(Context context, int devId) {
        Banco banco = new Banco(context);
        return banco.getDeviceById(devId);
    }
}
