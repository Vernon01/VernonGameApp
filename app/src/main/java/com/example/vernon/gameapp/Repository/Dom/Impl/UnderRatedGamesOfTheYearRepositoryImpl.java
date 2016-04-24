package com.example.vernon.gameapp.Repository.Dom.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vernon.gameapp.Config.Databases.DBConstants;
import com.example.vernon.gameapp.Domain.UnderRatedGames;
import com.example.vernon.gameapp.Repository.Dom.UnderRatedGamesRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by VERNON on 2016/04/24.
 */
public class UnderRatedGamesOfTheYearRepositoryImpl extends SQLiteOpenHelper implements UnderRatedGamesRepository {

    public static final String TABLE_UNDERRATEDGAMESOFTHEYEAR = "UnderRatedGamesOfTheYear";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITlE = "title";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_REASON = "reason";

    //Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE"
            + TABLE_UNDERRATEDGAMESOFTHEYEAR + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITlE + " TEXT NOT NULL, "
            + COLUMN_YEAR + " TEXT NOT NULL, "
            + COLUMN_REASON + " TEXT NOT NULL );";

    public UnderRatedGamesOfTheYearRepositoryImpl(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {

        db = this.getWritableDatabase();
    }

    public void close() {

        this.close();
    }

    @Override
    public UnderRatedGames findById(Integer id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_UNDERRATEDGAMESOFTHEYEAR,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TITlE,
                        COLUMN_YEAR,
                        COLUMN_REASON},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UnderRatedGames underRat = new UnderRatedGames.Builder(COLUMN_TITlE)
                    .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                    .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                    .reason(cursor.getString(cursor.getColumnIndex(COLUMN_REASON)))
                    .build();

            return underRat;
        }else {
            return null;
        }
    }

    @Override
    public UnderRatedGames save (UnderRatedGames entity){

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_REASON, entity.getReason());
        long id = db.insertOrThrow(TABLE_UNDERRATEDGAMESOFTHEYEAR, null, values);
        UnderRatedGames insertedEntity = new UnderRatedGames.Builder(COLUMN_TITlE)
                .copy(entity)
                .id(new Integer(COLUMN_ID))
                .build();
        return insertedEntity;
    }

    @Override
    public UnderRatedGames update(UnderRatedGames entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TITlE, entity.getTitle());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_REASON, entity.getReason());
        db.update(
                TABLE_UNDERRATEDGAMESOFTHEYEAR,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public UnderRatedGames delete(UnderRatedGames entity) {

        open();
        db.delete(
                TABLE_UNDERRATEDGAMESOFTHEYEAR,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<UnderRatedGames> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<UnderRatedGames> underRat = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_UNDERRATEDGAMESOFTHEYEAR, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final UnderRatedGames underRa = new UnderRatedGames.Builder(COLUMN_TITlE)
                        .id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITlE)))
                        .year(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)))
                        .reason(cursor.getString(cursor.getColumnIndex(COLUMN_REASON)))
                        .build();
                underRat.add(underRa);
            } while (cursor.moveToNext());
        }
        return underRat;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_UNDERRATEDGAMESOFTHEYEAR,null,null);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNDERRATEDGAMESOFTHEYEAR);
        onCreate(db);

    }
}
