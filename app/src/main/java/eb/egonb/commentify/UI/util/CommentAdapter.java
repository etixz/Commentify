package eb.egonb.commentify.UI.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import eb.egonb.commentify.R;
import eb.egonb.commentify.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.RowViewHolder> {

    class RowViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUser, tvContent;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    private List<Comment> items;

    public CommentAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);

        return new RowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RowViewHolder holder, int position) {
            Comment c = items.get(position);
            holder.tvUser.setText(c.getUsername());
            holder.tvContent.setText(c.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Comment> newItems){
        items.clear();
        items.addAll(newItems);
    }
}
