package com.nitkkr.techspardha.root.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nitkkr.techspardha.root.userPojo.Udata;
import com.nitkkr.techspardha.root.userPojo.userInfo;

public class userDataStore {
    private static userDataStore userD;
    private SharedPreferences sharedPreferences;

    public static userDataStore getInstance(Context context) {
        if (userD == null) {
            userD= new userDataStore(context);
        }
        return userD;
    }

    private userDataStore(Context context) {
        sharedPreferences = context.getSharedPreferences("userDataStore",Context.MODE_PRIVATE);
    }

    public void saveData(userInfo uData, String onboard ) {


        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(uData);
        prefsEditor.putString("MyObject", json);
        prefsEditor.putString("OnBoard",onboard);
        prefsEditor.commit();
    }

    public void changeState(String onboard){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("OnBoard",onboard);
        prefsEditor.apply();


    }
    public String getState(){
        String state=sharedPreferences.getString("OnBoard","false");
        return state;


    }


    public userInfo getData() {
        userInfo udata=new userInfo();
        if (sharedPreferences!= null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("MyObject", "");
            userInfo obj = gson.fromJson(json, userInfo.class);
            return obj;
        }
        return udata;

    }

}
