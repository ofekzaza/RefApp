package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteTask extends AsyncTask<String, Void, Void> {
    private Socket socket;
    private PrintWriter writer;

    @Override
    protected Void doInBackground(String ... voids){

        try{
            String message = voids[0];
            socket = new Socket(Communication.init().ip, Communication.init().port);
            writer = new PrintWriter(socket.getOutputStream());

            writer.println(message);
            writer.flush();
            writer.close();

        }catch (IOException x){
            x.printStackTrace();

        }
        return null;
    }

}
