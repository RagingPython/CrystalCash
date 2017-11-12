package ragingpython.crystalcash.entities;

import android.view.View;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.containers.ViewContainer;


public abstract class CCEntityView implements EventReceiver {
    public EventManager eventManager;
    public String hash=null;
    public View mainView;

    public CCEntityView(String hash) {
        this.hash=hash;
    }

    public abstract View createMainView();
    public abstract void update();
    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager) o;
                if (mainView==null) {
                    mainView = createMainView();
                }
                break;
            case EventTag.VIEW_DESTROY:
                eventManager.broadcastEvent(EventTag.INIT_DESTROY, this);
                break;
            case EventTag.VIEW_GET_VIEW:
                if (((ViewContainer) o).hash.compareTo(hash)==0) {
                    ((ViewContainer) o).view=mainView;
                }
                break;
            case EventTag.VIEW_UPDATE_ALL:
                update();
                break;
        }
    }
}
