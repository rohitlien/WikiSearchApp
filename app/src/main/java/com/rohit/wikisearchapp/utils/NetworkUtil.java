package com.rohit.wikisearchapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rohit.wikisearchapp.AppController;


/**
 * Created by shilpysamaddar on 07/03/17.
 */

public class NetworkUtil {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                    return TYPE_WIFI;
                if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    return TYPE_MOBILE;
            }
        }catch (Exception e){}
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        String status = "";
        try {
            if (context == null) {
                context = AppController.instance;
            }
            int conn = NetworkUtil.getConnectivityStatus(context);
            if (conn == NetworkUtil.TYPE_WIFI) {
                status = "Connected to Internet with WIFI";
            } else if (conn == NetworkUtil.TYPE_MOBILE) {
                status = "Connected to Internet with Mobile Data";
            } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
                status = "Internet connection required";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

}
