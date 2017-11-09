package ragingpython.crystalcash.entities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;


public class CCEntityView extends View implements EventReceiver {
    EventManager eventManager;

    public CCEntityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager) o;
                break;
        }
    }
}
