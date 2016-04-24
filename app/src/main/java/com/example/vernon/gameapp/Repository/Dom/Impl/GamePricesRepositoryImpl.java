package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.GamePrices;
import com.example.vernon.gameapp.Repository.Dom.GamePricesRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class GamePricesRepositoryImpl extends SQLiteOpenHelper implements GamePricesRepository {

    public static final String TABLE_GAMEPRICES = "GamePrices";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_MACHINE = "machine";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_GAMEPRICES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_PRICE + " TEXT NOT NULL, "
            + COLUMN_MACHINE + " TEXT NOT NULL );";

    public GamePricesRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public GamePrices findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_GAMEPRICES,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_PRICE,
                        COLUMN_MACHINE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final GamePrices gamePri = new GamePrices.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .price(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)))
                    .machine(cursor.getString(cursor.getColumnIndex(COLUMN_MACHINE)))
                    .build();

            return gamePri;
        }else {
            return null;
        }
    }

    @Override
    public GamePrices save (GamePrices entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_MACHINE, entity.getMachine());
        long id = db.insertOrThrow(TABLE_GAMEPRICES, null, values);
        GamePrices insertedEntity = new GamePrices.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public GamePrices update(GamePrices entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put(COLUMN_MACHINE, entity.getMachine());
        db.update(
                TABLE_GAMEPRICES,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public GamePrices delete(GamePrices entity) {

        open();
        db.delete(
                TABLE_GAMEPRICES,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GamePrices> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<GamePrices> gamePri = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_GAMEPRICES, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GamePrices gamePr = new GamePrices.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .price(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE)))
                        .machine(cursor.getString(cursor.getColumnIndex(COLUMN_MACHINE)))
                        .build();
                gamePri.add(gamePr);
            } while (cursor.moveToNext());
        }
        return gamePri;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_GAMEPRICES,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMEPRICES);
        onCreate(db);

    }
}
