import Helpers.Menu;
import Helpers.MenuHelper;

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
        MenuHelper.menuLoop("Social Network\n\nNext action:",
                new String[] {"Exit", "See feed", "List users", "Log in as user"},
                new Runnable[] {this::seeFeed, this::listUsers, this::logIn},
                false);
        System.out.println("Goodbye.");
    }

    private void seeFeed() {

    }

    private void listUsers() {

    }

    private void logIn() {

    }
}
