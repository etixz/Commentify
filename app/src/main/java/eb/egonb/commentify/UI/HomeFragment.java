package eb.egonb.commentify.UI;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import eb.egonb.commentify.R;
import eb.egonb.commentify.UI.util.CommentAdapter;
import eb.egonb.commentify.model.Comment;
import eb.egonb.commentify.viewmodel.CommentViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentActivity myContext;
    private EditText userET;
    private EditText contentET;
    private Button btnPost;
    private RecyclerView commentsRV;
    private CommentViewModel model;


    private View.OnClickListener postCommentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = userET.getText().toString();
            String content = contentET.getText().toString();
            if(user.isEmpty() || content.isEmpty()){
                return;
            }
            Comment c = new Comment(user, content);
            model.insertComment(c);
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        model = new ViewModelProvider(myContext).get(CommentViewModel.class);

        userET = rootView.findViewById(R.id.et_username);
        contentET = rootView.findViewById(R.id.et_content);
        btnPost = rootView.findViewById(R.id.btn_post);
        commentsRV = rootView.findViewById(R.id.rv_comments);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(myContext, RecyclerView.VERTICAL, false);

        CommentAdapter adapter = new CommentAdapter();
        commentsRV.setAdapter(adapter);
        commentsRV.setLayoutManager(layoutManager);

        model.getCOMMENTS().observe(myContext, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                adapter.addItems(comments);
                adapter.notifyDataSetChanged();
            }
        });

        btnPost.setOnClickListener(postCommentListener);

        return rootView;
    }

}
