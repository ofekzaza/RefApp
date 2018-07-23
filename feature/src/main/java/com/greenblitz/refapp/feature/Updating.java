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

    @Override
    @Deprecated
    protected Void doInBackground(MainActivity... voids) {
        return null;
    }

}
