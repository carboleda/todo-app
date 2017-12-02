package co.devhack.todoapp.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.devhack.todoapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryPasswordFragment extends DialogFragment {


    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }

    public static RecoveryPasswordFragment getInstance() {
        return new RecoveryPasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery_password, container, false);

        return view;
    }

}
