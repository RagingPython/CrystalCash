package ragingpython.crystalcash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.containers.ViewContainer;

public class MainFragment extends Fragment implements View.OnClickListener, EventReceiver{
    LinearLayout entityContainer;
    EventManager eventManager;
    View plusView;
    Button plusButton;
    boolean settingsMode=false;


    private void refresh(){
        entityContainer.removeAllViews();
        eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_INSERT_WIDGETS, entityContainer);
        entityContainer.addView(plusView);
        if (settingsMode){
            HashSet<String> names= new HashSet<String>();
            eventManager.broadcastEvent(EventTag.ENTITY_CONSTRUCTOR_GET_NAME, names);
            ViewContainer viewContainer=new ViewContainer();
            for (String s:names) {
                viewContainer.hash=s;
                eventManager.broadcastEvent(EventTag.ENTITY_CONSTRUCTOR_GET_ADD_VIEW,viewContainer);
                entityContainer.addView(viewContainer.view);
            }
        }
        eventManager.broadcastEvent(EventTag.ENTITY_REFRESH, null);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        plusView=inflater.inflate(R.layout.element_pluss,container, false);
        plusButton=plusView.findViewById(R.id._button_pluss);
        plusButton.setOnClickListener(this);
        entityContainer = view.findViewById(R.id._entityContainer);
        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View view) {
        if (view==plusButton) {
            settingsMode=!settingsMode;
            refresh();
        }
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
