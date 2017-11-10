package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.DatabaseContainer;

public abstract class CCEntityConstructor implements EventReceiver{

    public EventManager eventManager;
    public SQLiteDatabase database;

    public abstract String getDbCreateQuery();
    public abstract String getDbDeleteQuery();
    public abstract void loadEntities();


    @Override
    public void destroy() {
        eventManager=null;
        database=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager)o;
                break;
            case EventTag.DATABASE_CREATE_DB:
                ((SQLiteDatabase) o).execSQL(getDbCreateQuery());
                break;
            case EventTag.DATABASE_DELETE_DB:
                ((SQLiteDatabase) o).execSQL(getDbDeleteQuery());
                break;
            case EventTag.ENTITY_CONSTRUCTOR_LOAD_ENTITIES:
                DatabaseContainer databaseContainer = new DatabaseContainer();
                eventManager.broadcastEvent(EventTag.DATABASE_GET_DB, databaseContainer);
                database=databaseContainer.sqLiteDatabase;
                loadEntities();
                break;
        }
    }
}
