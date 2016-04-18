package com.example.vernon.gameapp.Factories;

import com.example.vernon.gameapp.Domain.GamePrices;

/**
 * Created by VERNON on 2016/04/18.
 */
public class GamePricesFactory {

    public static GamePrices GamePricesFactory(String title)
    {
        GamePrices gt = new GamePrices
                .Builder("Games of Wars")
                .year("2013")
                .price("480")
                .machine("xbox")
                .build();
        return gt;
    }
}
