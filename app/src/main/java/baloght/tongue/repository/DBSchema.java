package baloght.tongue.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class DBSchema  extends SQLiteOpenHelper {

    private static final int    DB_VERSION  = 1;
    private static final String DB_NAME     = "example.db";

    public DBSchema(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String TABLE_USER  = "user";

    private static final String COMMA_SPACE     = ", ";
    private static final String CREATE_TABLE    = "CREATE TABLE ";
    private static final String PRIMARY_KEY     = "PRIMARY KEY ";
    private static final String TYPE_TEXT       = " TEXT ";
    private static final String AUTOINCREMENT       = " AUTOINCREMENT ";
    private static final String TYPE_INT        = " INTEGER ";
    private static final String NOT_NULL        = "NOT NULL ";

    public static final class TB_USER {
        public static final String ID = "_id";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String USERNAME = "username";
        public static final String DATE = "date";

    }

    private static final String CREATE_TABLE_NOTES =
            CREATE_TABLE + TABLE_USER + " ( " +
                    TB_USER.ID + TYPE_INT + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE +
                    TB_USER.EMAIL + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
                    TB_USER.PASSWORD + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
                    TB_USER.USERNAME + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
                    TB_USER.DATE + TYPE_TEXT + NOT_NULL +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES);
        Log.d("tbalogh","adatbázis elkészítce");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE_NOTES);
    }
}
