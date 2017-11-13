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
    public static final int FRAGMENT_MAIN_FRAGMENT =2100;               //null
    public static final int FRAGMENT_CLEAR_WIDGETS=2101;                //null
    public static final int FRAGMENT_INSERT_WIDGET=2102;                //View
    //MENU_FRAGMENT
    public static final int FRAGMENT_MENU_FRAGMENT=2200;                //null
    //DATABASE
    public static final int DATABASE_CREATE_DB=3000;                    //SQLiteDatabase
    public static final int DATABASE_DELETE_DB=3001;                    //SQLiteDatabase
    public static final int DATABASE_GET_DB=3002;                       //DatabaseContainer
    //ENTITY_MANAGER
    public static final int ENTITY_MANAGER_RELOAD_ENTITIES =4000;       //null
    public static final int ENTITY_MANAGER_ENTITY_SET_MODIFIED =4001;   //null
    public static final int ENTITY_MANAGER_REFRESH_WIDGETS=4002;        //null
    //ENTITY_CONSTRUCTOR
    public static final int ENTITY_CONSTRUCTOR_LOAD_ENTITIES =5000;     //null
    public static final int ENTITY_CONSTRUCTOR_INSERT_CREATION_VIEW =5001; //ViewGroup
    //ENTITY
    public static final int ENTITY_DESTROY =6000;                       //null
    public static final int ENTITY_GET_HASH =6001;                      //HashSet
    public static final int ENTITY_CREATE_MAIN_VIEW=6002;               //String(HASH)
    // WALLET
    public static final int ENTITY_WALLET_NEW = 6100;                   //null
    public static final int ENTITY_WALLET_GET_DATA = 6101;              //WalletDataContainer
    //VIEW
    public static final int VIEW_GET_VIEW = 7000;                       //ViewContainer
    public static final int VIEW_DESTROY = 7001;                        //null
    public static final int VIEW_UPDATE_ALL = 7002;                     //null

}
