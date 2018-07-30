package com.greenblitz.refapp.feature;

import java.io.IOException;

/**
 * Created by ofeke on 7/24/2018.
 */
public class WriteTask extends Thread {
    private Thread thread;
    private String threadName;
    private ReadTask readTaskInstance;
    private String message;

    public WriteTask(String mes, ReadTask coo){
        readTaskInstance = coo;
        threadName = "WriteThread";
        message = mes;
    }

    @Override
    public void run(){
        try {
            System.out.println("debug sending data "+message);
            readTaskInstance.getPrintStream().println(message);
            System.out.println("debug sended data "+message);
        } catch (IOException x){
            x.printStackTrace();
        }
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start();
        }
    }
}
