package ragingpython.crystalcash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import EDEMVP.HoldingEventManager;

public class MainActivity extends Activity implements EventReceiver{
    EventManager eventManager;
    FragmentControl fragmentControl;
    FrameLayout fragmentContainer;
    DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer=findViewById(R.id._fragmentContainer);

        eventManager = new EventManager();
        dbOpenHelper = new DBOpenHelper(getApplicationContext());
        fragmentControl = new FragmentControl(getFragmentManager(),fragmentContainer);

        eventManager.registerReceiver(this);
        eventManager.registerReceiver(dbOpenHelper);
        eventManager.registerReceiver(fragmentControl);


        eventManager.broadcastEvent(EventTag.INIT_STAGE_EVENT_MANAGER, eventManager);

        eventManager.broadcastEvent(EventTag.INIT_FINAL_STAGE, null);
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.DEBUG_TOAST:
                Toast.makeText(this, (String) o, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
