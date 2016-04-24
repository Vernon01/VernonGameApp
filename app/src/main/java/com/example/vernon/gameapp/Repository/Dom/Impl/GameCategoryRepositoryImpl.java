package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.GameCategory;
import com.example.vernon.gameapp.Repository.Dom.GameCategoryRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameCategoryRepositoryImpl extends SQLiteOpenHelper implements GameCategoryRepository{

    public static final String TABLE_GAMECATEGORY = "GameCategory";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_CATEGORY = "category";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_GAMECATEGORY + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_CATEGORY + " TEXT NOT NULL );";

    public GameCategoryRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException{

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public GameCategory findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_GAMECATEGORY,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_CATEGORY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final GameCategory gameCat = new GameCategory.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)))
                    .build();

            return gameCat;
        }else {
            return null;
        }
    }

    @Override
    public GameCategory save (GameCategory entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_CATEGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_GAMECATEGORY, null, values);
        GameCategory insertedEntity = new GameCategory.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public GameCategory update(GameCategory entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_CATEGORY, entity.getCategory());
        db.update(
                TABLE_GAMECATEGORY,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public GameCategory delete(GameCategory entity) {

        open();
        db.delete(
                TABLE_GAMECATEGORY,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GameCategory> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<GameCategory> settings = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_GAMECATEGORY, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GameCategory setting = new GameCategory.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)))
                        .build();
                settings.add(setting);
            } while (cursor.moveToNext());
        }
        return settings;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_GAMECATEGORY,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMECATEGORY);
        onCreate(db);

    }
}
