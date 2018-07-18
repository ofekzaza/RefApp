package com.greenblitz.refapp.feature;

import android.os.AsyncTask;

import java.io.IOException;

public class Updating extends AsyncTask <Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Communication com = Communication.init();
            if (com.getGameState() == GameState.Post) {
                return null;
            }
            com.read();
            Thread.sleep(100);
        }catch(Exception x){
            x.printStackTrace();
        }
        return doInBackground();
    }
}
