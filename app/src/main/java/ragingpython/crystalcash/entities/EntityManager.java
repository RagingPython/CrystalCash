package ragingpython.crystalcash.entities;


import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;

public class EntityManager implements EventReceiver{
    EventManager eventManager;
    LinkedList<Entity> entityList;
    SQLiteDatabase database;

    public EntityManager() {
        entityList = new LinkedList<Entity>();


    };

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager = (EventManager) o;
                break;
        }
    }
}
