package ragingpython.crystalcash.entities;

import android.view.View;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.FreeMemory;

public abstract class CCEntity implements EventReceiver, FreeMemory{
    EventManager eventManager;

    public abstract View getEntityView();
    public abstract String getHash();

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_STAGE_EVENT_MANAGER:
                eventManager= (EventManager) o;
                break;
            case EventTag.ENTITIES_SELFDESTRUCT:
                eventManager.unRegisterReceiver(this);
                freeMemory();
                break;
        }
    }
}
