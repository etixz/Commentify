package eb.egonb.commentify.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDAO {

    @Insert
    void insertComment(Comment comment);

    @Query(value = "SELECT * FROM Comment")
    LiveData<List<Comment>> getAllComments();

}
