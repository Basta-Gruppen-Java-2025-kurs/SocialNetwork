import Helpers.Menu;

import java.util.ArrayList;

import static Helpers.MenuHelper.*;

public class SocialNetwork implements Menu {
    private static SocialNetwork instance;

    public final ArrayList<Post> posts = new ArrayList<>();
    public final ArrayList<User> users = new ArrayList<>();
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
        menuLoop("Social Network\n\nNext action:",
                new String[] {"Exit", "See feed", "List users", "Log in as user"},
                new Runnable[] {this::seeFeed, this::listUsers, this::logIn},
                false);
        System.out.println("Goodbye.");
    }

    private void seeFeed() {
        if (posts.isEmpty()) {
            System.out.println("No posts found.");
            return;
        }
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    private void listUsers() {
        if (users.isEmpty()) {
            System.out.println("There are no users.");
            return;
        }
        for (User user : users) {
            System.out.println("* " + user.getName() + " - " + user.getClass().getSimpleName());
        }
    }

    private void logIn() {
        listMenuLoop("Select user", "Cancel", "No users found.", users, User::menu, true);
    }
}
