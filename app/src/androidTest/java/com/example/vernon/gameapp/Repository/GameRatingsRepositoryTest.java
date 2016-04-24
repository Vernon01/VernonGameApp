package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GameRatings;
import com.example.vernon.gameapp.Repository.Dom.GameRatingsRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GameRatingsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameRatingsRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMERATINGS TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GameRatingsRepository repo = new GameRatingsRepositoryImpl(this.getContext());
        // CREATE
        GameRatings createEntity = new GameRatings.Builder("UFC")
                .year("2015")
                .price("800")
                .rating(9)
                .build();
        GameRatings insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GameRatings> gameRat = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gameRat.size()>0);

        //READ ENTITY
        GameRatings entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GameRatings updateEntity = new GameRatings.Builder("UFC")
                .copy(entity)
                .rating(8)
                .build();
        repo.update(updateEntity);
        GameRatings newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",8,newEntity.getRating());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GameRatings deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
