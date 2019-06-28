package com.example.duoplus.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Integer userId;
    private String idPlan;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userCPF;
    private String userBornDate;

    private String userTest;
    private String userCEP;
    private String userAddress;
    private String userPhoneNumber;

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCEP() {
        return userCEP;
    }

    public void setUserCEP(String userCEP) {
        this.userCEP = userCEP;
    }

    public String getUserCPF() {
        return userCPF;
    }

    public void setUserCPF(String userCPF) {
        this.userCPF = userCPF;
    }

    public String getUserBornDate() {
        return userBornDate;
    }

    public void setUserBornDate(String userBornDate) {
        this.userBornDate = userBornDate;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String toString(){
        return "ID:"+getUserId()+
                "\nNome: "+getUserName()+
                "\nEmail: "+getUserEmail()+
                "\nCPF: "+getUserCPF()+
                "\nData de Nascimento: "+getUserBornDate()+
                "\nTeste: "+getUserTest()+
                "\nCEP: "+getUserCEP()+
                "\nEndereço: "+getUserAddress()+
                "\nTelefone: "+getUserPhoneNumber()+
                "\nPlano: "+getIdPlan();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getUserTest() {
        return userTest;
    }

    public void setUserTest(String userTest) {
        this.userTest = userTest;
    }
}
