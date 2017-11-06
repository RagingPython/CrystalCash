package ragingpython.crystalcash;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import EDEMVP.EventManager;
import EDEMVP.EventReceiver;

class DBController implements EventReceiver{

    private EventManager eventManager;
    private SQLiteDatabase database;

    DBController(Context context) {
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        database=dbOpenHelper.getWritableDatabase();
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager=(EventManager) o;
                break;
        }
    }
}
