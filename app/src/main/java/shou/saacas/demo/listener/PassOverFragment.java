package shou.saacas.demo.listener;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PassOverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PassOverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassOverFragment extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PassOverFragment.
     */
    public static PassOverFragment newInstance() {
        PassOverFragment fragment = new PassOverFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PassOverFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pass_over, container, false);
    }
}
