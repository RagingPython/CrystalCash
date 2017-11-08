package ragingpython.crystalcash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import EDEMVP.HoldingEventManager;
import ragingpython.crystalcash.entities.EntityManager;

public class MainActivity extends Activity implements EventReceiver{
    EventManager eventManager;
    FragmentControl fragmentControl;
    FrameLayout fragmentContainer;
    DBOpenHelper dbOpenHelper;
    EntityManager entityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer=findViewById(R.id._fragmentContainer);

        eventManager = new EventManager();
        dbOpenHelper = new DBOpenHelper(getApplicationContext());
        fragmentControl = new FragmentControl(getFragmentManager(),fragmentContainer);
        entityManager = new EntityManager();

        eventManager.registerReceiver(this);
        eventManager.registerReceiver(dbOpenHelper);
        eventManager.registerReceiver(fragmentControl);
        eventManager.registerReceiver(entityManager);


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
