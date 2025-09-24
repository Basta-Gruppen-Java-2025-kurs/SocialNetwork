import java.util.ArrayList;

public class Post implements Likeable {
    ArrayList<User> likes = new ArrayList<>();
    String content;
    String destination;
    public ArrayList<User> getLikes() {
        return likes;
    }

    Post(String content, String destination) {
        this.content = content;
        this.destination = destination;
    }
}
