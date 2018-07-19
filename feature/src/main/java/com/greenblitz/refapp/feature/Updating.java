package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Updating extends AsyncTask <MainActivity, Void, Void> {
    //this thread is responsible for updating time and anchor state, the thread reads every 0.2 sec from the socket

    private Socket socket;
    private DataInputStream is;
    private BufferedReader in;

    @Override
    protected Void doInBackground(MainActivity... voids) {
        MainActivity instance = voids[0];
        try {
            //creating all instance in order to read from socket
            socket = new Socket(Communication.init().ip, Communication.init().port);
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));
            Communication com = Communication.init();

            //until the game is ended this thread works
            while(com.getGameState() != GameState.Post) {
                com.setJson(read(in));
                //if game havn't stated yet wait and read
                while (com.getGameState() != GameState.Pre) {
                    Thread.sleep(200);
                    com.setJson(read(in));
                }
                //game have started update time and buttons
                instance.setTime();
                instance.buttonsUpdate();
                Thread.sleep(200);
            }

        }catch(Exception x){
            x.printStackTrace();
        }
        return null;
    }

    private JSONObject read(BufferedReader br) throws  Exception{
        JSONObject curjson = new JSONObject(br.readLine());
        if(curjson != null) {
            return curjson;
        }
        return  null;
    }
}
