package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GamePrices;
import com.example.vernon.gameapp.Repository.Dom.GamePricesRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GamePricesRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GamePricesRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMEPRICES TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GamePricesRepository repo = new GamePricesRepositoryImpl(this.getContext());
        // CREATE
        GamePrices createEntity = new GamePrices.Builder("UFC")
                .year("2015")
                .price("800")
                .machine("xbox360")
                .build();
        GamePrices insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GamePrices> gamePri = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gamePri.size()>0);

        //READ ENTITY
        GamePrices entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GamePrices updateEntity = new GamePrices.Builder("UFC")
                .copy(entity)
                .machine("PS3")
                .build();
        repo.update(updateEntity);
        GamePrices newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","PS3",newEntity.getMachine());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GamePrices deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
