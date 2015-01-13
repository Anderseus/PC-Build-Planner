package com.example.wchoi.pcbuildplanner;

import android.app.Application;

import com.parse.Parse;


/**
 * Created by 2015wchoi on 1/12/2015.
 */
public class Initialize extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "nUFPJy5mEIPfKrY2pFPm7qswpRWkgeV9N0oY3OOK", "DgZQr1pHNnTZMqFYBuah1pD4cLvZsSAP3fqxpUjh");
    }
}
