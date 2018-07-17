package com.greenblitz.refapp.feature;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by ofeke on 7/17/2018.
 */
public class ReadThread extends Thread{
    private JSONObject json = null;
    private String ip = "127.0.0.1";
    private int port = 4590;
    private Socket socket;
    private BufferedReader in;
    private DataInputStream is;

    public ReadThread(){

    }

    public JSONObject getJson(){
        return json;
    }

    public void run(){
        try{
            socket = new Socket(ip, port);
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));

            try {
                JSONObject curjson = new JSONObject(in.readLine());
                if(curjson != null) {
                    json = curjson;

                    return ;
                }
                return ;
            }catch (JSONException y){

            }
        } catch(IOException x){

        }
    }
}

