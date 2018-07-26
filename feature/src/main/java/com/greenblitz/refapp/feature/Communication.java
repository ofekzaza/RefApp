package com.greenblitz.refapp.feature;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Communication {
    /**
     * communication is a singalton which responsible for all communication with the server
     */
    public int port = 4590;
    public String ip = "192.168.8.2";
    private static boolean inite = false;
    private static Communication instance;
    private JSONObject curJson;
    private int callCannon = 0;
    private ReadTask rt; // read thread dont create two of them
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
        // the cannon have been activeted
        callCannon++;
        if(callCannon > 1){
            //there are only two cannons some people are realy stuiped
            return ;
        }
        String str = "{\"Message\":Cannon}";
        rt.writeMessage(str);
    }

    public void setJson(JSONObject json){
        curJson = json;
    }

    public int getTimeSec() throws  JSONException{
        //give the current time is seconds
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

        //hard coded works better

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

    public void writePile(ArrayList<ArrayList<Integer>> arl) throws JSONException{
        //format in the rules @ https://docs.google.com/document/d/16fq_tQZT29nXIGknlA2EAHR2dllp7476EbqBuN_RXX8/edit
        JSONObject obj = new JSONObject();
        JSONObject helper;
        JSONArray ja = new JSONArray();
        obj.put("Length", arl.size());


        //making the Arraylist an json object !

        for(int x = 0; x< arl.size(); x++){
            helper = new JSONObject();
            helper.put("Pile", x);
            helper.put("Length", arl.get(x).size());
            int h;

            for(Integer y = 0; y < helper.getInt("Length"); y++){
                h = arl.get(x).get(y);
                if (h == 0){
                    helper.put("Cargo"+y, 1);
                }else if(h == 1){
                    helper.put("Cargo"+y, 0);
                }
                else{
                    helper.put("Cargo"+y, h);
                }
            }
            ja.put(x, helper);
        }
        obj.put("Piles", ja);
        rt.writeMessage(obj.toString());
    }

}