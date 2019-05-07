package com.example.duoplus;

import java.util.Date;

class User {
    private String userEmail;
    private String userPassword;
    private int userCPF;
    private Date userBornDate;
    private int userCEP;
    private String userAddress;
    private int userAddressNumber;
    private String userComplement;

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

    public String getUserComplement() {
        return userComplement;
    }

    public void setUserComplement(String userComplement) {
        this.userComplement = userComplement;
    }

    public int getUserAddressNumber() {
        return userAddressNumber;
    }

    public void setUserAddressNumber(int userAddressNumber) {
        this.userAddressNumber = userAddressNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserCEP() {
        return userCEP;
    }

    public void setUserCEP(int userCEP) {
        this.userCEP = userCEP;
    }

    public int getUserCPF() {
        return userCPF;
    }

    public void setUserCPF(int userCPF) {
        this.userCPF = userCPF;
    }

    public Date getUserBornDate() {
        return userBornDate;
    }

    public void setUserBornDate(Date userBornDate) {
        this.userBornDate = userBornDate;
    }
}
