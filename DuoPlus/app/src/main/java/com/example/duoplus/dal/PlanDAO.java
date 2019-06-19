package com.example.duoplus.dal;

import android.content.Context;

import com.example.duoplus.model.Plan;

import java.util.ArrayList;

public class PlanDAO {

    public static ArrayList<Plan> planList(Context context) {
        Banco banco = new Banco(context);
        return banco.getPlanList();
    }

    public static Plan planById(Context context, int id) {
        Banco banco = new Banco(context);
        return banco.getPlanId(id);
    }
}
