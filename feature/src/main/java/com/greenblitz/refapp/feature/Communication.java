package com.greenblitz.refapp.feature;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

public class Communication {
    public int port = 4590;
    public String ip = "192.168.1.226";
    private static boolean inite = false;
    private static Communication instance;
    private JSONObject curJson;

    private Communication() throws UnknownHostException, IOException{

        curJson = null;
    }

    public static Communication init() throws UnknownHostException, IOException{
        if(!inite) {
            instance = new Communication();
            inite = true;
        }
        return instance;
    }

    public void writeCargo(MessageType mes, Cargo cartype){
        String str = "{\"Message\":"+mes.toString()+",\"CargoType\":"+cartype.toString()+"}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void write(MessageType mes){
        String str = "{\"Message\":"+mes.toString()+"}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void write(String mes){
        String str = "{\"Message\":"+mes+"}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void writeDisable(MessageType mes, String robot){
        String str = "{\"Message\":"+mes.toString()+",\"Robot\":"+robot+"}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void writeTeam(String team){
        if(team != "Blue" && team != "Red"){
            return ;
        }
        String str = "{\"Message\":Team,\"CargoType\":"+team+"}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void writeCannon(MessageType mes, String robot){
        String str = "{\"Message\":Cannon,\"State\":Fired}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public JSONObject read() throws IOException, JSONException, InterruptedException{
        ReadTask rt = new ReadTask();
        rt.execute();
        Thread.sleep(10);
        curJson = rt.getJson();
        return curJson;
    }

    public int updateTimeSec() throws  JSONException{
        if (curJson == null){
            return 0;
        }
        return curJson.getInt("Time");
    }

    public boolean updateAnchor1() throws  JSONException{
        if (curJson == null){
            return false;
        }
        if(curJson.has("Anchor1")) {
            return curJson.getBoolean("Anchor1");
        }
        return false;
    }

    public boolean updateAnchor2() throws  JSONException{
        if (curJson == null){
            return false;
        }
        if(curJson.has("Anchor2")) {
            return curJson.getBoolean("Anchor2");
        }
        return false;
    }

}