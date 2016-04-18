package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GameCategory;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GameCategoryFactory {

    public static GameCategory GameGategoryFactory(String title)
    {
        GameCategory gc = new GameCategory
                .Builder("Games of Wars")
                .year("2013")
                .category("Action")
                .build();
        return gc;
    }
}
