package com.greenblitz.refapp.feature;

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
        readTaskInstance.getPrintStream().println(message);
        //System.out.println("sended");
    }

    public void start () {
       // System.out.println("Starting " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }
}
