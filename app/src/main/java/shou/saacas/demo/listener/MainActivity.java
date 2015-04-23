package shou.saacas.demo.listener;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements ConfirmDialogFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new TopFragment())
                    .commit();
        }
    }

    @Override
    public void onConfirmed() {
        PassOverFragment fragment = (PassOverFragment)getFragmentManager().findFragmentByTag("pass_over");
        fragment.setConfirmed(true);
    }
}
