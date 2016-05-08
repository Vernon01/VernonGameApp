package com.example.vernon.gameapp.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.vernon.gameapp.Config.Databases.Util.App;
import com.example.vernon.gameapp.Config.Databases.Util.DomainState;
import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Factories.GameCategoryFactory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GameCategoryRepositoryImpl;
import com.example.vernon.gameapp.Services.AddMarkupService;

////This service is to add the games that is going to be reviewed

public class AddMarkupServiceImpl extends Service implements AddMarkupService{

    private final IBinder localBinder = new AddMarkupServiceLocalBinder();
    public GameCategoryRepository repo;

    public AddMarkupServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class AddMarkupServiceLocalBinder extends Binder {
        public AddMarkupServiceImpl getService(){
            return AddMarkupServiceImpl.this;
        }
    }

    @Override
    public String addGameMarkup(String title) {
        if (true) {
            GameCategory gameCat = GameCategoryFactory.GameGategoryFactory(title);
            return DomainState.SUCCEEDED.name();
        } else {
            return DomainState.FAILED.name();
        }
    }

    private GameCategory addGame(GameCategory gameCat) {
        repo = new GameCategoryRepositoryImpl(App.getAppContext());
        return repo.save(gameCat);
    }

}
