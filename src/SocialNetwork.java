import Helpers.Menu;

import java.util.ArrayList;

public class SocialNetwork implements Menu {
    private static SocialNetwork instance;
    private ArrayList<Post> posts;
    private ArrayList<User> users;
    private SocialNetwork() {

    }
    public static SocialNetwork getInstance() {
        if (instance == null) {
            instance = new SocialNetwork();
        }
        return instance;
    }

    @Override
    public void menu() {

    }
}
