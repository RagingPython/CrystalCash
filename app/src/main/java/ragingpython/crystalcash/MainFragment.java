package ragingpython.crystalcash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;

public class MainFragment extends Fragment implements View.OnClickListener, EventReceiver{
    LinearLayout entityContainer;
    Button buttonCreateWallet;
    EventManager eventManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        entityContainer = view.findViewById(R.id._entityContainer);
        buttonCreateWallet = view.findViewById(R.id._buttonCreateWallet);
        buttonCreateWallet.setOnClickListener(this);
        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View view) {
        eventManager.broadcastEvent(EventTag.ENTITY_WALLET_NEW,"test!");
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
            case EventTag.FRAGMENT_NOW_ACTIVE:
                eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_REFRESH_WIDGETS,null);
                break;
            case EventTag.FRAGMENT_CLEAR_WIDGETS:
                entityContainer.removeAllViews();
                break;
            case EventTag.FRAGMENT_INSERT_WIDGET:
                entityContainer.addView((View) o);
                break;
        }
    }
}
