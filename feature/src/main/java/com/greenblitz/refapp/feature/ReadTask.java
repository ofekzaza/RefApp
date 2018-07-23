package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadTask extends AsyncTask<Void, Void, Void> {
    /**
     * this thread is responsible on reading from the socket
     */

    private Socket socket;
    private DataInputStream is;
    private BufferedReader in;
    private JSONObject json;
    private Communication com;
    //private MainActivity instance;

    @Override
    protected Void doInBackground(Void ... voids){
        //instance = voids[0];
        try{
            socket = new Socket(Communication.init().ip, 2212);
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));
            com = Communication.init();
            System.out.println("json reading");
            //json  = null;
            boolean comeon = true;
            while(comeon) {
                json = new JSONObject(in.readLine());
                try{
                    if(json.getString("State") != null){
                        comeon = false;
                    }
                }catch (Exception x){
                    x.printStackTrace();
                }
                System.out.println("json have read");
            }

            while (json == null || json.getString("State").toString() != "Post"){
                System.out.println("State + " + json.getString("State"));
                if(json.getString("State").toString() == "Porst"){
                    break;
                }
                com.setJson(json);
                Thread.sleep(400);
               // instance.setTime();
               // instance.buttonsUpdate();
                if(json != null) {
                    System.out.println("json " + json.toString());
                }
                System.out.println("json reading loop");
                json = new JSONObject(in.readLine());
                System.out.println("json next loop");
            }
            System.out.println("json finished?");
            com.setJson(json);
            return null;
        } catch(Exception x){
            x.printStackTrace();
        }
        return null;
    }

    public JSONObject getJson() {
        return json;
    }
}
