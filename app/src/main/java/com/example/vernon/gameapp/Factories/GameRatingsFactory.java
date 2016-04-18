package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GameRatings;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GameRatingsFactory {

    public static GameRatings GameRatingsFactory(String title)
    {
        GameRatings gt = new GameRatings
                .Builder("Games of Wars")
                .year("2013")
                .price("480")
                .rating(8)
                .build();
        return gt;
    }
}
