package EDEMVP;


public class EventManagerSet {
    EventManagerSet next=null;
    EventReceiver eventReceiver = null;

    public EventManagerSet(EventReceiver eventReceiver) {
        this.eventReceiver=eventReceiver;
    }

    public void add(EventReceiver eventReceiver){
        if (this!=eventReceiver) {
            if (next!=null) {
                next.add(eventReceiver);
            } else {
                next = new EventManagerSet(eventReceiver);
            }
        }
    }

    public EventManagerSet remove(EventReceiver eventReceiver) {
        if (this.eventReceiver==eventReceiver) {
            this.eventReceiver=null;
            return next;
        }   else {
            if (next!=null) {
                next = next.remove(eventReceiver);
            }
            return this;
        }
    }

    public void extinct(){
        if(next!=null){
            next.extinct();
        }
        next=null;
        eventReceiver=null;
    }

    public void chainBroadcast(int tag, Object o){
        eventReceiver.eventMapping(tag, o);
        if (next!=null) {
            next.chainBroadcast(tag, o);
        }
    }

    public void broadcastEvent(int tag, Object o) {
        EventManagerSet tmpSetStart = new EventManagerSet(this.eventReceiver);
        EventManagerSet tmpSetPointer = this.next;

        while (tmpSetPointer!=null) {
            tmpSetStart.add(tmpSetPointer.eventReceiver);
            tmpSetPointer=tmpSetPointer.next;
        }
        tmpSetStart.chainBroadcast(tag, o);
        tmpSetStart.extinct();
    }
}
