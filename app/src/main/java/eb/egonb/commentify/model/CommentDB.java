package eb.egonb.commentify.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Comment.class}, exportSchema = false)
public abstract class CommentDB extends RoomDatabase {

    private static CommentDB sharedInstance;

    public static CommentDB getSharedInstance(Context context){
        if(sharedInstance == null){
            createDB(context);
        }
        return sharedInstance;
    }

    private static void createDB(Context context) {
        sharedInstance = Room.databaseBuilder(context, CommentDB.class, "comment_db").build();
    }

    public abstract CommentDAO getCommentDAO();

    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);

}
