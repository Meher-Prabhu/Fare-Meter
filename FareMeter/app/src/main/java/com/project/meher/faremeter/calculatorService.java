package com.project.meher.faremeter;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/**
 * Created by Meher on 14/03/2015.
 */
public class calculatorService extends Service {

    private final static String MESSAGE_KEY = "com.project.meher.calculator.SERVICE";
    private final static int CALCULATE_START = 1;
    private final static int CALCULATE_END = 2;
    long waitTime = 0;
    Location prevLocation;
    Location currLocation;

    final Messenger mMessenger = new Messenger(new IncomingMessagesHandler());

    static class IncomingMessagesHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {

            }
            super.handleMessage(msg);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
