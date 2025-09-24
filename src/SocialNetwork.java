import Helpers.Menu;
import Helpers.SafeInput;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
                new String[] {"Exit", "See feed", "List users", "Log in as user", "Add new user"},
                new Runnable[] {this::seeFeed, this::listUsers, this::logIn, this::addNewUser},
                false);
        System.out.println("Goodbye.");
    }

    private void addNewUser() {
        SafeInput si = new SafeInput(new Scanner(System.in));
        si.nameInputLoop("Enter new user name (empty to cancel)", "", "Name already exists. Try another name", userName -> {
            if (users.stream().anyMatch(user -> user.name.equals(userName))) {
                return false;
            }
            String userEmail = si.nextLine("Enter user email:").trim();
            listMenuLoop("Select user's role:", "Cancel", "No user roles found.", Arrays.asList(User.UserRoles.values()),
                    role -> {
                        try {
                            Constructor<? extends User> cons = role.userClass.getConstructor(String.class, String.class);
                            User newUser = cons.newInstance(userName, userEmail);
                            System.out.println(users.add(newUser) ? "User added." : "Failed to add user.");
                        } catch (Exception e) {
                            System.out.println("Error creating user: " + e);;
                        }
                    }, true);
            return true;
        });
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
