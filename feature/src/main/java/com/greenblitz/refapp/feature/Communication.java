package com.greenblitz.refapp.feature;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Communication {
    public int port = 4590;
    public String ip = "192.168.8.2";
    private static boolean inite = false;
    private static Communication instance;
    private JSONObject curJson;
    private int callCannon = 0;
    private ReadTask rt;
    private int[] cargoDelta;

    private Communication() throws IOException{
        curJson = null;
        System.out.println("debug preperint to create reading tread");
        rt = new ReadTask();
        System.out.println("debug reading have been created");
        rt.start();
        System.out.println("debug reading have started");
        cargoDelta = new int[5];
    }


    public static Communication init() throws UnknownHostException, IOException{
        if(!inite) {
            instance = new Communication();
            inite = true;
        }
        return instance;
    }

    public void addCargo(Cargo car, boolean added){
        int i = new Integer(5);
        switch (car){
            case Alliance:
                i = 0;
                break;
            case Barrel:
                i = 1;
                break;
            case Box:
                i = 2;
                break;
            case Crate:
                i = 3;
                break;
            case Treasure:
                i = 4;
                break;
        }
        if(added){
            cargoDelta[i] ++;
            return;
        }
        if(cargoDelta[i] > 0)
            cargoDelta[i] --;
        return;
    }

    public int getBarrel(){
        return cargoDelta[1];
    }
    public int getAlliance(){
        return cargoDelta[0];
    }

    public int getBox(){
        return cargoDelta[2];
    }

    public int getCrate(){
        return cargoDelta[3];
    }
    public int getTreasure(){
        return cargoDelta[4];
    }

    public void writeCargo(MessageType mes, Cargo cartype){
        boolean adding = true;
        if(mes == MessageType.RemC){
            adding = false;
        }
        addCargo(cartype, adding);
        String str = "{\"Message\":"+mes.toString()+",\"CargoType\":"+cartype.toString()+"}";
        rt.writeMessage(str);
    }

    public void write(MessageType mes){
        //you write fouls and penalty's through here
        String str = "{\"Message\":"+mes.toString()+"}";
        rt.writeMessage(str);
    }

    public void write(String mes){
        String str = "{\"Message\":"+mes+"}";
        rt.writeMessage(str);
    }

    public void writeDisable(MessageType mes, String robot){
        String str = "{\"Message\":"+mes.toString()+",\"Robot\":"+robot+"}";
        rt.writeMessage(str);
    }

    public void writeTeam(String team){
        if(team != "Blue" && team != "Red"){
            return ;
        }
        System.out.println("debug Writing team");
        String str = "{\"Message\":Team,\"CargoType\":"+team+"}";
        rt.writeMessage(str);
    }

    public void writeCannon(){
        callCannon++;
        if(callCannon > 1){
            return ;
        }
        String str = "{\"Message\":Cannon}";
        rt.writeMessage(str);
    }

    public void setJson(JSONObject json){
        curJson = json;
    }

    public int getTimeSec() throws  JSONException{
        if (curJson == null){
            return 0;
        }
        if (!curJson.has("Time")){
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
        String state = curJson.getString("State");
        if(state == "Auto")
            return GameState.Auto;
        if(state == "Tele")
            return GameState.Tele;
        if(state == "Post")
            return GameState.Post;
        return GameState.Pre;
    }
    public GameState getWorkingGameState() throws JSONException{
        int t = getTimeSec();
        GameState state = GameState.Pre;
        if(t > 0)
            state = GameState.Auto;
        if(t > 15)
            state = GameState.Tele;
        if(t > 3.5*60)
            state = GameState.Post;
        return state;
    }

    public void writePile(ArrayList<ArrayList<Integer>> arr){

    }

}