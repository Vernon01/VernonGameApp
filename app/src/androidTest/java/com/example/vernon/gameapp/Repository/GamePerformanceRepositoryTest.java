package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GamePerformanceToPrice;
import com.example.vernon.gameapp.Repository.Dom.GamePerformanceToPriceRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GamePerformanceToPriceRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GamePerformanceRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMEPERFORMANCETOPRICE TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GamePerformanceToPriceRepository repo = new GamePerformanceToPriceRepositoryImpl(this.getContext());
        // CREATE
        GamePerformanceToPrice createEntity = new GamePerformanceToPrice.Builder("UFC")
                .year("2015")
                .opinion("Good game for good value")
                .build();
        GamePerformanceToPrice insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GamePerformanceToPrice> gamePer = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gamePer.size()>0);

        //READ ENTITY
        GamePerformanceToPrice entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GamePerformanceToPrice updateEntity = new GamePerformanceToPrice.Builder("UFC")
                .copy(entity)
                .opinion("Very good value for price")
                .build();
        repo.update(updateEntity);
        GamePerformanceToPrice newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Very good value for price",newEntity.getOpinion());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GamePerformanceToPrice deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
