package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadTask extends AsyncTask<String, Void, Void> {
    private Socket socket;
    private DataInputStream is;
    private BufferedReader in;
    private JSONObject json;

    @Override
    protected Void doInBackground(String ... voids){

        try{
            socket = new Socket(Communication.init().ip, Communication.init().port);
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));

            try {
                JSONObject curjson = new JSONObject(in.readLine());
                if(curjson != null) {
                    json = curjson;
                    System.out.println("curjson is something");
                    return null;
                }
                System.out.println("curjson is null");

                return null;
            }catch (JSONException y){
                y.printStackTrace();
            }
        } catch(IOException x){
            x.printStackTrace();
        }
        return null;
    }

    public JSONObject getJson() {
        return json;
    }
}
