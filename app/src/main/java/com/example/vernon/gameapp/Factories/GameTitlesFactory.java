package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GameTitles;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GameTitlesFactory {

    public static GameTitles GameTitlesFactory(String title)
    {
        GameTitles gt = new GameTitles
                .Builder("Games of Wars")
                .year("2013")
                .machine("PlayStation2")
                .build();
        return gt;
    }
}
