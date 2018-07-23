package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class readThread extends AsyncTask<Void, Void, Void> {
    /**
     * this thread is responsible on reading from the socket
     */

    private Socket socket;
    private DataInputStream is;
    private BufferedReader in;
    private JSONObject json;
    private Communication com;
    private Scanner scan;

    @Override
    protected Void doInBackground(Void ... voids) {
        try {
            socket = new Socket(Communication.init().ip, 2212);
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));
            com = Communication.init();
            scan = new Scanner(is);

            System.out.println("json have constructe sockets");

            boolean a = true;
            while (a) {
                while (!scan.hasNext()) {System.out.println("json  is waiting for the input"}

                System.out.println("json got input");

                String input = scan.next();
                System.out.println("json " + input);
                try {
                    json = new JSONObject(input);
                } catch (Exception x) {
                    //well the server is retarded, pls dont
                    x.printStackTrace();
                }
                System.out.println("State + " + json.getString("State"));
                if (json.getString("State").toString() == "Post") {
                    break;
                }
                // instance.setTime();
                // instance.buttonsUpdate();
                if (json != null) {
                    com.setJson(json);
                    System.out.println("json " + json.toString());
                }
                System.out.println("json next loop");
                Thread.sleep(400);
            }
            System.out.println("json finished?");
            com.setJson(json);
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
}
