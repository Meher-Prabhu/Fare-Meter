package com.project.meher.faremeter;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Meher on 14/03/2015.
 */
public class calculatorService extends IntentService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public calculatorService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {



    }
}
