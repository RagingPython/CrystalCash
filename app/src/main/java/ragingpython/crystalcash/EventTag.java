package ragingpython.crystalcash;


public class EventTag {
    //DEBUG
    public static final int DEBUG_TOAST=100;                    //String
    //INIT
    public static final int INIT_SET_EVENT_MANAGER =1000;       //EventManager
    public static final int INIT_FINAL_STAGE=1001;              //null
    public static final int INIT_DESTROY=1002;                  //EventReceiver
    //NAVIGATION
    public static final int NAVIGATION_MAIN_FRAGMENT=2000;      //null
    public static final int NAVIGATION_GO_TO_FRAGMENT=2001;     //Fragment
    //DATABASE
    public static final int DATABASE_CREATE_DB=3000;            //SQLiteDatabase
    public static final int DATABASE_DELETE_DB=3001;            //SQLiteDatabase
    public static final int DATABASE_GET_DB=3002;               //DatabaseContainer
    //ENTITY_MANAGER
    public static final int ENTITY_MANAGER_RELOAD_ENTITIES =4000;//null
    //ENTITY_CONSTRUCTOR
    public static final int ENTITY_CONSTRUCTOR_LOAD_ENTITIES =5000;//null
    //ENTITY
    public static final int ENTITY_DESTROY =6000;               //null
    public static final int ENTITY_WALLET_NEW = 6100;           //null
    //VIEW
    public static final int VIEW_FIND = 7000;                   //ViewContainer

}
