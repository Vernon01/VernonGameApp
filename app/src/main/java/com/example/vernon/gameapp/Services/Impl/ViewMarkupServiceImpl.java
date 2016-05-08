package com.example.vernon.gameapp.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.vernon.gameapp.Config.Databases.Util.DomainState;
import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Factories.GameCategoryFactory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;
import com.example.vernon.gameapp.Services.ViewMarkupServices;

public class ViewMarkupServiceImpl extends Service implements ViewMarkupServices{

    private final IBinder localBinder = new ViewMarkupServiceLocalBinder();
    public GameCategoryRepository repo;


    public ViewMarkupServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class ViewMarkupServiceLocalBinder extends Binder {
        public ViewMarkupServiceImpl getService(){
            return ViewMarkupServiceImpl.this;
        }
    }

    @Override
    public String viewGameMarkup(String title) {
        if (true) {
            GameCategory gameCat = GameCategoryFactory.GameGategoryFactory(title);
            return DomainState.SUCCEEDED.name();
        } else {
            return DomainState.FAILED.name();
        }
    }

    @Override
    public boolean viewGames() {
        return repo.findAll().size()>0;
    }
}
