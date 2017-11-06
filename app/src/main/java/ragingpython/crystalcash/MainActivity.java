package ragingpython.crystalcash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import EDEMVP.EventManager;
import EDEMVP.HoldingEventManager;

public class MainActivity extends Activity {
    EventManager eventManager;
    HoldingEventManager viewState;
    FragmentControl fragmentControl;
    FrameLayout fragmentContainer;
    DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer=findViewById(R.id._fragmentContainer);

        eventManager = new EventManager();
        viewState = new HoldingEventManager();

        fragmentControl = new FragmentControl(getFragmentManager(),fragmentContainer);
        dbController = new DBController(getApplicationContext());


        eventManager.registerReceiver(fragmentControl);
        eventManager.registerReceiver(dbController);


        eventManager.broadcastEvent(EventTag.INIT_STAGE_VIEW_STATE, viewState);
        eventManager.broadcastEvent(EventTag.INIT_STAGE_EVENT_MANAGER, eventManager);
        viewState.broadcastEvent(EventTag.INIT_STAGE_VIEW_STATE, viewState);
        viewState.broadcastEvent(EventTag.INIT_STAGE_EVENT_MANAGER, eventManager);

        eventManager.broadcastEvent(EventTag.INIT_FINAL_STAGE, null);
    }
}
