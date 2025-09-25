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
        String[] options = {"Exit", "Post a message", "Delete post"};
        Runnable[] actions = {
                () -> postMessage("Hello from AdminUser menu!", "My Wall"),
                () -> {
                    Helpers.MenuHelper.listMenuLoop(
                            "Select a post to delete:",
                            "Cancel",
                            "No posts available",
                            SocialNetwork.getInstance().getPosts(),
                            (Post post) -> {
                                deletePost(post);
                            },
                            true
                    );
                }
        };
        String header = name + "'s Menu";
        Helpers.MenuHelper.menuLoop(header, options, actions, false);
    }

}
