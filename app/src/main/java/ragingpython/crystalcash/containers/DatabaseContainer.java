package ragingpython.crystalcash.containers;


import android.database.sqlite.SQLiteDatabase;

public class DatabaseContainer {
    SQLiteDatabase sqLiteDatabase;

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }
}
