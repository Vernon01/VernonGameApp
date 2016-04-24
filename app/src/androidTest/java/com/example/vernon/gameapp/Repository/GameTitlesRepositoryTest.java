package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GameTitles;
import com.example.vernon.gameapp.Repository.Dom.GameTitlesRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GameTitlesRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameTitlesRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMETITLES TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GameTitlesRepository repo = new GameTitlesRepositoryImpl(this.getContext());
        // CREATE
        GameTitles createEntity = new GameTitles.Builder("UFC")
                .year("2015")
                .machine("xbox360")
                .build();
        GameTitles insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GameTitles> gametitl = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gametitl.size()>0);

        //READ ENTITY
        GameTitles entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GameTitles updateEntity = new GameTitles.Builder("UFC")
                .copy(entity)
                .machine("PC")
                .build();
        repo.update(updateEntity);
        GameTitles newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","PC",newEntity.getMachine());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GameTitles deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
