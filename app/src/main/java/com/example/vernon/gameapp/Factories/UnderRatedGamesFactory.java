package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.UnderRatedGames;

/**
 * Created by VERNON on 2016/04/18.
 */
public class UnderRatedGamesFactory {

    public static UnderRatedGames UnderRatedGamesFactory(String title)
    {
        UnderRatedGames gt = new UnderRatedGames
                .Builder("Games of Wars")
                .year("2013")
                .reason("excellent experience")
                .build();
        return gt;
    }
}
