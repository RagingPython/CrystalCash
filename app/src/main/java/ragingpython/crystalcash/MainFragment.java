package ragingpython.crystalcash;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;

public class MainFragment extends Fragment implements View.OnClickListener, EventReceiver{
    LinearLayout entityContainer;
    EventManager eventManager;



    private void refresh(){
        entityContainer.removeAllViews();
        eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_INSERT_WIDGETS, entityContainer);

        //TODO: add + button


        //TODO: add adding buttons
        eventManager.broadcastEvent(EventTag.ENTITY_REFRESH, null);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        entityContainer = view.findViewById(R.id._entityContainer);
        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void destroy() {
        eventManager=null;
        entityContainer=null;
    }


    @Override
    public void eventMapping(int eventTag, Object o) {
        switch(eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager = (EventManager) o;
                break;
            case EventTag.FRAGMENT_NOW_NOT_ACTIVE:
                eventManager.unRegisterReceiver(this);
                eventManager=null;
                break;
            case EventTag.ENTITY_MANAGER_RELOAD_ENTITIES:
            case EventTag.FRAGMENT_NOW_ACTIVE:
            case EventTag.FRAGMENT_MAIN_REFRESH:
                refresh();
                break;
        }
    }


}
