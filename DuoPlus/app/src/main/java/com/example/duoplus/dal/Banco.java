package com.example.duoplus.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.example.duoplus.model.Devices;
import com.example.duoplus.model.Environment;
import com.example.duoplus.model.Plan;
import com.example.duoplus.model.User;
import com.example.duoplus.model.UserEnvironment;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class Banco extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT ";
    private static final String INTEGER_TYPE = " INTEGER ";
    private static final String DECIMAL_TYPE = " DECIMAL(5,2) ";
    private static final String VIRGULA = ", ";

    private static final String QUERY_CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + Contrato.UserTable.TABLE_NAME + " (" +
                    Contrato.UserTable.COLUMN_ID + INTEGER_TYPE + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.UserTable.USER_PLANID + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_NAME + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_CPF + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_BORNDATE + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_CEP + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_TEST + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_ADDRESS + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_PHONENUMBER + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_EMAIL + TEXT_TYPE + VIRGULA +
                    Contrato.UserTable.USER_PASSWORD + TEXT_TYPE + ")";

    private static final String QUERY_CREATE_PLAN_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ Contrato.PlanTable.TABLE_NAME + " (" +
                    Contrato.PlanTable.COLUMN_ID + INTEGER_TYPE + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.PlanTable.PLAN_NAME + TEXT_TYPE + VIRGULA +
                    Contrato.PlanTable.PLAN_VALUE + DECIMAL_TYPE + VIRGULA +
                    Contrato.PlanTable.PLAN_QUANTITY + INTEGER_TYPE + ")";

    private static final String QUERY_CREATE_ENVIRONMENT_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ Contrato.EnvironmentTable.TABLE_NAME + " (" +
                    Contrato.EnvironmentTable.COLUMN_ID + INTEGER_TYPE + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.EnvironmentTable.ENVIRONMENT_TYPE + TEXT_TYPE +")";

    private static final String QUERY_CREATE_DEVICE_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ Contrato.DevicesTable.TABLE_NAME + " (" +
                    Contrato.DevicesTable.COLUMN_ID + INTEGER_TYPE + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.DevicesTable.DEVICE_TYPE + TEXT_TYPE+ ")";

    private static final String QUERY_CREATE_USER_ENVIRONMENT_TABLE =
            "CREATE TABLE IF NOT EXISTS "+ Contrato.UserEnvironmentTable.TABLE_NAME + " (" +
                    Contrato.UserEnvironmentTable.COLUMN_ID + INTEGER_TYPE + "PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    Contrato.UserEnvironmentTable.USER_ID + TEXT_TYPE + VIRGULA +
                    Contrato.UserEnvironmentTable.ENVIRONMENT_NAME + TEXT_TYPE + VIRGULA +
                    Contrato.UserEnvironmentTable.DEVICE_QUANTITY + INTEGER_TYPE + VIRGULA +
                    Contrato.UserEnvironmentTable.DEVICE_ID + TEXT_TYPE + VIRGULA +
                    Contrato.UserEnvironmentTable.ENVIRONMENT_ID + TEXT_TYPE+ ")";

    private static  final String QUERY_INSERT_PLAN_TABLE =
            "INSERT INTO "+Contrato.PlanTable.TABLE_NAME+" ("+
            Contrato.PlanTable.PLAN_NAME + VIRGULA +
            Contrato.PlanTable.PLAN_VALUE + VIRGULA +
            Contrato.PlanTable.PLAN_QUANTITY+") VALUES ('Avançado', 50, 15), ('Intermédio', 35, 10), ('Básico', 20, 5)";

    private static final String QUERY_INSERT_ENVIRONMENT_TABLE =
            "INSERT INTO "+Contrato.EnvironmentTable.TABLE_NAME+" ("+
                    Contrato.EnvironmentTable.ENVIRONMENT_TYPE+") VALUES ('Cozinha')," +
                    "('Sala de Estar')," +
                    "('Sala de Jantar')," +
                    "('Quarto')," +
                    "('Banheiro'),"+
                    "('Garagem')," +
                    "('Escritório')," +
                    "('Jardim')," +
                    "('Varanda')," +
                    "('Lavanderia')";

    private static final String QUERY_INSERT_DEVICE_TABLE =
            "INSERT INTO "+Contrato.DevicesTable.TABLE_NAME+" ("+
                    Contrato.DevicesTable.DEVICE_TYPE+") VALUES ('Lâmpada')";

    private static final String QUERY_DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + Contrato.UserTable.TABLE_NAME;

    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("CREATE TABLES", QUERY_CREATE_USER_TABLE);
        db.execSQL(QUERY_CREATE_USER_TABLE);
        db.execSQL(QUERY_CREATE_PLAN_TABLE);
        db.execSQL(QUERY_CREATE_ENVIRONMENT_TABLE);
        db.execSQL(QUERY_CREATE_DEVICE_TABLE);
        db.execSQL(QUERY_CREATE_USER_ENVIRONMENT_TABLE);

        db.execSQL(QUERY_INSERT_PLAN_TABLE);
        db.execSQL(QUERY_INSERT_ENVIRONMENT_TABLE);
        db.execSQL(QUERY_INSERT_DEVICE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("UPDATE TABLES", QUERY_DELETE_USER_TABLE);
        db.execSQL(QUERY_DELETE_USER_TABLE);
    }

    public long createUser(User user){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contrato.UserTable.USER_NAME, user.getUserName());
        values.put(Contrato.UserTable.USER_CPF, user.getUserCPF());
        values.put(Contrato.UserTable.USER_BORNDATE, user.getUserBornDate());
        values.put(Contrato.UserTable.USER_CEP, user.getUserCEP());
        values.put(Contrato.UserTable.USER_ADDRESS, user.getUserAddress());
        values.put(Contrato.UserTable.USER_PHONENUMBER, user.getUserPhoneNumber());
        values.put(Contrato.UserTable.USER_TEST, user.getUserTest());
        values.put(Contrato.UserTable.USER_EMAIL, user.getUserEmail());
        values.put(Contrato.UserTable.USER_PASSWORD, user.getUserPassword());
        values.put(Contrato.UserTable.USER_PLANID, user.getIdPlan());

        return db.insert(Contrato.UserTable.TABLE_NAME, null, values);

    }

    public int editUser(User user) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Contrato.UserTable.USER_NAME, user.getUserName());
        values.put(Contrato.UserTable.USER_CPF, user.getUserCPF());
        values.put(Contrato.UserTable.USER_BORNDATE, user.getUserBornDate());
        values.put(Contrato.UserTable.USER_CEP, user.getUserCEP());
        values.put(Contrato.UserTable.USER_ADDRESS, user.getUserAddress());
        values.put(Contrato.UserTable.USER_PHONENUMBER, user.getUserPhoneNumber());
        values.put(Contrato.UserTable.USER_TEST, user.getUserTest());
        values.put(Contrato.UserTable.USER_EMAIL, user.getUserEmail());
        values.put(Contrato.UserTable.USER_PASSWORD, user.getUserPassword());
        values.put(Contrato.UserTable.USER_PLANID, user.getIdPlan());

        return db.update(Contrato.UserTable.TABLE_NAME, values, Contrato.UserTable.COLUMN_ID+"="+user.getUserId(), null);
    }

    public User setUserPlan(String idPlan, int idUser){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contrato.UserTable.USER_PLANID, idPlan);

        db.update(Contrato.UserTable.TABLE_NAME, cv, Contrato.UserTable.COLUMN_ID+"="+idUser, null);

        return getUserId(idUser);
    }

    public boolean userCheckRegister(String email) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT userId FROM tb_User WHERE userEmail = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }

    }

    public User userCheckLogin(String email, String password){
        SQLiteDatabase db = getReadableDatabase();
        User u;
        String query = "SELECT userId, " +
                "userName, " +
                "userCPF, " +
                "userBornDate, " +
                "userCEP, " +
                "userAddress, " +
                "userPhoneNumber, " +
                "userTest, "+
                "userEmail, " +
                "userPassword, " +
                "planId " +
                "FROM tb_User WHERE userEmail = ? AND userPassword = ? ";

        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            u = new User();
            u.setUserId(cursor.getInt(0));
            u.setUserName(cursor.getString(1));
            u.setUserCPF(cursor.getString(2));
            u.setUserBornDate(cursor.getString(3));
            u.setUserCEP(cursor.getString(4));
            u.setUserAddress(cursor.getString(5));
            u.setUserPhoneNumber(cursor.getString(6));
            u.setUserTest(cursor.getString(7));
            u.setUserEmail(cursor.getString(8));
            u.setUserPassword(cursor.getString(9));
            u.setIdPlan(cursor.getString(10));

        } else {
            u = null;
        }
        cursor.close();
        db.close();

        return u;
    }

    public ArrayList<String> getEnvironmentTypeList() {
        ArrayList<String> env = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String[] colunas = {
                Contrato.EnvironmentTable.ENVIRONMENT_TYPE
        };

        Cursor cursor = db.query(Contrato.EnvironmentTable.TABLE_NAME,colunas,
                null, null, null, null, null);

        cursor.moveToFirst();

        do {
            env.add(cursor.getString(0));
        } while (cursor.moveToNext());

        return env;
    }

    public ArrayList<UserEnvironment> userEnvByIdUser(int userId) {
        UserEnvironment userEnv;
        ArrayList<UserEnvironment> users = new ArrayList<UserEnvironment>();
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT userEnvironmentId, "+
                "userId, "+
                "environmentId, "+
                "deviceId, "+
                "deviceQuantity, "+
                "environmentName "+
                "FROM tb_UserEnvironment WHERE userId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{Integer.toString(userId)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                userEnv = new UserEnvironment();
                userEnv.setUserEnvironmentId(cursor.getInt(0));
                userEnv.setUserId(cursor.getString(1));
                userEnv.setEnvironmentId(cursor.getString(2));
                userEnv.setDeviceId(cursor.getString(3));
                userEnv.setQuantityDevices(cursor.getInt(4));
                userEnv.setEnvironmentName(cursor.getString(5));

                users.add(userEnv);
            }while (cursor.moveToNext());
        }

        return users;
    }

    public ArrayList<UserEnvironment> getUsersEnvList(){
        ArrayList<UserEnvironment> users = new ArrayList<UserEnvironment>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                Contrato.UserEnvironmentTable.COLUMN_ID,
                Contrato.UserEnvironmentTable.USER_ID,
                Contrato.UserEnvironmentTable.DEVICE_ID,
                Contrato.UserEnvironmentTable.ENVIRONMENT_ID,
                Contrato.UserEnvironmentTable.ENVIRONMENT_NAME,
                Contrato.UserEnvironmentTable.DEVICE_QUANTITY
        };

        Cursor cursor = db.query(Contrato.UserEnvironmentTable.TABLE_NAME,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                UserEnvironment u = new UserEnvironment();
                u.setUserEnvironmentId(cursor.getInt(0));
                u.setUserId(cursor.getString(1));
                u.setDeviceId(cursor.getString(2));
                u.setEnvironmentId(cursor.getString(3));
                u.setEnvironmentName(cursor.getString(4));
                u.setQuantityDevices(cursor.getInt(5));

                users.add(u);
            } while (cursor.moveToNext());
        }
        return users;
    }

    public ArrayList<User> getUsersList(){
        ArrayList<User> users = new ArrayList<User>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                Contrato.UserTable.COLUMN_ID,
                Contrato.UserTable.USER_NAME,
                Contrato.UserTable.USER_CPF,
                Contrato.UserTable.USER_BORNDATE,
                Contrato.UserTable.USER_CEP,
                Contrato.UserTable.USER_ADDRESS,
                Contrato.UserTable.USER_PHONENUMBER,
                Contrato.UserTable.USER_TEST,
                Contrato.UserTable.USER_EMAIL,
                Contrato.UserTable.USER_PASSWORD,
                Contrato.UserTable.USER_PLANID
        };

        Cursor cursor = db.query(Contrato.UserTable.TABLE_NAME,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                User u = new User();
                u.setUserId(cursor.getInt(0));
                u.setUserName(cursor.getString(1));
                u.setUserCPF(cursor.getString(2));
                u.setUserBornDate(cursor.getString(3));
                u.setUserCEP(cursor.getString(4));
                u.setUserAddress(cursor.getString(5));
                u.setUserPhoneNumber(cursor.getString(6));
                u.setUserTest(cursor.getString(7));
                u.setUserEmail(cursor.getString(8));
                u.setUserPassword(cursor.getString(9));
                u.setIdPlan(cursor.getString(10));

                users.add(u);
            } while (cursor.moveToNext());
        }
        return users;
    }

    public ArrayList<String> getDeviceTypeList(){

        ArrayList<String> devices = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                Contrato.DevicesTable.DEVICE_TYPE
        };

        Cursor cursor = db.query(Contrato.DevicesTable.TABLE_NAME,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();

       do {
           devices.add(cursor.getString(0));
       } while (cursor.moveToNext());

        return devices;
    }

    public Environment getEnvironmentById(int envId) {
        SQLiteDatabase db = getReadableDatabase();
        Environment env = new Environment();

        String query = "SELECT environmentId, " +
                "environmentType " +
                "FROM tb_Environment WHERE environmentId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(envId)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            env.setIdEnvironment(cursor.getInt(0));
            env.setTypeEnvironment(cursor.getString(1));

        } else {
            env = null;
        }
        cursor.close();
        db.close();

        return env;
    }

    public Environment getEnvironmentByName(String envName) {
        SQLiteDatabase db = getReadableDatabase();
        Environment env = new Environment();

        String query = "SELECT environmentId, " +
                "environmentType " +
                "FROM tb_Environment WHERE environmentType = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(envName)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            env.setIdEnvironment(cursor.getInt(0));
            env.setTypeEnvironment(cursor.getString(1));

        } else {
            env = null;
        }
        cursor.close();
        db.close();

        return env;
    }

    public boolean deleteUserEnv(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(Contrato.UserEnvironmentTable.TABLE_NAME, "userEnvironmentId = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public void insertAllUserEnv(UserEnvironment userEnv) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contrato.UserEnvironmentTable.USER_ID, userEnv.getUserId());
        cv.put(Contrato.UserEnvironmentTable.DEVICE_ID, userEnv.getDeviceId());
        cv.put(Contrato.UserEnvironmentTable.ENVIRONMENT_ID, userEnv.getEnvironmentId());
        cv.put(Contrato.UserEnvironmentTable.ENVIRONMENT_NAME, userEnv.getEnvironmentName());
        cv.put(Contrato.UserEnvironmentTable.DEVICE_QUANTITY, userEnv.getQuantityDevices());

        db.insert(Contrato.UserEnvironmentTable.TABLE_NAME, null, cv);

    }

    public Devices getDeviceById(int devId) {
        SQLiteDatabase db = getReadableDatabase();
        Devices dev = new Devices();

        String query = "SELECT deviceId, " +
                "deviceType " +
                "FROM tb_Devices WHERE deviceId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(devId)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            dev.setIdDevice(cursor.getInt(0));
            dev.setTypeDevice(cursor.getString(1));

        } else {
            dev = null;
        }
        cursor.close();
        db.close();

        return dev;
    }

    public Devices getDeviceByName(String devName) {
        SQLiteDatabase db = getReadableDatabase();
        Devices dev = new Devices();

        String query = "SELECT deviceId, " +
                "deviceType " +
                "FROM tb_Devices WHERE deviceType = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(devName)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            dev.setIdDevice(cursor.getInt(0));
            dev.setTypeDevice(cursor.getString(1));

        } else {
            dev = null;
        }
        cursor.close();
        db.close();

        return dev;
    }

    public Plan getPlanId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Plan p;
        String query = "SELECT planId, " +
                "planName, " +
                "planValue, " +
                "planQuantity " +
                "FROM tb_Plan WHERE planId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            p = new Plan();
            p.setPlanId(cursor.getInt(0));
            p.setPlanName(cursor.getString(1));
            p.setPlanValue(cursor.getFloat(2));
            p.setPlanQuantity(cursor.getInt(3));
        } else {
            p = null;
        }
        cursor.close();
        db.close();

        return p;
    }

    public User getUserId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        User u = new User();
        String query = "SELECT userId, " +
                "userName, " +
                "userCPF, " +
                "userBornDate, " +
                "userCEP, " +
                "userAddress, " +
                "userPhoneNumber, " +
                "userTest, "+
                "userEmail, " +
                "userPassword, " +
                "planId " +
                "FROM tb_User WHERE userId = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            u.setUserId(cursor.getInt(0));
            u.setUserName(cursor.getString(1));
            u.setUserCPF(cursor.getString(2));
            u.setUserBornDate(cursor.getString(3));
            u.setUserCEP(cursor.getString(4));
            u.setUserAddress(cursor.getString(5));
            u.setUserPhoneNumber(cursor.getString(6));
            u.setUserTest(cursor.getString(7));
            u.setUserEmail(cursor.getString(8));
            u.setUserPassword(cursor.getString(9));
            u.setIdPlan(cursor.getString(10));
        } else {
            u = null;
        }
        cursor.close();
        db.close();

        return u;
    }

    public ArrayList<Plan> getPlanList(){
        ArrayList<Plan> plans = new ArrayList<Plan>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                Contrato.PlanTable.COLUMN_ID,
                Contrato.PlanTable.PLAN_NAME,
                Contrato.PlanTable.PLAN_VALUE,
                Contrato.PlanTable.PLAN_QUANTITY
        };

        Cursor cursor = db.query(Contrato.PlanTable.TABLE_NAME,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            do {
                Plan p = new Plan();
                p.setPlanId(cursor.getInt(0));
                p.setPlanName(cursor.getString(1));
                p.setPlanValue(cursor.getFloat(2));
                p.setPlanQuantity(cursor.getInt(3));

                plans.add(p);
            } while (cursor.moveToNext());
        }
        return plans;
    }
}
