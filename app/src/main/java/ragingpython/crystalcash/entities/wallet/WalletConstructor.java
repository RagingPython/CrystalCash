package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import ragingpython.crystalcash.entities.CCEntityConstructor;


public class WalletConstructor extends CCEntityConstructor {

    @Override
    public String getDbCreateQuery() {
        return "create table wallet (id integer primary key asc autoincrement, name text) create table wallet_operations (id integer primary key asc autoincrement, wallet_id integer references wallet(id) on update cascade, amount integer, time text)";
    }

    @Override
    public String getDbDeleteQuery() {
        return "drop table wallet drop table wallet_operations";
    }

    @Override
    public void loadEntities() {
        Cursor cursor = database.query("wallet",null,null,null,null,null,null);
        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                eventManager.registerReceiver(new Wallet(cursor));
            }
            cursor.close();
        }
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
    }
}
