package ragingpython.crystalcash.entities.wallet;

import android.database.Cursor;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ragingpython.crystalcash.EventTag;
import ragingpython.crystalcash.R;
import ragingpython.crystalcash.containers.CursorContainer;
import ragingpython.crystalcash.containers.InflateRequest;
import ragingpython.crystalcash.entities.CCEntity;


public class Wallet extends CCEntity implements View.OnClickListener, TextView.OnEditorActionListener{
    private int id;
    private Button buttonDelete;
    private EditText newWalletName;
    private TextView textViewName, textViewBalance;
    private View displayView, settingsView;
    private boolean isLocked=false;


    public Wallet(Cursor cursor) {
        id=cursor.getInt(cursor.getColumnIndex("id"));
    }

    @Override
    public View createMainView()
    {
        InflateRequest inflateRequest = new InflateRequest(R.layout.wallet_main);
        eventManager.broadcastEvent(EventTag.ACTIVITY_INFLATE, inflateRequest);
        displayView=inflateRequest.view;
        inflateRequest.resourceID=R.layout.wallet_settings;
        eventManager.broadcastEvent(EventTag.ACTIVITY_INFLATE, inflateRequest);
        settingsView=inflateRequest.view;

        textViewName=displayView.findViewById(R.id._textView_walletName);
        textViewBalance=displayView.findViewById(R.id._textView_walletBalance);
        buttonDelete=settingsView.findViewById(R.id._button_wallet_delete);
        newWalletName=settingsView.findViewById(R.id._editText_wallet_newName);

        buttonDelete.setOnClickListener(this);
        displayView.setOnClickListener(this);
        newWalletName.setOnEditorActionListener(this);


        return displayView;
    }

    @Override
    public String getHash() {
        return "wallet(id="+ String.valueOf(id)+")";
    }

    @Override
    public void refresh() {
        CursorContainer cursorContainer = new CursorContainer("select w.name, sum(wo.amount) from wallet as w left join wallet_operations as wo on w.id=w0.wallet_id where w.id=" + String.valueOf(id) + " group by w.name");
        eventManager.broadcastEvent(EventTag.DATABASE_RAW_QUERY, cursorContainer);
        Cursor cursor = cursorContainer.cursor;
        cursor.moveToFirst();
        if (currentView==displayView) {
            Double balance;
            if (cursor.getCount() == 0) {
                balance = 0.0;
            } else {
                balance = cursor.getDouble(1);
            }
            textViewName.setText(cursor.getString(0));
            textViewBalance.setText(String.valueOf(balance));
        } else if (currentView==settingsView) {
            newWalletName.setText(cursor.getString(0));
        }
    }

    @Override
    public void setMode(boolean mode) {
        if (mode) {
            currentView=settingsView;
        } else {
            currentView=displayView;
        }
    }

    @Override
    public void onClick(View view) {
        if (view==displayView) {
            isLocked=!isLocked;
            if (isLocked) {
                eventManager.broadcastEvent(EventTag.FRAGMENT_MAIN_LOCK_ON, this);
            } else {
                eventManager.broadcastEvent(EventTag.FRAGMENT_MAIN_UNLOCK, null);
            }
        }   else if (view==buttonDelete) {
            eventManager.broadcastEvent(EventTag.DATABASE_EXEC_SQL, "delete from wallet where id=" + String.valueOf(id));
            eventManager.broadcastEvent(EventTag.ENTITY_MANAGER_RELOAD_ENTITIES,null);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i== EditorInfo.IME_ACTION_DONE) {
            eventManager.broadcastEvent(EventTag.DATABASE_EXEC_SQL, "update wallet set name=" + textView.getText()+"where id="+String.valueOf(id));
            refresh();
        }
        return false;
    }
}
