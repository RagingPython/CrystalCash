package ragingpython.crystalcash;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.entities.EntityManager;

public class MainActivity extends Activity implements EventReceiver{
    EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout fragmentContainer=findViewById(R.id._fragmentContainer);

        eventManager = new EventManager();

        eventManager.registerReceiver(this);
        eventManager.registerReceiver(new DBOpenHelper(getApplicationContext()));
        eventManager.registerReceiver(new FragmentControl(getFragmentManager(), fragmentContainer));
        eventManager.registerReceiver(new EntityManager());

        eventManager.broadcastEvent(EventTag.INIT_FINAL_STAGE, null);
    }

    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager= (EventManager) o;
                break;
            case EventTag.DEBUG_TOAST:
                Toast.makeText(this, (String) o, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
