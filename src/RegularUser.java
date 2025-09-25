public class RegularUser extends User {

    public RegularUser(String name, String email) {
        super(name, email);
    }

    public Post postMessage(String message, String destination) {
        Post newPost = new Post(message, destination);
        SocialNetwork.getInstance().getPosts().add(newPost);

        System.out.println(name + " posted to " + destination + ": " + message);

        return newPost;
    }

    @Override
    public void menu() {
        String[] options = {"Exit", "Post a message"};
        Runnable[] actions = {
                () -> postMessage("Hello from RegularUser menu!", "My Wall")
        };

        String header = name + "'s Menu";
        Helpers.MenuHelper.menuLoop(header, options, actions, false);
    }

}
