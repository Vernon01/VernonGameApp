package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GameComparing;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GameComparingFactory {

    public static GameComparing GameComparingFactory(String title)
    {
        GameComparing gt = new GameComparing
                .Builder("Games of Wars")
                .year("2013")
                .comment("Awesome war game")
                .build();
        return gt;
    }
}
