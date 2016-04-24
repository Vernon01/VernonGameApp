package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.UnderRatedGames;
import com.example.vernon.gameapp.Repository.Dom.Impl.UnderRatedGamesOfTheYearRepositoryImpl;
import com.example.vernon.gameapp.Repository.Dom.UnderRatedGamesRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class UnderRatedGamesRepositoryTest extends AndroidTestCase {

    private static final String TAG = "UNDER_RATEDGAMES TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        UnderRatedGamesRepository repo = new UnderRatedGamesOfTheYearRepositoryImpl(this.getContext());
        // CREATE
        UnderRatedGames createEntity = new UnderRatedGames.Builder("UFC")
                .year("2015")
                .build();
        UnderRatedGames insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<UnderRatedGames> underRat = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",underRat.size()>0);

        //READ ENTITY
        UnderRatedGames entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        UnderRatedGames updateEntity = new UnderRatedGames.Builder("UFC")
                .copy(entity)
                .year("2016")
                .build();
        repo.update(updateEntity);
        UnderRatedGames newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","2016",newEntity.getYear());

        // DELETE ENTITY
        repo.delete(updateEntity);
        UnderRatedGames deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
