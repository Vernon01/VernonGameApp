package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.GameTitles;
import com.example.vernon.gameapp.Repository.Dom.GameTitlesRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GameTitlesRepositoryImpl extends SQLiteOpenHelper implements GameTitlesRepository {

    public static final String TABLE_GAMETITLES = "GameTitles";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MACHINE = "machine";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_GAMETITLES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_MACHINE + " TEXT NOT NULL );";

    public GameTitlesRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public GameTitles findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_GAMETITLES,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_MACHINE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final GameTitles gameTit = new GameTitles.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .machine(cursor.getString(cursor.getColumnIndex(COLUMN_MACHINE)))
                    .build();

            return gameTit;
        }else {
            return null;
        }
    }

    @Override
    public GameTitles save (GameTitles entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_MACHINE, entity.getMachine());
        long id = db.insertOrThrow(TABLE_GAMETITLES, null, values);
        GameTitles insertedEntity = new GameTitles.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public GameTitles update(GameTitles entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_MACHINE, entity.getMachine());
        db.update(
                TABLE_GAMETITLES,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public GameTitles delete(GameTitles entity) {

        open();
        db.delete(
                TABLE_GAMETITLES,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GameTitles> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<GameTitles> gameTit = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_GAMETITLES, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GameTitles gameTi = new GameTitles.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .machine(cursor.getString(cursor.getColumnIndex(COLUMN_MACHINE)))
                        .build();
                gameTit.add(gameTi);
            } while (cursor.moveToNext());
        }
        return gameTit;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_GAMETITLES,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMETITLES);
        onCreate(db);

    }
}
