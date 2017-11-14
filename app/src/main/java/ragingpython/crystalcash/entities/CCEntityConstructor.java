package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;


import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;


public abstract class CCEntityConstructor implements EventReceiver{

    public EventManager eventManager;

    public abstract void onDbCreate(SQLiteDatabase database);
    public abstract void onDbDelete(SQLiteDatabase database);
    public abstract void loadEntities();
    public abstract String getName();


    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager)o;
                break;
            case EventTag.DATABASE_CREATE_DB:
                onDbCreate((SQLiteDatabase)o);
                break;
            case EventTag.DATABASE_DELETE_DB:
                onDbDelete((SQLiteDatabase)o);
                break;
            case EventTag.ENTITY_CONSTRUCTOR_LOAD_ENTITIES:
                loadEntities();
                break;
            case EventTag.ENTITY_CONSTRUCTOR_GET_NAME:
                ((HashSet<String>)o).add(getName());
                break;
        }
    }
}
