package com.example.vernon.gameapp.Repository;

import android.test.AndroidTestCase;

import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;
import com.example.vernon.gameapp.Repository.Dom.Impl.GameCategoryRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameCategoryRepositoryTest extends AndroidTestCase {

    private static final String TAG = "GAMECATEGORY TEST";
    private Integer id;

    public void testCreateReadUpdateDelete() throws Exception {

        GameCategoryRepository repo = new GameCategoryRepositoryImpl(this.getContext());
        // CREATE
        GameCategory createEntity = new GameCategory.Builder("UFC")
                .year("2015")
                .category("fighting")
                .build();
        GameCategory insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<GameCategory> gameCat = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",gameCat.size()>0);

        //READ ENTITY
        GameCategory entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        GameCategory updateEntity = new GameCategory.Builder("UFC")
                .copy(entity)
                .category("Action")
                .build();
        repo.update(updateEntity);
        GameCategory newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Action",newEntity.getCategory());

        // DELETE ENTITY
        repo.delete(updateEntity);
        GameCategory deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
