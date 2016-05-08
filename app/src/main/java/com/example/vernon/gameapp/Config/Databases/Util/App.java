package com.example.vernon.gameapp.Config.Databases.Util;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by VERNON on 2016/05/08.
 */
public class App extends Application {

    private static Context context;

    private static App singleton;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();


    public static synchronized App getInstance() {
        return singleton;
    }


}
