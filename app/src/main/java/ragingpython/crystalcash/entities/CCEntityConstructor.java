package ragingpython.crystalcash.entities;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


import java.util.HashSet;

import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.R;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.containers.ViewContainer;


public abstract class CCEntityConstructor implements EventReceiver, OnClickListener{
    public EventManager eventManager;
    public View createEntityView;

    public abstract void onDbCreate(SQLiteDatabase database);
    public abstract void onDbDelete(SQLiteDatabase database);
    public abstract void loadEntities();
    public abstract String getName();
    public abstract void createEntity();

    public void initialize() {
        InflateRequest inflateRequest = new InflateRequest(R.layout.element_new_widget);
        inflateRequest.view.findViewById(R.id._button_addNewWidget).setOnClickListener(this);
        ((TextView)inflateRequest.view.findViewById(R.id._textView_addingEntityName)).setText(getName());
        createEntityView=inflateRequest.view;
    }

    @Override
    public void onClick(View view){
        createEntity();
    }

    @Override
    public void destroy() {
        eventManager=null;
    }

    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager=(EventManager)o;
                initialize();
                break;
            case EventTag.DATABASE_CREATE_DB:
                onDbCreate((SQLiteDatabase)o);
                break;
            case EventTag.DATABASE_DELETE_DB:
                onDbDelete((SQLiteDatabase)o);
                break;
            case EventTag.ENTITY_CONSTRUCTOR_LOAD_ENTITIES:
                loadEntities();
                break;
            case EventTag.ENTITY_CONSTRUCTOR_GET_NAME:
                ((HashSet<String>)o).add(getName());
                break;
            case EventTag.ENTITY_CONSTRUCTOR_NEW_ENTITY:
                if (getName().compareTo((String)o)==0) {
                    createEntity();
                }
                break;
            case EventTag.ENTITY_CONSTRUCTOR_GET_ADD_VIEW:
                if (getName().compareTo(((ViewContainer)o).hash)==0) {
                    ((ViewContainer) o).view = createEntityView;
                }
                break;
        }
    }
}
