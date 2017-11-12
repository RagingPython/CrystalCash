package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.DatabaseContainer;

public abstract class CCEntity implements EventReceiver{
    public EventManager eventManager;
    public SQLiteDatabase database;

    public abstract void createMainView();
    public abstract String getHash();

    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager= (EventManager) o;
                DatabaseContainer databaseContainer = new DatabaseContainer();
                eventManager.broadcastEvent(EventTag.DATABASE_GET_DB, databaseContainer);
                database=databaseContainer.sqLiteDatabase;
                break;
            case EventTag.ENTITY_DESTROY:
                eventManager.broadcastEvent(EventTag.INIT_DESTROY, this);
                break;
            case EventTag.ENTITY_CREATE_MAIN_VIEW:
                if (getHash().compareTo((String) o)==0) {
                    createMainView();
                }
                break;
            case EventTag.ENTITY_GET_HASH:
                ((HashSet<String>) o).add(getHash());
                break;
        }
    }
}
