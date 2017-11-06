package ragingpython.crystalcash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



class DBOpenHelper extends SQLiteOpenHelper {
    private static final String CREATE_DB="";
    private static final String DELETE_DB="";
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME= "FIELD_STATE";

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_DB);
        sqLiteDatabase.execSQL(CREATE_DB);
    }
}
