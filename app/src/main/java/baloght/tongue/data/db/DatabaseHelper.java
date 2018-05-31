package baloght.tongue.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import baloght.tongue.di.ApplicationContext;
import baloght.tongue.utils.LogUtil;

@Singleton
public class DatabaseHelper extends SQLiteOpenHelper implements DbHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "statistics_db";

    @Inject
    public DatabaseHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogUtil.log("database has been created");
        db.execSQL(Statistics.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Statistics.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public long insertStatistics(Statistics statistics) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Statistics.COLUMN_DATE, statistics.getDate());
        values.put(Statistics.COLUMN_STAR, statistics.getStar());
        values.put(Statistics.COLUMN_PERCENTAGE, statistics.getPercentage());
        long id = db.insert(Statistics.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    @Override
    public void deleteAllStatistics() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + Statistics.TABLE_NAME);
        db.close();
        LogUtil.log("All data have been deleted from " + Statistics.TABLE_NAME);
    }

    @Override
    public Statistics getStatisticsById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Statistics.TABLE_NAME,
                new String[]{Statistics.COLUMN_ID, Statistics.COLUMN_DATE, Statistics.COLUMN_STAR, Statistics.COLUMN_PERCENTAGE},
                Statistics.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Statistics statistics = new Statistics(
                cursor.getLong(cursor.getColumnIndex(Statistics.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Statistics.COLUMN_DATE)),
                cursor.getInt(cursor.getColumnIndex(Statistics.COLUMN_STAR)),
                cursor.getInt(cursor.getColumnIndex(Statistics.COLUMN_PERCENTAGE)));

        cursor.close();
        return statistics;
    }

    @Override
    public List<Statistics> getAllStatistics() {
        List<Statistics> statistics = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + Statistics.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                statistics.add(new Statistics(cursor.getLong(cursor.getColumnIndex(Statistics.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Statistics.COLUMN_DATE)),
                        cursor.getInt(cursor.getColumnIndex(Statistics.COLUMN_STAR)),
                        cursor.getInt(cursor.getColumnIndex(Statistics.COLUMN_PERCENTAGE))));
            } while (cursor.moveToNext());
        }

        db.close();
        return statistics;
    }

    @Override
    public int getNumberOfGamesByDate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Statistics.TABLE_NAME,
                new String[]{Statistics.COLUMN_ID, Statistics.COLUMN_DATE, Statistics.COLUMN_STAR, Statistics.COLUMN_PERCENTAGE},
                Statistics.COLUMN_DATE + "=?",
                new String[]{date}, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}
