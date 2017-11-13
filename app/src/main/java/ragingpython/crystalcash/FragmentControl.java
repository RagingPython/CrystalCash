package ragingpython.crystalcash;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import EDEMVP.EventManager;
import EDEMVP.EventReceiver;

class FragmentControl implements EventReceiver {
    private EventManager eventManager;
    private FragmentManager fragmentManager;
    private FrameLayout fragmentContainer;
    private Fragment currentFragment = null;
    private MainFragment mainFragment;
    private MenuFragment menuFragment;

    FragmentControl(FragmentManager fragmentManager, FrameLayout fragmentContainer) {
        this.fragmentManager=fragmentManager;
        this.fragmentContainer=fragmentContainer;
        mainFragment = new MainFragment();
        menuFragment = new MenuFragment();


    }

    private void goToFragment(Fragment fragment) {
        if (currentFragment!=fragment) {
            eventManager.broadcastEvent(EventTag.FRAGMENT_NOW_ACTIVE,null);
            eventManager.registerReceiver((EventReceiver) fragment);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(fragmentContainer.getId(), fragment);
            transaction.commit();
            fragmentManager.executePendingTransactions();
            currentFragment = fragment;
        }
        eventManager.broadcastEvent(EventTag.FRAGMENT_NOW_ACTIVE,null);
    }

    @Override
    public void destroy() {
        eventManager=null;
    }


    @Override
    public void eventMapping(int eventTag, Object o) {
        switch (eventTag) {
            case EventTag.INIT_SET_EVENT_MANAGER:
                eventManager = (EventManager) o;
                break;
            case EventTag.FRAGMENT_MAIN_FRAGMENT:
                goToFragment(mainFragment);
                break;
            case EventTag.FRAGMENT_GO_TO_FRAGMENT:
                goToFragment((Fragment) o);
                break;
            case EventTag.FRAGMENT_MENU_FRAGMENT:
                goToFragment(menuFragment);
                break;
            case EventTag.ACTIVITY_BACK_BUTTON:
                if (currentFragment==mainFragment) {
                    eventManager.broadcastEvent(EventTag.ACTIVITY_EXIT,null);
                } else {
                    goToFragment(mainFragment);
                }
                break;
        }
    }
}
