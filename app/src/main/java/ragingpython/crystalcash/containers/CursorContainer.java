package ragingpython.crystalcash.containers;


import android.database.Cursor;

public class CursorContainer {
    public String sql=null;
    public Cursor cursor=null;

    public CursorContainer(String string) {
        sql=string;
    }

    @Override
    protected void finalize() throws Throwable {
        if (cursor!=null) {
            cursor.close();
        }
        super.finalize();
    }
}
