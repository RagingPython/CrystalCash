package ragingpython.crystalcash;


public class EventTag {
    //DEBUG
    public static final int DEBUG_TOAST=100;                    //String
    //INIT
    public static final int INIT_STAGE_EVENT_MANAGER=1000;      //EventManager
    public static final int INIT_FINAL_STAGE=1001;              //null
    //NAVIGATION
    public static final int NAVIGATION_MAIN_FRAGMENT=2000;      //null
    public static final int NAVIGATION_GO_TO_FRAGMENT=2001;     //Fragment
    //DATABASE
    public static final int DATABASE_CREATE_DB=3000;            //SQLiteDatabase
    public static final int DATABASE_DELETE_DB=3001;            //SQLiteDatabase
    public static final int DATABASE_GET_DB=3002;               //DatabaseContainer
    //ENTITIES
    public static final int ENTITIES_RELOAD_ENTITIES=4000;      //LinkedList
    public static final int ENTITIES_SELFDESTRUCT=4001;         //null
}
