package ragingpython.crystalcash.entities;


import android.database.sqlite.SQLiteDatabase;
import android.view.ViewGroup;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.ViewContainer;
import ragingpython.crystalcash.entities.wallet.WalletConstructor;

public class EntityManager implements EventReceiver{
    private static final String CREATE_DB="create table entity(hash text primary key, position integer)";
    private static final String DELETE_DB="drop table entity";
    private EventManager eventManager;


    private void createEntityConstructors() {
        //TODO: insert entity constructors here
        eventManager.registerReceiver(new WalletConstructor());
    }

    private void reloadEntities() {
        eventManager.broadcastEvent(EventTag.ENTITY_DESTROY, null);
        eventManager.broadcastEvent(EventTag.ENTITY_CONSTRUCTOR_LOAD_ENTITIES, null);
    }

    private void  insertWidgets(ViewGroup viewGroup) {
        HashSet<String> hashSet = new HashSet<String>();
        eventManager.broadcastEvent(EventTag.ENTITY_GET_HASH, hashSet);
        ViewContainer viewContainer = new ViewContainer();
        for (String s:hashSet) {
            viewContainer.hash=s;
            eventManager.broadcastEvent(EventTag.ENTITY_GET_MAIN_VIEW, viewContainer);
            viewGroup.addView(viewContainer.view);
        }
    }


    @Override
    public void destroy() {
        eventManager=null;
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
            case EventTag.ENTITY_MANAGER_RELOAD_ENTITIES:
                reloadEntities();
                break;
            case EventTag.ENTITY_MANAGER_INSERT_WIDGETS:
                insertWidgets((ViewGroup)o);
                break;
        }
    }
}
