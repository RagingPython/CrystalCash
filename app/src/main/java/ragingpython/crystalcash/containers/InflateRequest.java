package ragingpython.crystalcash.containers;


import android.view.View;
import android.view.ViewGroup;

public class InflateRequest {
    public View view=null;
    public int resourceID=0;
    public boolean attachToRoot=false;
    public ViewGroup viewContainer=null;

    public InflateRequest(int resourceID){
        this.resourceID=resourceID;
    }
}
