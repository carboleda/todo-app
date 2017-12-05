package co.devhack.todoapp.presentation.view.dialog;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.devhack.todoapp.R;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.presentation.presenter.RecoveryPasswordContract;
import co.devhack.todoapp.presentation.presenter.RecoveryPasswordPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecoveryPasswordFragment extends DialogFragment
        implements RecoveryPasswordContract.View, View.OnClickListener {

    private RecoveryPasswordContract.UserActionsListener mActionsListener;
    private TextInputLayout tilEmail;
    private TextView tvMessages;
    private Button btnRecovery;
    private Button btnAccept;

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

        mActionsListener = new RecoveryPasswordPresenter(this);

        tilEmail = view.findViewById(R.id.tilEmail);
        tvMessages = view.findViewById(R.id.tvMessages);
        btnRecovery = view.findViewById(R.id.btnRecovery);
        btnAccept = view.findViewById(R.id.btnAccept);

        btnRecovery.setOnClickListener(this);
        btnAccept.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRecovery:
                onRecovery();
                break;
            case R.id.btnAccept:
                dismiss();
                break;
        }
    }

    @Override
    public void showErrorMessage(Exception error) {
        tvMessages.setText(error.getMessage());
        tvMessages.setTextColor(getResources().getColor(R.color.error_message));
    }

    @Override
    public void showSuccessMessage() {
        tvMessages.setText(R.string.recovery_email_sent);
        tvMessages.setTextColor(getResources().getColor(R.color.success_message));
        btnRecovery.setVisibility(View.GONE);
        btnAccept.setVisibility(View.VISIBLE);
    }

    private void onRecovery() {
        try {
            boolean result = true;

            String email = tilEmail.getEditText().getText().toString();
            if(Utilities.isEmpty(email)) {
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            if(result) {
                mActionsListener.onRecovery(email);
            }
        } catch (Exception e) {
            showErrorMessage(e);
        }
    }
}
