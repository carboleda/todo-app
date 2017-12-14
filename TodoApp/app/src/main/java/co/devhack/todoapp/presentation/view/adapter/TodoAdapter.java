package co.devhack.todoapp.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import co.devhack.todoapp.R;
import co.devhack.todoapp.domain.model.Todo;
import co.devhack.todoapp.helpers.Utilities;

/**
 * Created by krlosf on 5/12/17.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> dataSet;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd 'de' MMM 'de' yyyy");

    public TodoAdapter(List<Todo> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = dataSet.get(position);

        holder.cbFinished.setChecked(todo.getFinished() != null && todo.getFinished());
        holder.cbFinished.setText(todo.getDescription());
        if(!Utilities.isEmpty(todo.getImage())) {
            Glide.with(holder.itemView).load(todo.getImage()).into(holder.ivPhoto);
        }
        String finishDate = holder.itemView.getContext()
                .getString(R.string.finish_date_template, simpleDateFormat.format(todo.getFinishDate()));
        holder.tvFinishDate.setText(finishDate);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        public CheckBox cbFinished;
        public ImageView ivPhoto;
        public TextView tvFinishDate;

        public TodoViewHolder(View itemView) {
            super(itemView);

            cbFinished = itemView.findViewById(R.id.cbFinished);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvFinishDate = itemView.findViewById(R.id.tvFinishDate);
        }
    }

}
