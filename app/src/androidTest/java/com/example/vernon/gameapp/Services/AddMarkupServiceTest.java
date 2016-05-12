package com.example.vernon.gameapp.Services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Config.Databases.Util.App;
import com.example.vernon.gameapp.Services.Impl.AddMarkupServiceImpl;

import junit.framework.Assert;

/**
 * Created by VERNON on 2016/05/08.
 */
public class AddMarkupServiceTest extends AndroidTestCase{

    private AddMarkupServiceImpl addMarkupService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), AddMarkupServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AddMarkupServiceImpl.AddMarkupServiceLocalBinder binder
                    = (AddMarkupServiceImpl.AddMarkupServiceLocalBinder) service;
            addMarkupService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testAddGameMarkup() throws Exception {
        String success = addMarkupService.addGameMarkup("Warzone");
        Assert.assertEquals("SUCCEEDED", success);
    }

}
