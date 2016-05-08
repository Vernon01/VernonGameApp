package com.example.vernon.gameapp.Services;

/**
 * Created by VERNON on 2016/05/08.
 */

//This service is to delete the games that are listed or added .. deletes

public interface DeleteMarkupService {
    String deleteGameMarkup(String title);

    boolean deleteGames();
}
