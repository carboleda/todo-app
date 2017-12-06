package co.devhack.todoapp.presentation.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.devhack.todoapp.R;
import co.devhack.todoapp.presentation.presenter.TodoListContract;
import co.devhack.todoapp.presentation.presenter.TodoListPresenter;
import co.devhack.todoapp.presentation.view.adapter.TodoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements TodoListContract.View {

    private TodoListContract.UserActionsListener mActionsListener;
    private RecyclerView rvTodoList;

    public TodoListFragment() {
        // Required empty public constructor
    }

    public static TodoListFragment getInstance() {
        return new TodoListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mActionsListener = new TodoListPresenter(this);

        rvTodoList = view.findViewById(R.id.rvTodoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTodoList.setLayoutManager(layoutManager);

        TodoAdapter adapter = new TodoAdapter(mActionsListener.getLstTodos());
        rvTodoList.setAdapter(adapter);

        mActionsListener.loadAll();

        return view;
    }

    @Override
    public void refreshTodos() {
        rvTodoList.getAdapter().notifyDataSetChanged();
    }
}
