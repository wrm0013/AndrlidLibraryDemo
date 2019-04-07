package com.example.wangruiming.myapplication.http;

public class ServiceConfig {

    public static String getService(){

        String baseUrl = "";

        switch (Config.mode){
            case UAT:
                baseUrl = "https://tstmobile.gqichina.net/app/";
                break;
            case DEBUG:
                baseUrl = "https://tstmobile.gqichina.net/app/";
                break;
            case RELEASE:
                baseUrl = "https://mobile.gqget.com/app/";
                break;
        }
        return baseUrl;
    }

}
