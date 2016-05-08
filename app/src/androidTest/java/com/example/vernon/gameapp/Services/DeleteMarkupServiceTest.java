package com.example.vernon.gameapp.Services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Config.Databases.Util.App;
import com.example.vernon.gameapp.Services.Impl.DeleteMarkupServiceImpl;

import junit.framework.Assert;

/**
 * Created by VERNON on 2016/05/08.
 */
public class DeleteMarkupServiceTest extends AndroidTestCase {

    private DeleteMarkupServiceImpl deleteMarkupService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), DeleteMarkupServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DeleteMarkupServiceImpl.DeleteMarkupServiceLocalBinder binder
                    = (DeleteMarkupServiceImpl.DeleteMarkupServiceLocalBinder) service;
            deleteMarkupService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testDeleteGameMarkup() throws Exception {
        Boolean delete = deleteMarkupService.deleteGames();
        Assert.assertTrue("SUCCEEDED", delete);
    }
}
