package com.greenblitz.refapp.feature;

import android.widget.Toast;

import org.json.JSONArray;
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
    private int callCannon = 0;

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
        //you write fouls and penalty's through here
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

    public void writeCannon(){
        callCannon++;
        if(callCannon > 1){
            return ;
        }
        String str = "{\"Message\":Cannon}";
        WriteTask wt  = new WriteTask();
        wt.execute(str);
    }

    public void setJson(JSONObject json){
        curJson = json;
    }

    public int getTimeSec() throws  JSONException{
        if (curJson == null){
            return 0;
        }
        return curJson.getInt("Time");
    }

    public boolean getAnchor1State() throws  JSONException{
        //return the current state if anchor 1 true pressed, false unpressed
        if (curJson == null){
            return false;
        }
        if(curJson.has("Anchor1")) {
            return curJson.getBoolean("Anchor1");
        }
        return false;
    }

    public boolean getAnchor2State() throws  JSONException{
        //return the current state if anchor 2 true pressed, false unpressed
        if (curJson == null){
            return false;
        }
        if(curJson.has("Anchor2")) {
            return curJson.getBoolean("Anchor2");
        }
        return false;
    }
    public GameState getGameState() throws  JSONException{
        //return the current state of the game
        if (curJson == null){
            return GameState.Pre;
        }
        if(curJson.has("Anchor2")) {
            String state = curJson.getString("State");
            if(state == "Auto")
                return GameState.Auto;
            if(state == "Tele")
                return GameState.Tele;
            if(state == "Post")
                return GameState.Post;
        }
        return GameState.Pre;
    }


}