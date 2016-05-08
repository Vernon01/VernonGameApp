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
import com.example.vernon.gameapp.Services.UpdateMarkupService;

//This service updates a game that is added .. updates

public class UpdateMarkupServiceImpl extends Service implements UpdateMarkupService{

    private final IBinder localBinder = new UpdateMarkupServiceLocalBinder();
    public GameCategoryRepository repo;

    public UpdateMarkupServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class UpdateMarkupServiceLocalBinder extends Binder {
        public UpdateMarkupServiceImpl getService(){
            return UpdateMarkupServiceImpl.this;
        }
    }

    @Override
    public String updateGameMarkup(String title) {
        if (true) {
            GameCategory gameCat = GameCategoryFactory.GameGategoryFactory(title);
            return DomainState.SUCCEEDED.name();
        } else {
            return DomainState.FAILED.name();
        }
    }

    private GameCategory updateGame(GameCategory gameCat) {
        repo = new GameCategoryRepositoryImpl(App.getAppContext());
        return repo.update(gameCat);
    }
}
