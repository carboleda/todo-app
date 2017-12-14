package co.devhack.todoapp.presentation.view.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.devhack.todoapp.R;
import co.devhack.todoapp.presentation.presenter.TodoListContract;
import co.devhack.todoapp.presentation.presenter.TodoListPresenter;
import co.devhack.todoapp.presentation.view.activity.MainActivity;
import co.devhack.todoapp.presentation.view.adapter.TodoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements TodoListContract.View {

    public static final String RECEIVER_COUNTER = "RECEIVER_COUNTER";

    private TodoListContract.UserActionsListener mActionsListener;
    private RecyclerView rvTodoList;
    private TextView tvCounter;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvCounter.setText(String.valueOf(intent.getIntExtra("count", 0)));
        }
    };

    public TodoListFragment() {
        // Required empty public constructor
    }

    public static TodoListFragment getInstance() {
        return new TodoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setEnableBackbutton(false);

        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mActionsListener = new TodoListPresenter(this);

        rvTodoList = view.findViewById(R.id.rvTodoList);
        tvCounter = view.findViewById(R.id.tvCounter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTodoList.setLayoutManager(layoutManager);

        TodoAdapter adapter = new TodoAdapter(mActionsListener.getLstTodos());
        rvTodoList.setAdapter(adapter);

        mActionsListener.loadAll();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVER_COUNTER);
        getContext().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(receiver);
    }

    @Override
    public void refreshTodos() {
        rvTodoList.getAdapter().notifyDataSetChanged();
    }
}
