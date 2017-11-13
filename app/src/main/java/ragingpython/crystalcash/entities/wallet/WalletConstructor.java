package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.containers.ViewContainer;
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
        Cursor cursor = database.query("wallet",null,null,null,null,null,null);
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
    public void insertCreationView(ViewGroup viewGroup) {
        InflateRequest inflateRequest = new InflateRequest();

    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
        switch (eventTag) {
            case EventTag.ENTITY_WALLET_NEW:
                database.execSQL("insert into wallet(name) values ('"+(String) o + "')");
                eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_ENTITY_SET_MODIFIED,null);
                break;
        }
    }
}
