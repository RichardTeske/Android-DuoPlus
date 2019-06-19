package com.example.duoplus.dal;

import android.provider.BaseColumns;

import java.util.Date;

public class Contrato {
    private Contrato(){}

    public static abstract class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "tb_User";
        public static final String COLUMN_ID = "userId";
        public static final String USER_PLANID = "planId";
        public static final String USER_NAME = "userName";
        public static final String USER_CPF = "userCPF";
        public static final String USER_BORNDATE = "userBornDate";
        public static final String USER_CEP = "userCEP";
        public static final String USER_ADDRESS = "userAddress";
        public static final String USER_PHONENUMBER = "userPhoneNumber";
        public static final String USER_EMAIL = "userEmail";
        public static final String USER_PASSWORD = "userPassword";
    }

    public static abstract class PlanTable implements BaseColumns {
        public static final String TABLE_NAME = "tb_Plan";
        public static final String COLUMN_ID = "planId";
        public static final String PLAN_NAME = "planName";
        public static final String PLAN_VALUE = "planValue";
        public static final String PLAN_QUANTITY = "planQuantity";

    }

    public static abstract class EnvironmentTable implements BaseColumns {
        public static final String TABLE_NAME = "tb_Environment";
        public static final String COLUMN_ID = "environmentId";
        public static final String ENVIRONMENT_TYPE = "environmentType";
    }

    public static abstract class DevicesTable implements BaseColumns {
        public static final String TABLE_NAME = "tb_Devices";
        public static final String COLUMN_ID = "deviceId";
        public static final String DEVICE_TYPE = "deviceType";
    }

    public static abstract class UserEnvironmentTable implements BaseColumns {
        public static final String TABLE_NAME = "tb_UserEnvironment";
        public static final String COLUMN_ID = "userEnvironmentId";
        public static final String USER_ID = "userId";
        public static final String ENVIRONMENT_ID = "environmentId";
        public static final String DEVICE_ID = "deviceId";
        public static final String DEVICE_QUANTITY = "deviceQuantity";
        public static final String ENVIRONMENT_NAME = "environmentName";
    }
}
