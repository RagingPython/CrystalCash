package ragingpython.crystalcash.entities.wallet;

import android.view.View;
import android.widget.TextView;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.R;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.entities.CCEntityView;


public class WalletMainView extends CCEntityView {
    TextView walletName,walletBalance;

    public WalletMainView(String hash) {
        super(hash);
    }

    @Override
    public View createMainView() {
        InflateRequest inflateRequest = new InflateRequest(R.layout.wallet_main);
        eventManager.broadcastEvent(EventTag.ACTIVITY_INFLATE, inflateRequest);
        walletName=inflateRequest.view.findViewById(R.id._textView_walletName);
        walletBalance=inflateRequest.view.findViewById(R.id._textView_walletBalance);
        return inflateRequest.view;
    }
}
