package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.TopGamesOfTheYear;
import com.example.vernon.gameapp.Repository.Dom.Impl.TopGamesOfTheYearRepositoryImpl;
import com.example.vernon.gameapp.Repository.Dom.TopGamesOfTheYearRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class TopGamesOfTheYearRepositoryTest extends AndroidTestCase {

    private static final String TAG = "TOPGAMESOFTHEYEAR TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        TopGamesOfTheYearRepository repo = new TopGamesOfTheYearRepositoryImpl(this.getContext());
        // CREATE
        TopGamesOfTheYear createEntity = new TopGamesOfTheYear.Builder("UFC")
                .year("2015")
                .build();
        TopGamesOfTheYear insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<TopGamesOfTheYear> topGames = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",topGames.size()>0);

        //READ ENTITY
        TopGamesOfTheYear entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        TopGamesOfTheYear updateEntity = new TopGamesOfTheYear.Builder("UFC")
                .copy(entity)
                .year("2016")
                .build();
        repo.update(updateEntity);
        TopGamesOfTheYear newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","2016",newEntity.getYear());

        // DELETE ENTITY
        repo.delete(updateEntity);
        TopGamesOfTheYear deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
