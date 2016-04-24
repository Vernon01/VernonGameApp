package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.GameRatings;
import com.example.vernon.gameapp.Repository.Dom.GameRatingsRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameRatingsRepositoryImpl extends SQLiteOpenHelper implements GameRatingsRepository {

    public static final String TABLE_GAMERATINGS = "GameRatings";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_RATING = "rating";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_GAMERATINGS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_PRICE + " TEXT NOT NULL, "
            + COLUMN_RATING + " TEXT NOT NULL );";

    public GameRatingsRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public GameRatings findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_GAMERATINGS,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_PRICE,
                        COLUMN_RATING},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final GameRatings gameRat = new GameRatings.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .price(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)))
                    .rating(cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)))
                    .build();

            return gameRat;
        }else {
            return null;
        }
    }

    @Override
    public GameRatings save (GameRatings entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_RATING, entity.getRating());
        long id = db.insertOrThrow(TABLE_GAMERATINGS, null, values);
        GameRatings insertedEntity = new GameRatings.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public GameRatings update(GameRatings entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_RATING, entity.getRating());
        db.update(
                TABLE_GAMERATINGS,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public GameRatings delete(GameRatings entity) {

        open();
        db.delete(
                TABLE_GAMERATINGS,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GameRatings> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<GameRatings> gameRat = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_GAMERATINGS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GameRatings gameRa = new GameRatings.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .price(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)))
                        .rating(cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)))
                        .build();
                gameRat.add(gameRa);
            } while (cursor.moveToNext());
        }
        return gameRat;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_GAMERATINGS,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMERATINGS);
        onCreate(db);

    }
}
