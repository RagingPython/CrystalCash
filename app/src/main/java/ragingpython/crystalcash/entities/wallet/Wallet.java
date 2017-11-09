package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.view.View;
import ragingpython.crystalcash.entities.CCEntity;


public class Wallet extends CCEntity {
    int id;
    String name;


    public Wallet(Cursor cursor) {
        id=cursor.getInt(cursor.getColumnIndex("id"));
        name=cursor.getString(cursor.getColumnIndex("name"));
    }

    @Override
    public View getEntityView() {
        return null;
    }

    @Override
    public String getHash() {
        return "wallet(id="+ String.valueOf(id)+")";
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
    }
}
