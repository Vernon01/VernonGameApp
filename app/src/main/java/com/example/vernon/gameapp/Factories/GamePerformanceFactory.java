package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GamePerformanceToPrice;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GamePerformanceFactory {

    public static GamePerformanceToPrice GamePerformanceFactory(String title)
    {
        GamePerformanceToPrice gt = new GamePerformanceToPrice
                .Builder("Games of Wars")
                .year("2013")
                .opinion("pay the price for a good game")
                .build();
        return gt;
    }
}
