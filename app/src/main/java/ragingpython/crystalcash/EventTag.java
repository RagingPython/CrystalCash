package ragingpython.crystalcash;


public class EventTag {
    //ACTIVITY
    public static final int ACTIVITY_TOAST=100;                         //String
    public static final int ACTIVITY_INFLATE=101;                       //InflateRequest
    public static final int ACTIVITY_BACK_BUTTON=102;                   //null
    public static final int ACTIVITY_EXIT=103;                          //null
    //INIT
    public static final int INIT_SET_EVENT_MANAGER =1000;               //EventManager
    public static final int INIT_DESTROY=1002;                          //EventReceiver
    //FRAGMENTS
    public static final int FRAGMENT_GO_TO_FRAGMENT =2000;              //Fragment
    public static final int FRAGMENT_NOW_ACTIVE =2001;                  //null
    public static final int FRAGMENT_NOW_NOT_ACTIVE =2002;              //null
    //MAIN_FRAGMENT
    public static final int FRAGMENT_MAIN_ACTIVATE =2100;               //null
    public static final int FRAGMENT_MAIN_REFRESH=2101;                 //null
    //DATABASE
    public static final int DATABASE_CREATE_DB=3000;                    //SQLiteDatabase
    public static final int DATABASE_DELETE_DB=3001;                    //SQLiteDatabase
    public static final int DATABASE_EXEC_SQL=3002;                     //String
    public static final int DATABASE_RAW_QUERY =3003;                   //CursorContainer
    //ENTITY_MANAGER
    public static final int ENTITY_MANAGER_RELOAD_ENTITIES =4000;       //null
    public static final int ENTITY_MANAGER_INSERT_WIDGETS=4001;         //ViewGroup
    //ENTITY_CONSTRUCTOR
    public static final int ENTITY_CONSTRUCTOR_LOAD_ENTITIES =5000;     //null
    public static final int ENTITY_CONSTRUCTOR_GET_NAME =5001;          //HashSet
    public static final int ENTITY_CONSTRUCTOR_NEW_ENTITY =5002;        //String(name)
    public static final int ENTITY_CONSTRUCTOR_GET_ADD_VIEW =5003;      //ViewContainer
    //ENTITY
    public static final int ENTITY_DESTROY =6000;                       //null
    public static final int ENTITY_GET_HASH =6001;                      //HashSet
    public static final int ENTITY_GET_MAIN_VIEW=6002;                  //ViewContainer
    public static final int ENTITY_REFRESH=6003;                        //null (or String(hash))
}
