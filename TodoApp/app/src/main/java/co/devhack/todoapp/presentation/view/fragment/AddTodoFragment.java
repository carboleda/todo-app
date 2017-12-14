package co.devhack.todoapp.presentation.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private AddTodoContract.UserActionsListener mActionsListener;
    private TextInputLayout tilDescription;
    private EditText etFinishDate;
    private ImageButton btnSelectDate;
    private ImageView ivTakePhoto;
    private Button btnSave;
    private FloatingActionButton fab;
    private Bitmap imageBitmap;

    public AddTodoFragment() {
        // Required empty public constructor
    }

    public static AddTodoFragment getInstance() {
        return new AddTodoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setEnableBackbutton(true);

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
                onTakePhoto();
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

    private void onTakePhoto() {
        int isGrantedCamera = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        int isGrantedStorage = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(isGrantedCamera == android.content.pm.PackageManager.PERMISSION_GRANTED
                && isGrantedStorage == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    REQUEST_IMAGE_CAPTURE);
        }
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
                String image = "";
                if(imageBitmap != null) {
                    image = Utilities.saveImage(getContext(), imageBitmap);
                }
                mActionsListener.onSave(description, false, finishDate, image);
            }
        } catch (Exception e) {
            showMessageError(e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivTakePhoto.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            if(grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                onTakePhoto();
            }
        }
    }
}

