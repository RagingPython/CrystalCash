package ragingpython.crystalcash;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.Toast;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.containers.InflateRequest;
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
        eventManager.registerReceiver(new EntityManager());
        eventManager.registerReceiver(new DBOpenHelper(getApplicationContext()));
        eventManager.registerReceiver(new FragmentControl(getFragmentManager(), fragmentContainer));


        eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_RELOAD_ENTITIES, null);
    }

    private void doInflate(InflateRequest inflateRequest){
        LayoutInflater layoutInflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        inflateRequest.view=layoutInflater.inflate(inflateRequest.resourceID,inflateRequest.viewContainer,inflateRequest.attachToRoot);
    }


    @Override
    protected void onResume() {
        super.onResume();
        eventManager.broadcastEvent(EventTag.FRAGMENT_MAIN_ACTIVATE, null);
    }

    @Override
    public void onBackPressed() {
        eventManager.broadcastEvent(EventTag.ACTIVITY_BACK_BUTTON,null);
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
            case EventTag.ACTIVITY_TOAST:
                Toast.makeText(this, (String) o, Toast.LENGTH_SHORT).show();
                break;
            case EventTag.ACTIVITY_INFLATE:
                doInflate((InflateRequest) o);
                break;
            case EventTag.ACTIVITY_EXIT:
                finish();
                break;
        }
    }
}
