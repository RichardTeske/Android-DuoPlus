package com.example.duoplus.model;

import java.io.Serializable;

public class Plan implements Serializable {

    private int planId;
    private String planName;
    private float planValue;
    private int planQuantity;

    public int getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(int planQuantity) {
        this.planQuantity = planQuantity;
    }

    public float getPlanValue() {
        return planValue;
    }

    public void setPlanValue(float planValue) {
        this.planValue = planValue;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String toString() {
        return "Descrição: "+ getPlanName()+
                "\nValor: "+getPlanValue()+
                "\nQuantidade: "+getPlanQuantity()+
                "\nID: "+getPlanId();
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
