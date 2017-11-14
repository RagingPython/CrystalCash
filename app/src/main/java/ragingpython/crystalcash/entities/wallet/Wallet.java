package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.R;
import ragingpython.crystalcash.containers.CursorContainer;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.entities.CCEntity;


public class Wallet extends CCEntity {
    private int id;
    private String name;
    private TextView textViewName, textViewBalance;


    public Wallet(Cursor cursor) {
        id=cursor.getInt(cursor.getColumnIndex("id"));
        name=cursor.getString(cursor.getColumnIndex("name"));
    }

    @Override
    public View createMainView()
    {
        InflateRequest inflateRequest = new InflateRequest(R.layout.wallet_main);
        eventManager.broadcastEvent(EventTag.ACTIVITY_INFLATE, inflateRequest);
        textViewName=inflateRequest.view.findViewById(R.id._textView_walletName);
        textViewBalance=inflateRequest.view.findViewById(R.id._textView_walletBalance);
        return inflateRequest.view;
    }

    @Override
    public String getHash() {
        return "wallet(id="+ String.valueOf(id)+")";
    }

    @Override
    public void refresh() {
        CursorContainer cursorContainer = new CursorContainer("select sum(amount) from wallet_operations where wallet_id="+String.valueOf(id));
        eventManager.broadcastEvent(EventTag.DATABASE_RAW_QUERY, cursorContainer);
        Cursor cursor = cursorContainer.cursor;
        cursor.moveToFirst();
        Double balance;
        if (cursor.getCount()==0){
            balance=0.0;
        } else {
            balance=cursor.getDouble(0);
        }
        textViewName.setText(name);
        textViewBalance.setText(String.valueOf(balance));
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        super.eventMapping(eventTag, o);
        switch (eventTag) {

        }
    }
}
