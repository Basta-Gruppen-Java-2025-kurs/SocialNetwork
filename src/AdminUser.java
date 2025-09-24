public class AdminUser extends User {

    public AdminUser(String name, String email) {
        super(name, email);
    }

    @Override
    public Post postMessage(String message, String destination) {
        Post newPost = new Post();

        //need addPost method in SocialNetwork
       // SocialNetwork.getInstance().addPost(newPost);
        System.out.println("Admin " + name + " posted to " + destination + ": " + message);

        return newPost;
    }

    public void deletePost(Post post) {
        //need removePost in SocialNetwork
        //SocialNetwork.getInstance().removePost(post);

        System.out.println("Admin" + name + " deleted post: " + post);
    }

}
