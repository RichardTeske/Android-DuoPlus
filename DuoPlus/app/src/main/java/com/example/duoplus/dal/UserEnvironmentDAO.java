package com.example.duoplus.dal;

import android.content.Context;

import com.example.duoplus.model.User;
import com.example.duoplus.model.UserEnvironment;

import java.util.ArrayList;

public class UserEnvironmentDAO {

    public static void insertAllUserEnv(Context context, UserEnvironment userEnv) {
        Banco banco = new Banco(context);
        banco.insertAllUserEnv(userEnv);
    }

    public static boolean removeUserEnv(Context context, int userEnvId) {
        Banco banco = new Banco(context);
        return banco.deleteUserEnv(userEnvId);
    }


    public static ArrayList<UserEnvironment> userEnvList(Context context) {
        Banco banco = new Banco(context);
        return banco.getUsersEnvList();
    }

    public static ArrayList<UserEnvironment> userEnvByIdUser(Context context, int idUser) {
        Banco banco = new Banco(context);
        return banco.userEnvByIdUser(idUser);
    }
}
