package ragingpython.crystalcash.entities;

import android.view.View;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.ViewContainer;

public abstract class CCEntity implements EventReceiver{
    public EventManager eventManager;
    public View mainView;

    public abstract View createMainView();
    public abstract String getHash();
    public abstract void refresh();

    public View getMainView(){
        return mainView;
    }

    @Override
    public void destroy() {
        eventManager=null;
        mainView=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager= (EventManager) o;
                mainView=createMainView();
                break;
            case EventTag.ENTITY_DESTROY:
                eventManager.broadcastEvent(EventTag.INIT_DESTROY, this);
                break;
            case EventTag.ENTITY_GET_HASH:
                ((HashSet<String>) o).add(getHash());
                break;
            case EventTag.ENTITY_GET_MAIN_VIEW:
                if (getHash().compareTo(((ViewContainer)o).hash)==0) {
                    ((ViewContainer) o).view = getMainView();
                }
                break;
            case EventTag.ENTITY_REFRESH:
                if (o==null) {
                    refresh();
                } else if (getHash().compareTo((String) o)==0) {
                    refresh();
                }
                break;
        }
    }
}
