import Helpers.Menu;
import Helpers.SafeInput;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Helpers.MenuHelper.*;

public class SocialNetwork implements Menu {
    private static SocialNetwork instance;
    public final ArrayList<Post> posts = new ArrayList<>();
    public final ArrayList<User> users = new ArrayList<>();
    private User loggedInAs = null;
    private SocialNetwork() {
    }
    public static SocialNetwork getInstance() {
        if (instance == null) {
            instance = new SocialNetwork();
        }
        return instance;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public User getCurrentUser() {
        return loggedInAs;
    }

    private String menuHeader() {
        return "\n--- Social Network Menu " + (loggedInAs != null ? "- logged in as \033[1m" + loggedInAs.name + "\033[0m " : "") + " ---\nSelect an action:";
    }

    private String[] menuOptions() {
        if (loggedInAs == null) {
            return new String[]{"Exit ❌", "See feed", "List users", "Log in as user", "Add new user"};
        } else {
            return new String[]{"Exit ❌", "See feed", "List users", "User menu", "Handle posts", "Log out", "Add new user"};
        }
    }

    private Runnable[] menuActions() {
        if (loggedInAs == null) {
            return new Runnable[]{this::seeFeed, this::listUsers, this::logIn, this::addNewUser};
        } else {
            return new Runnable[]{this::seeFeed, this::listUsers, loggedInAs::menu, this::handlePosts, this::logOut, this::addNewUser};
        }
    }
    @Override
    public void menu() {
        System.out.println("\n╔═════════════════════════════════╗");
        System.out.println("🌐 WELCOME TO THE SOCIAL NETWORK 🌐");
        System.out.println("╚═════════════════════════════════╝");
        menuLoop(this::menuHeader, this::menuOptions, this::menuActions, false);
        System.out.println("Goodbye.");
    }

    private void logOut() {
        loggedInAs = null;
        System.out.println("Logged out");
    }

    public void initializeState() {
        User johnSmith = new RegularUser("John Smith", "jsmith@forgery.hit");
        users.add(johnSmith);
        User peterPane = new Moderator("Peter Pane", "pain@suffering.com");
        users.add(peterPane);
        User yiLinTao = new AdminUser("Yi Lin Tao", "yilintao@nomeans.no");
        users.add(yiLinTao);
        loggedInAs = yiLinTao;
        posts.add(new Post("Hello world!", "Everyone"));
        loggedInAs = johnSmith;
        posts.add(new Post("Inappropriate content", "Everyone"));
        loggedInAs = peterPane;
        posts.add(new Post("I am so tired of this work", "Peter's blog"));
        loggedInAs = null;
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
                            Constructor<? extends User> cons = role.userClass.asSubclass(User.class).getConstructor(String.class, String.class);
                            User newUser = cons.newInstance(userName, userEmail);
                            System.out.println(users.add(newUser) ? "User added." : "Failed to add user.");
                        } catch (Exception e) {
                            System.out.println("Error creating user: " + e);
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
            System.out.println(post + "\n");
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
        listMenuLoop("Select user", "Cancel", "No users found.", users, user ->
        {
            loggedInAs = user;
            System.out.println("Logged in as " + user.name + " (" + (
                    user instanceof AdminUser ? User.UserRoles.ADMIN.getName() :
                    user instanceof Moderator ? User.UserRoles.MODERATOR.getName() :
                                                User.UserRoles.REGULAR.getName()) + ")");
            user.menu();
        }, true);
    }


    private void handlePosts() {
        if (loggedInAs == null) {
            System.out.println("Not logged in.");
            return;
        }
        ArrayList<Post> postList = switch (loggedInAs) {
            case RegularUser user -> posts.stream().filter(p -> p.postedBy == user).collect(Collectors.toCollection(ArrayList::new));
            case Moderator ignored -> posts;
            case AdminUser ignored -> posts;
            default -> null;
        };

        if (postList == null) {
            System.out.println("No posts to handle found.");
        } else {
            listMenuLoop("Select post:", "Back", "No posts found.", postList, Post::menu, false);
        }
    }

}
