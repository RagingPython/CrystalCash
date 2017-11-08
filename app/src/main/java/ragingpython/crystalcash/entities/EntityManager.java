package ragingpython.crystalcash.entities;


import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.DatabaseContainer;

public class EntityManager implements EventReceiver{
    private static final String CREATE_DB="";
    private static final String DELETE_DB="";
    private EventManager eventManager;
    private LinkedList<CCEntity> entityList;
    private LinkedList<CCEntityConstructor> entityConstructorList;
    private SQLiteDatabase database;

    public EntityManager() {
        entityList = new LinkedList<CCEntity>();
        entityConstructorList = new LinkedList<CCEntityConstructor>();

        //TODO: insert constructors in list
    };

    private void initialize() {
        DatabaseContainer databaseContainer = new DatabaseContainer();
        eventManager.broadcastEvent(EventTag.DATABASE_GET_DB, databaseContainer);
        database=databaseContainer.getSqLiteDatabase();
        eventManager.broadcastEvent(EventTag.ENTITIES_SELFDESTRUCT, null);
        entityList.clear();
        eventManager.broadcastEvent(EventTag.ENTITIES_RELOAD_ENTITIES, entityList);
        for(CCEntity e:entityList){
            eventManager.registerReceiver(e);
        }
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager = (EventManager) o;
                break;
            case EventTag.DATABASE_CREATE_DB:
                ((SQLiteDatabase) o).execSQL(CREATE_DB);
                break;
            case EventTag.DATABASE_DELETE_DB:
                ((SQLiteDatabase) o).execSQL(DELETE_DB);
                break;
            case EventTag.INIT_FINAL_STAGE:
                initialize();
                break;
        }
    }
}
