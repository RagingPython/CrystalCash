package ragingpython.crystalcash;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainFragment extends Fragment {
    LinearLayout entityContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_main, container, false);
        entityContainer = view.findViewById(R.id._entityContainer);
        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
    }
}
