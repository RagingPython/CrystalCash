package ragingpython.crystalcash;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;


public class MenuFragment extends Fragment implements EventReceiver {
    EventManager eventManager;
    LinearLayout widgetAddViewContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        widgetAddViewContainer = view.findViewById(R.id._linearLayout_widgetAddViewContainer);
        return view;
    }

    public void fillContainer() {
        eventManager.broadcastEvent(EventTag.VIEW_DESTROY,null);
        widgetAddViewContainer.removeAllViews();
        eventManager.broadcastEvent(EventTag.ENTITY_CONSTRUCTOR_INSERT_CREATION_VIEW, widgetAddViewContainer);
    }

    @Override
    public void destroy() {
        eventManager=null;
        widgetAddViewContainer=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager) o;
                break;
            case EventTag.FRAGMENT_NOW_ACTIVE:
                fillContainer();
                break;
            case EventTag.FRAGMENT_NOW_NOT_ACTIVE:
                widgetAddViewContainer.removeAllViews();
                eventManager.unRegisterReceiver(this);
                break;
        }
    }
}
