package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.util.Log;
import android.view.View;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.ViewContainer;
import ragingpython.crystalcash.entities.CCEntity;


public class Wallet extends CCEntity {
    int id;
    String name;


    public Wallet(Cursor cursor) {
        Log.d("wallet", "wallet created!!!");
        id=cursor.getInt(cursor.getColumnIndex("id"));
        name=cursor.getString(cursor.getColumnIndex("name"));
    }

    private void getData(WalletDataContainer walletDataContainer){
        walletDataContainer.name=name;
        walletDataContainer.answered=true;
        Cursor cursor = database.rawQuery("select sum(amount) from wallet_operations where wallet_id="+String.valueOf(id),null);
        cursor.moveToFirst();
        if (cursor.getCount()==0){
            Log.d("wallet", "count==0");
            walletDataContainer.balance=0;
        } else walletDataContainer.balance=cursor.getDouble(0);
        cursor.close();
    }

    @Override
    public void createMainView() {
        eventManager.registerReceiver(new WalletMainView(getHash()));
    }

    @Override
    public String getHash() {
        return "wallet(id="+ String.valueOf(id)+")";
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
        switch (eventTag) {
            case EventTag.ENTITY_WALLET_GET_DATA:
                if (getHash().compareTo(((WalletDataContainer) o).hash)==0){
                    getData((WalletDataContainer) o);
                }
                break;
        }
    }
}
