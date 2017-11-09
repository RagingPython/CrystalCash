package ragingpython.crystalcash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.containers.DatabaseContainer;


class DBOpenHelper extends SQLiteOpenHelper implements EventReceiver{
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME= "DATA";
    private EventManager eventManager;
    private SQLiteDatabase database=null;

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        eventManager.broadcastEvent(EventTag.DATABASE_CREATE_DB, sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        eventManager.broadcastEvent(EventTag.DATABASE_DELETE_DB, sqLiteDatabase);
        eventManager.broadcastEvent(EventTag.DATABASE_CREATE_DB, sqLiteDatabase);
    }

    @Override
    public void destroy() {
        eventManager=null;
        database.close();
        database=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager= (EventManager) o;
                break;
            case EventTag.DATABASE_GET_DB:
                if (database==null) {database=this.getWritableDatabase();}
                ((DatabaseContainer) o).setSqLiteDatabase(database);
                break;
        }
    }
}
