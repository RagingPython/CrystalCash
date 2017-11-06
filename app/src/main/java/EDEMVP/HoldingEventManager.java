package EDEMVP;

import java.util.HashMap;


public class HoldingEventManager extends EventManager {
    private HashMap<Integer, Object> heldEvents = new HashMap<Integer, Object>();

    @Override
    public void broadcastEvent(int eventTag, Object o) {
        heldEvents.put(eventTag, o);
        super.broadcastEvent(eventTag, o);
    }

    public Object getEvent(int eventTag) {
        return heldEvents.get(eventTag);
    }

    public void removeHeldEvent(int eventTag) {
        heldEvents.remove(eventTag);
    }

    public void reBroadcastEvent(int eventTag){
        if (heldEvents.containsKey(eventTag)){
            broadcastEvent(eventTag, heldEvents.get(eventTag));
        }
    }

    @Override
    public void registerReceiver(EventReceiver receiver) {
        super.registerReceiver(receiver);
        for(int i:heldEvents.keySet()) {
            receiver.eventMapping(i, heldEvents.get(i));
        }
    }
}
