package com.example.vernon.gameapp;

import com.example.vernon.gameapp.Factories.GameCategoryTest;
import com.example.vernon.gameapp.Factories.GameComparingTest;
import com.example.vernon.gameapp.Factories.GamePerformanceToPriceTest;
import com.example.vernon.gameapp.Factories.GamePricesTest;
import com.example.vernon.gameapp.Factories.GameRatingsTest;
import com.example.vernon.gameapp.Factories.GameTitlesTest;
import com.example.vernon.gameapp.Factories.TopGamesOfTheYearTest;
import com.example.vernon.gameapp.Factories.UnderRatedGamesTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameCategoryTest.class,
        GameComparingTest.class,
        GamePerformanceToPriceTest.class,
        GamePricesTest.class,
        GameRatingsTest.class,
        GameTitlesTest.class,
        TopGamesOfTheYearTest.class,
        UnderRatedGamesTest.class})
public class GameAppUnitTest {}