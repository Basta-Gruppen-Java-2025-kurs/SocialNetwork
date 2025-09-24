import Helpers.Menu;
import Helpers.Named;

import java.util.ArrayList;

public class Post implements Likeable, Named, Menu {
    ArrayList<User> likes = new ArrayList<>();
    int id;
    String content;
    String destination;
    public ArrayList<User> getLikes() {
        return likes;
    }

    Post(String content, String destination) {
        this.content = content;
        this.destination = destination;
        id = this.hashCode();
    }

    @Override
    public String getName() {
        return "" + id;
    }

    @Override
    public void menu() {

    }
}
