package EDEMVP;

import android.util.Log;

import java.util.HashSet;

import ragingpython.crystalcash.EventTag;

public class EventManager {
    private HashSet<EventReceiver> receivers = new HashSet<EventReceiver>();

    public void registerReceiver(EventReceiver receiver) {
        receivers.add(receiver);
        receiver.eventMapping(EventTag.INIT_SET_EVENT_MANAGER, this);
    }

    public void unRegisterReceiver(EventReceiver receiver){
        receivers.remove(receiver);
    }

    public void broadcastEvent(int eventTag, Object o) {
        Log.d("Event",String.valueOf(eventTag));
        if (eventTag==EventTag.INIT_DESTROY){
            unRegisterReceiver((EventReceiver) o);
            ((EventReceiver) o).destroy();
        } else {
            for (EventReceiver e : receivers) {
                e.eventMapping(eventTag, o);
            }
        }
    }
}
