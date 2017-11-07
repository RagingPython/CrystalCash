package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;

public abstract class EntityConstructor implements EventReceiver{
    EventManager eventManager;
    private static final String DB_CREATE="";
    private static final String DB_DELETE="";

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager=(EventManager)o;
                break;
            case EventTag.DATABASE_CREATE_DB:
                ((SQLiteDatabase) o).execSQL(this.DB_CREATE);

        }
    }
}
