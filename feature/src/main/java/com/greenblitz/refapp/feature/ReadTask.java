package com.greenblitz.refapp.feature;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ofeke on 7/24/2018.
 */
public class ReadTask extends Thread {
    private Socket socket;
    private DataInputStream is;
    private BufferedReader in;
    private JSONObject json;
    private Communication com;
    private Scanner scan;
    private PrintStream writer;
    private Thread thread;
    private String threadName;
    private boolean mainWhile = true;


    public ReadTask(){
        threadName = "MainCommThread";
    }

    @Override
    public void run(){
        try{
            com = Communication.init();
            socket = new Socket(com.ip, com.port);
            socket.setReuseAddress(true);
            writer = new PrintStream(socket.getOutputStream());
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));
            scan = new Scanner(is);

            while(mainWhile){
                while(!scan.hasNext()){}
                try{
                    json = new JSONObject(scan.next());
                    com.setJson(json);
                   // System.out.println("debug jsonis "+ json.toString());
                }catch(JSONException x){
                    x.printStackTrace();
                }
            }

            writer.close();
            scan.close();
            in.close();
            is.close();
        }catch(Exception x){
            x.printStackTrace();
        }
    }

    public void start () {
        //System.out.println("Starting " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }

    public void finalize(){
        try{
            writer.close();
            scan.close();
            in.close();
            is.close();
        }catch (IOException x){
            x.printStackTrace();
        }
    }

    public PrintStream getPrintStream(){
        return writer;
    }

    public void writeMessage(String mes){
        WriteTask wc = new WriteTask(mes, this);
        wc.start();
    }

    public void setOff(){
        //dont fucking use mate
        mainWhile = false;
    }

}
