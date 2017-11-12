package ragingpython.crystalcash.entities;


import android.database.sqlite.SQLiteDatabase;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.DatabaseContainer;
import ragingpython.crystalcash.containers.ViewContainer;
import ragingpython.crystalcash.entities.wallet.WalletConstructor;

public class EntityManager implements EventReceiver{
    private static final String CREATE_DB="create table entity(hash text primary key, position integer)";
    private static final String DELETE_DB="drop table entity";
    private EventManager eventManager;
    private SQLiteDatabase database;


    private void createEntityConstructors() {
        //TODO: insert entity constructors here
        eventManager.registerReceiver(new WalletConstructor());
    }

    private void initialize() {
        DatabaseContainer databaseContainer = new DatabaseContainer();
        eventManager.broadcastEvent(EventTag.DATABASE_GET_DB, databaseContainer);
        database=databaseContainer.sqLiteDatabase;
        eventManager.broadcastEvent(EventTag.VIEW_DESTROY, null);
        eventManager.broadcastEvent(EventTag.ENTITY_DESTROY, null);
        eventManager.broadcastEvent(EventTag.ENTITY_CONSTRUCTOR_LOAD_ENTITIES, null);
    }

    private void  refreshWidgets() {
        HashSet<String> hashSet = new HashSet<String>();
        eventManager.broadcastEvent(EventTag.ENTITY_GET_HASH, hashSet);
        eventManager.broadcastEvent(EventTag.FRAGMENT_CLEAR_WIDGETS, null);
        eventManager.broadcastEvent(EventTag.VIEW_DESTROY,null);
        ViewContainer viewContainer = new ViewContainer();
        for (String s:hashSet) {
            viewContainer.hash=s;
            eventManager.broadcastEvent(EventTag.ENTITY_CREATE_MAIN_VIEW, s);
            eventManager.broadcastEvent(EventTag.VIEW_GET_VIEW, viewContainer);
            eventManager.broadcastEvent(EventTag.FRAGMENT_INSERT_WIDGET, viewContainer.view);
        }
        eventManager.broadcastEvent(EventTag.VIEW_UPDATE_ALL,null);
    }


    @Override
    public void destroy() {
        eventManager=null;
        database=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager = (EventManager) o;
                createEntityConstructors();
                break;
            case EventTag.DATABASE_CREATE_DB:
                ((SQLiteDatabase) o).execSQL(CREATE_DB);
                break;
            case EventTag.DATABASE_DELETE_DB:
                ((SQLiteDatabase) o).execSQL(DELETE_DB);
                break;
            case EventTag.ENTITY_MANAGER_NEW_ENTITY_CREATED:
            case EventTag.ENTITY_MANAGER_RELOAD_ENTITIES:
                initialize();
                break;
            case EventTag.ENTITY_MANAGER_REFRESH_WIDGETS:
                refreshWidgets();
                break;
        }
    }
}
