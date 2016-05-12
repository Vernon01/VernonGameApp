package com.example.vernon.gameapp.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.vernon.gameapp.Config.Databases.Util.DomainState;
import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Factories.GameCategoryFactory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;
import com.example.vernon.gameapp.Services.CategoryMarkupService;

//This is to Show the games by categories that are listed or added ..

public class CategoryMarkupServiceImpl extends Service implements CategoryMarkupService{

    private final IBinder localBinder = new CategoryMarkupServiceLocalBinder();
    public GameCategoryRepository repo;

    public CategoryMarkupServiceImpl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class CategoryMarkupServiceLocalBinder extends Binder {
        public CategoryMarkupServiceImpl getService(){
            return CategoryMarkupServiceImpl.this;
        }
    }

    @Override
    public String categoryGamesMarkup(String title) {
        if (true) {
            GameCategory gameCat = GameCategoryFactory.GameGategoryFactory(title);
            return DomainState.SUCCEEDED.name();
        } else {
            return DomainState.FAILED.name();
        }
    }

    @Override
    public boolean viewCategories() {
        return repo.findAll().size()>0;
    }
}
