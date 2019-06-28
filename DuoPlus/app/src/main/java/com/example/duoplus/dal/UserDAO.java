package com.example.duoplus.dal;

import android.content.Context;
import android.database.Cursor;

import com.example.duoplus.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static long userCreate(Context context, User user) {
        Banco banco = new Banco(context);
        return banco.createUser(user);
    }

    public static int editUser(Context context, User user) {
        Banco banco = new Banco(context);
        return banco.editUser(user);
    }

    public static ArrayList<User> userList(Context context) {
        Banco banco = new Banco(context);
        return banco.getUsersList();
    }

    public static User userLogin(Context context, String email, String password) {
        Banco banco = new Banco(context);
        return banco.userCheckLogin(email, password);
    }

    public static User setUserPlan(Context context, String idPlan, int idUser){
        Banco banco = new Banco(context);
        return banco.setUserPlan(idPlan, idUser);

    }

    public static boolean CheckRegisterExists(Context context, String email) {
        Banco banco = new Banco(context);
        return banco.userCheckRegister(email);
    }
}
