package fsu.bignerdranch.elliottbregni.toodone;

/**
 * Created by elliottbregni on 3/12/17.
 */

import android.support.v4.app.Fragment;

public class TodoListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TodoListFragment();
    }
}
