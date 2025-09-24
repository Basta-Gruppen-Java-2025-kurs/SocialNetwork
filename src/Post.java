import java.util.ArrayList;

public class Post implements Likeable {
    ArrayList<User> likes = new ArrayList<>();
    public ArrayList<User> getLikes() {
        return likes;
    }
}
