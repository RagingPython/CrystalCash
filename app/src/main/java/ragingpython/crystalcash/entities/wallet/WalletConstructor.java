package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.CursorContainer;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.entities.CCEntityConstructor;


public class WalletConstructor extends CCEntityConstructor {

    @Override
    public void onDbCreate(SQLiteDatabase database) {
        database.execSQL("create table wallet (id integer primary key asc autoincrement, name text)");
        database.execSQL("create table wallet_operations (id integer primary key asc autoincrement, wallet_id integer references wallet(id) on update cascade, amount integer, time text)");
    }

    @Override
    public void onDbDelete(SQLiteDatabase database) {
        database.execSQL("drop table wallet");
        database.execSQL("drop table wallet_operations");
    }

    @Override
    public void loadEntities() {
        CursorContainer cursorContainer = new CursorContainer("select * from wallet");
        eventManager.broadcastEvent(EventTag.DATABASE_RAW_QUERY, cursorContainer);
        Cursor cursor = cursorContainer.cursor;
        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                eventManager.registerReceiver(new Wallet(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    @Override
    public String getName() {
        return "wallet";
    }

    @Override
    public void createEntity() {
        eventManager.broadcastEvent(EventTag.DATABASE_RAW_QUERY,"insert into wallet(name) values ('New wallet')");
    }


    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
        switch (eventTag) {

        }
    }
}
