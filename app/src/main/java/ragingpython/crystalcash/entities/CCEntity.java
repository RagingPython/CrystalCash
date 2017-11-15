package ragingpython.crystalcash.entities;

import android.view.View;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.ViewContainer;

public abstract class CCEntity implements EventReceiver{
    public EventManager eventManager;
    public View currentView;

    public abstract View createMainView();
    public abstract String getHash();
    public abstract void refresh();
    public abstract void setMode(boolean mode);

    public View getCurrentView(){
        return currentView;
    }

    @Override
    public void destroy() {
        eventManager=null;
        currentView =null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager= (EventManager) o;
                currentView =createMainView();
                break;
            case EventTag.ENTITY_DESTROY:
                eventManager.broadcastEvent(EventTag.INIT_DESTROY, this);
                break;
            case EventTag.ENTITY_GET_HASH:
                ((HashSet<String>) o).add(getHash());
                break;
            case EventTag.ENTITY_GET_MAIN_VIEW:
                if (getHash().compareTo(((ViewContainer)o).hash)==0) {
                    ((ViewContainer) o).view = getCurrentView();
                }
                break;
            case EventTag.ENTITY_REFRESH:
                if (o==null) {
                    refresh();
                } else if (getHash().compareTo((String) o)==0) {
                    refresh();
                }
                break;
            case EventTag.ENTITY_SET_MODE:
                setMode((boolean) o);
                break;
        }
    }
}
