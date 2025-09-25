import Helpers.Menu;
import Helpers.SafeInput;

import java.util.Scanner;

public class AdminUser extends User {

    public AdminUser(String name, String email) {
        super(name, email);
    }

    @Override
    public Post postMessage(String message, String destination) {
        Post newPost = new Post(message, destination);
        SocialNetwork.getInstance().posts.add(newPost);

        System.out.println("Admin " + name + " posted to " + destination + ": " + message);

        return newPost;
    }

    public void deletePost(Post post) {
        SocialNetwork.getInstance().posts.remove(post);
        System.out.println("Admin" + name + " deleted post: " + post);
    }

    @Override
    public void menu() {
        String[] options = {"Exit ❌", "Post a message", "Delete post"};
        Runnable[] actions = {
                () -> {
                    SafeInput si = new SafeInput(new Scanner(System.in));
                    String message = si.nextLine("Enter your message: ");
                    String destination = si.nextLine("Enter destination: ");
                    postMessage(message, destination);
                },
                () -> {
                    Menu.listMenuLoop(
                            "\n--- Select a post to delete \uD83D\uDDD1\uFE0F ---",
                            "Cancel ❌",
                            "No posts available",
                            SocialNetwork.getInstance().getPosts(),
                            (Post post) -> {
                                deletePost(post);
                            },
                            true
                    );
                }
        };
        String header = "\n--- " + name + "'s Admin Menu ---";
        Menu.menuLoop(header, options, actions, false);
    }

}
