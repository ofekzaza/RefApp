package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Communication {
    private int port ;
    private String addr;
    private static boolean inite = false;
    private static Communication instance;
    private Socket socket;
    private Scanner scan;
    private PrintStream pw;
    private BufferedReader in;
    private DataOutputStream os;
    private DataInputStream is;
    private JSONObject curJson;

    private Communication() throws UnknownHostException, IOException{
        port = 4590;
        addr = "127.0.0.1";
        socket = new Socket("192.168.1.226", 4590);
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        scan = new Scanner(is);
        pw = new PrintStream(os);
        in = new BufferedReader(new InputStreamReader(is));
        curJson = null;
    }

    public static Communication init() throws UnknownHostException, IOException{
        if(!inite) {
            instance = new Communication();
            instance.write("hello");
            inite = true;
        }
        return instance;
    }

    public void writeCargo(MessageType mes, Cargo cartype){
        String str = "{\"Message\":"+mes.toString()+",\"CargoType\":"+cartype.toString()+"}";
        pw.println(str);
    }

    public void write(MessageType mes){
        String str = "{\"Message\":"+mes.toString()+"}";
        pw.println(str);
    }

    public void write(String mes){
        String str = "{\"Message\":"+mes+"}";
        pw.println(str);
    }

    public void writeDisable(MessageType mes, String robot){
        String str = "{\"Message\":"+mes.toString()+",\"Robot\":"+robot+"}";
        pw.println(str);
    }

    public void writeTeam(String team){
        if(team != "Blue" && team != "Red"){
            return ;
        }
        String str = "{\"Message\":Team,\"CargoType\":"+team+"}";
        pw.println(str);
    }

    public void writeCannon(MessageType mes, String robot){
        String str = "{\"Message\":Cannon,\"State\":Fired}";
        pw.println(str);
    }

    public JSONObject read() throws IOException, JSONException{
        JSONObject json = new JSONObject(in.readLine());
        if(json != null) {
            curJson = json;

            return json;
        }
        return null;
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

    public void finalize() throws  IOException{
        in.close();
        pw.close();
        os.close();
        scan.close();
        socket.close();
    }

    class myTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void ...params){
            return null;
        }
    }

}