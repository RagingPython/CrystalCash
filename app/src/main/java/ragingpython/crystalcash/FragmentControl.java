package ragingpython.crystalcash;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;
import EDEMVP.EventManager;
import EDEMVP.EventReceiver;
import EDEMVP.HoldingEventManager;

class FragmentControl implements EventReceiver {
    private FragmentManager fragmentManager;
    private EventManager eventManager;
    private FrameLayout fragmentContainer;
    private HoldingEventManager viewState;
    private Fragment currentFragment = null;

    FragmentControl(FragmentManager fragmentManager, FrameLayout fragmentContainer) {
        this.fragmentManager=fragmentManager;
        this.fragmentContainer=fragmentContainer;
    }

    private void goToFragment(Fragment fragment) {
        if (currentFragment!=null) {
            viewState.unRegisterReceiver((EventReceiver) currentFragment);
        }
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(fragmentContainer.getId(),fragment);
        transaction.commit();
        fragmentManager.executePendingTransactions();
        currentFragment=fragment;
        viewState.registerReceiver((EventReceiver) fragment);
    }


    @Override
    public void eventMapping(int eventTag, Object o) {

    }
}
