package EDEMVP;

import android.util.Log;

import java.util.HashSet;

import ragingpython.crystalcash.EventTag;

public class EventManager {
    private EventManagerSet receivers = null;

    public void registerReceiver(EventReceiver receiver) {
        Log.d("EventManager", "registred "+receiver.getClass().getName());
        if (receivers==null){
            receivers=new EventManagerSet(receiver);
        } else {
            receivers.add(receiver);
        }
        receiver.eventMapping(EventTag.INIT_SET_EVENT_MANAGER, this);
    }

    public void unRegisterReceiver(EventReceiver receiver){
        Log.d("EventManager", "unregistred "+receiver.getClass().getName());
        if (receivers!=null) {
            receivers=receivers.remove(receiver);
        }
    }

    public void broadcastEvent(int eventTag, Object o) {
        Log.d("Event",String.valueOf(eventTag));
        if (eventTag==EventTag.INIT_DESTROY){
            unRegisterReceiver((EventReceiver) o);
            ((EventReceiver) o).destroy();
        } else {
            if (receivers!=null) {
                receivers.broadcastEvent(eventTag, o);
            }
        }
    }
}
