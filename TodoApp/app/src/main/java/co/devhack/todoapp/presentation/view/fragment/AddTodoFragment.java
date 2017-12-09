package co.devhack.todoapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Date;

import co.devhack.todoapp.R;
import co.devhack.todoapp.helpers.Utilities;
import co.devhack.todoapp.presentation.presenter.AddTodoContract;
import co.devhack.todoapp.presentation.presenter.AddTodoPresenter;
import co.devhack.todoapp.presentation.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoFragment extends Fragment implements AddTodoContract.View, View.OnClickListener {

    private AddTodoContract.UserActionsListener mActionsListener;
    private TextInputLayout tilDescription;
    private EditText etFinishDate;
    private ImageButton btnSelectDate;
    private ImageView ivTakePhoto;
    private Button btnSave;
    private FloatingActionButton fab;

    public AddTodoFragment() {
        // Required empty public constructor
    }

    public static AddTodoFragment getInstance() {
        return new AddTodoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_todo, container, false);

        fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        mActionsListener = new AddTodoPresenter(this);

        tilDescription = view.findViewById(R.id.tilDescription);
        etFinishDate = view.findViewById(R.id.etFinishDate);
        btnSelectDate = view.findViewById(R.id.btnSelectDate);
        ivTakePhoto = view.findViewById(R.id.ivTakePhoto);
        btnSave = view.findViewById(R.id.btnSave);

        btnSelectDate.setOnClickListener(this);
        ivTakePhoto.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        fab.setVisibility(View.VISIBLE);
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSelectDate:
                //TODO IMPLEMENTAR DIALOG PARA SELECCIONAR FECHA
                break;
            case R.id.ivTakePhoto:
                //TODO IMPLEMENTAR CAPTURA DE FOTO
                break;
            case R.id.btnSave:
                onSave();
                break;
        }
    }

    @Override
    public void goToTodoListFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.replaceFragment(TodoListFragment.getInstance(), true);
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void onSave() {
        try {
            boolean result = true;
            String description = tilDescription.getEditText().getText().toString();
            Date finishDate = new Date();

            if(Utilities.isEmpty(description)) {
                tilDescription.setError(getString(R.string.is_required));
                tilDescription.setErrorEnabled(true);
                result = false;
            } else {
                tilDescription.setError(null);
                tilDescription.setErrorEnabled(false);
            }

            if(result) {
                mActionsListener.onSave(description, false, finishDate, "");
            }
        } catch (Exception e) {
            showMessageError(e);
        }
    }
}

