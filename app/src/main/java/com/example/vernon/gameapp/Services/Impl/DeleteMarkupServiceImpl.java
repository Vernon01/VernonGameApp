package com.example.vernon.gameapp.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.vernon.gameapp.Config.Databases.Util.DomainState;
import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Factories.GameCategoryFactory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;
import com.example.vernon.gameapp.Services.DeleteMarkupService;

//This service is to delete the games that are listed or added

public class DeleteMarkupServiceImpl extends Service implements DeleteMarkupService {

    private final IBinder localBinder = new DeleteMarkupServiceLocalBinder();
    public GameCategoryRepository repo;

    public DeleteMarkupServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class DeleteMarkupServiceLocalBinder extends Binder {
        public DeleteMarkupServiceImpl getService(){
            return DeleteMarkupServiceImpl.this;
        }
    }

    @Override
    public String deleteGameMarkup(String title) {
        if (true) {
            GameCategory gameCat = GameCategoryFactory.GameGategoryFactory(title);
            return DomainState.SUCCEEDED.name();
        } else {
            return DomainState.FAILED.name();
        }
    }

    @Override
    public boolean deleteGames() {
        int rows = repo.deleteAll();
        return rows > 0;

    }
}
