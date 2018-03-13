package baloght.tongue.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import baloght.tongue.repository.models.User;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class DAO {
    DBSchema dbSchema;

    public DAO(Context context) {
        this.dbSchema = new DBSchema(context);
    }

    private SQLiteDatabase getReadDB(){
        return dbSchema.getReadableDatabase();
    }

    private SQLiteDatabase getWriteDB(){
        return dbSchema.getWritableDatabase();
    }

    public User insertUser(User user) {
        SQLiteDatabase db = getWriteDB();
        long id = db.insert(
                DBSchema.TABLE_USER,
                null,
                user.getValues()
        );
        db.close();
        User isertedUser = getUser((int)id);
        return isertedUser;
    }

    public User getUser(int id){
        SQLiteDatabase db = getReadDB();
        Cursor c = db.query(
                DBSchema.TABLE_USER,
                null,
                DBSchema.TB_USER.ID + " = ?",
                new String[]{Integer.toString(id)},
                null,
                null,
                null
        );
        if (c != null) {
            c.moveToFirst();
            User user = new User();
            user.setId(c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_USER.ID)));
            user.setEmail(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_USER.EMAIL)));
            user.setPassword(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_USER.PASSWORD)));
            user.setUsername(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_USER.USERNAME)));
            user.setDate(c.getString(c.getColumnIndexOrThrow(DBSchema.TB_USER.DATE)));
            c.close();
            db.close();
            return user;
        } else {
            return null;
        }
    }
}
