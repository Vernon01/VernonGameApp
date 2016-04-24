package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.GamePerformanceToPrice;
import com.example.vernon.gameapp.Repository.Dom.GamePerformanceToPriceRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GamePerformanceToPriceRepositoryImpl extends SQLiteOpenHelper implements GamePerformanceToPriceRepository {

    public static final String TABLE_GAMEPERFORMANCETOPRICE = "GamePerformanceToPrice";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_OPINION = "opinion";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_GAMEPERFORMANCETOPRICE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_OPINION + " TEXT NOT NULL );";

    public GamePerformanceToPriceRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public GamePerformanceToPrice findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_GAMEPERFORMANCETOPRICE,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_OPINION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final GamePerformanceToPrice gamePerf = new GamePerformanceToPrice.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .opinion(cursor.getString(cursor.getColumnIndex(COLUMN_OPINION)))
                    .build();

            return gamePerf;
        }else {
            return null;
        }
    }

    @Override
    public GamePerformanceToPrice save (GamePerformanceToPrice entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_OPINION, entity.getOpinion());
        long id = db.insertOrThrow(TABLE_GAMEPERFORMANCETOPRICE, null, values);
        GamePerformanceToPrice insertedEntity = new GamePerformanceToPrice.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public GamePerformanceToPrice update(GamePerformanceToPrice entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_OPINION, entity.getOpinion());
        db.update(
                TABLE_GAMEPERFORMANCETOPRICE,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public GamePerformanceToPrice delete(GamePerformanceToPrice entity) {

        open();
        db.delete(
                TABLE_GAMEPERFORMANCETOPRICE,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GamePerformanceToPrice> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<GamePerformanceToPrice> gamePerf = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_GAMEPERFORMANCETOPRICE, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GamePerformanceToPrice gamePer = new GamePerformanceToPrice.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .opinion(cursor.getString(cursor.getColumnIndex(COLUMN_OPINION)))
                        .build();
                gamePerf.add(gamePer);
            } while (cursor.moveToNext());
        }
        return gamePerf;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_GAMEPERFORMANCETOPRICE,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMEPERFORMANCETOPRICE);
        onCreate(db);

    }
}
