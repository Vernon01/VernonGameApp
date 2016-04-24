package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GameComparing;
import com.example.vernon.gameapp.Repository.Dom.GameComparingRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GameComparingRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameComparingRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMECOMPARING TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GameComparingRepository repo = new GameComparingRepositoryImpl(this.getContext());
        // CREATE
        GameComparing createEntity = new GameComparing.Builder("UFC")
                .year("2015")
                .comment("Great fighting game experience")
                .build();
        GameComparing insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GameComparing> gameComp = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gameComp.size()>0);

        //READ ENTITY
        GameComparing entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GameComparing updateEntity = new GameComparing.Builder("UFC")
                .copy(entity)
                .comment("Turns out better than expected")
                .build();
        repo.update(updateEntity);
        GameComparing newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Turns out better than expected",newEntity.getComment());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GameComparing deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
