package ragingpython.crystalcash.entities;

import android.view.View;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;

public abstract class CCEntity implements EventReceiver{
    private EventManager eventManager;

    public abstract View getEntityView();
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
                break;
            case EventTag.ENTITY_DESTROY:
                eventManager.broadcastEvent(EventTag.INIT_DESTROY, this);
                break;
            case EventTag.ENTITY_GET_HASH:
                ((HashSet<String>) o).add(getHash());
                break;
        }
    }
}
