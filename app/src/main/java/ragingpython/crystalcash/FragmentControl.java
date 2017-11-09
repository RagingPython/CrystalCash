package ragingpython.crystalcash;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import EDEMVP.HoldingEventManager;

class FragmentControl implements EventReceiver {
    private EventManager eventManager;
    private FragmentManager fragmentManager;
    private FrameLayout fragmentContainer;
    private Fragment currentFragment = null;
    private MainFragment mainFragment;

    FragmentControl(FragmentManager fragmentManager, FrameLayout fragmentContainer) {
        this.fragmentManager=fragmentManager;
        this.fragmentContainer=fragmentContainer;
        mainFragment = new MainFragment();

    }

    private void goToFragment(Fragment fragment) {
        if (currentFragment!=null) {
            eventManager.unRegisterReceiver((EventReceiver) currentFragment);
        }
        eventManager.registerReceiver((EventReceiver) fragment);
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(fragmentContainer.getId(),fragment);
        transaction.commit();
        fragmentManager.executePendingTransactions();
        currentFragment=fragment;
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
            case EventTag.NAVIGATION_MAIN_FRAGMENT:
            case EventTag.INIT_FINAL_STAGE:
                goToFragment(mainFragment);
                break;
            case EventTag.NAVIGATION_GO_TO_FRAGMENT:
                goToFragment((Fragment) o);
                break;
        }
    }
}
