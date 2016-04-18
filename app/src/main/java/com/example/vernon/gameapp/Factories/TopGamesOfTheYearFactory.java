package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.TopGamesOfTheYear;

/**
 * Created by VERNON on 2016/04/18.
 */
public class TopGamesOfTheYearFactory {

    public static TopGamesOfTheYear TopGamesOfTheYearFactory(String title)
    {
        TopGamesOfTheYear gc = new TopGamesOfTheYear
                .Builder("Games of Wars")
                .year("2013")
                .build();
        return gc;
    }
}
