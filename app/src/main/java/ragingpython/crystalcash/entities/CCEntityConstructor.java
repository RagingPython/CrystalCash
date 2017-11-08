package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;

public abstract class CCEntityConstructor implements EventReceiver{
    private EventManager eventManager;

    public abstract String getDbCreateQuery();
    public abstract String getDbDeleteQuery();
    public abstract void loadEntities(LinkedList<CCEntity> entityList);

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager=(EventManager)o;
                break;
            case EventTag.DATABASE_CREATE_DB:
                ((SQLiteDatabase) o).execSQL(getDbCreateQuery());
                break;
            case EventTag.DATABASE_DELETE_DB:
                ((SQLiteDatabase) o).execSQL(getDbDeleteQuery());
                break;
            case EventTag.ENTITIES_RELOAD_ENTITIES:
                loadEntities((LinkedList<CCEntity>) o);
                break;
        }
    }
}
