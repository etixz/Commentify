package eb.egonb.commentify.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Entity;

import java.util.List;

import eb.egonb.commentify.model.Comment;
import eb.egonb.commentify.model.CommentDAO;
import eb.egonb.commentify.model.CommentDB;

public class CommentViewModel extends AndroidViewModel {

    private final LiveData<List<Comment>> COMMENTS;
    private CommentDB databass;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        databass = CommentDB.getSharedInstance(application);
        COMMENTS = databass.getCommentDAO().getAllComments();
    }

    public LiveData<List<Comment>> getCOMMENTS(){
        return COMMENTS;
    }

    public void insertComment(Comment comment){
        CommentDB.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                databass.getCommentDAO().insertComment(comment);
            }
        });
    }
}
