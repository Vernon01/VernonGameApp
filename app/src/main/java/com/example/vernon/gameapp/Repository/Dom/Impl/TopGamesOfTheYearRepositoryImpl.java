package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.TopGamesOfTheYear;
import com.example.vernon.gameapp.Repository.Dom.TopGamesOfTheYearRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class TopGamesOfTheYearRepositoryImpl extends SQLiteOpenHelper implements TopGamesOfTheYearRepository {

    public static final String TABLE_TOPGAMESOFTHEYEAR = "TopGamesOfTheYear";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_TOPGAMESOFTHEYEAR + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL ); ";

    public TopGamesOfTheYearRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public TopGamesOfTheYear findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_TOPGAMESOFTHEYEAR,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final TopGamesOfTheYear topGames = new TopGamesOfTheYear.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .build();

            return topGames;
        }else {
            return null;
        }
    }

    @Override
    public TopGamesOfTheYear save (TopGamesOfTheYear entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        long id = db.insertOrThrow(TABLE_TOPGAMESOFTHEYEAR, null, values);
        TopGamesOfTheYear insertedEntity = new TopGamesOfTheYear.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public TopGamesOfTheYear update(TopGamesOfTheYear entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        db.update(
                TABLE_TOPGAMESOFTHEYEAR,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public TopGamesOfTheYear delete(TopGamesOfTheYear entity) {

        open();
        db.delete(
                TABLE_TOPGAMESOFTHEYEAR,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<TopGamesOfTheYear> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<TopGamesOfTheYear> topGames = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_TOPGAMESOFTHEYEAR, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final TopGamesOfTheYear topGam = new TopGamesOfTheYear.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .build();
                topGames.add(topGam);
            } while (cursor.moveToNext());
        }
        return topGames;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_TOPGAMESOFTHEYEAR,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPGAMESOFTHEYEAR);
        onCreate(db);

    }
}
