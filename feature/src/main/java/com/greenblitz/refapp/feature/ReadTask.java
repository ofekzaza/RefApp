package com.greenblitz.refapp.feature;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ofeke on 7/24/2018.
 */
public class ReadTask extends Thread {
    /**
     * this thread is responsible for reading data from the server, it is working but on slow devices like mine we have a delay
     */
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
            if (writer == null) {
                com = Communication.init();
                socket = new Socket("192.168.1.226", 4590);
                socket.setReuseAddress(true);
                writer = new PrintStream(socket.getOutputStream());
            }
            is = new DataInputStream(socket.getInputStream());
            in = new BufferedReader(new InputStreamReader(is));
            scan = new Scanner(is);

            while(mainWhile){
                while(!scan.hasNext()){Thread.sleep(10);}//waiting for input
                try{//got input
                    //System.out.println("debug json got input");
                    json = new JSONObject(scan.next());
                   // System.out.println("debug json what is it");
                    com.setJson(json);
                    System.out.println("debug jsonis "+ json.toString());
                }catch(JSONException x){ //well someone is realy stuiped
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

    public PrintStream getPrintStream() throws  IOException{
        if(writer == null){
            com = Communication.init();
            socket = new Socket("192.168.1.226", 4590);
            socket.setReuseAddress(true);
            writer = new PrintStream(socket.getOutputStream());
        }
        System.out.println("writer got sended");
        return writer;
    }

    public void writeMessage(String mes){
        //with this you are writing messages to the server cause im using only one socket
        WriteTask wc = new WriteTask(mes, this);
        System.out.println("debug starting writing");
        wc.start();
    }

    public void setOff(){
        //dont fucking use mate
        mainWhile = false;
    }


    public void finalize(){
        try{
            writer.close();
            scan.close();
            in.close();
            is.close();
        }catch (IOException x){// who the hell used set OFF!
            x.printStackTrace();
        }
    }


}
