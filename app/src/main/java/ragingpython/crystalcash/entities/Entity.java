package ragingpython.crystalcash.entities;

import android.view.View;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;

public abstract class Entity implements EventReceiver{
    EventManager eventManager;
    String type;

    public abstract View getEntityView();

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager= (EventManager) o;
                break;
        }
    }
}
