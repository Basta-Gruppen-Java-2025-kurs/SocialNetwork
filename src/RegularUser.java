public class RegularUser extends User {

    public RegularUser(String name, String email) {
        super(name, email);
    }

    public Post postMessage(String message, String destination) {
        Post newPost = new Post();

        System.out.println(name + " posted to " + destination + ": " + message);

        return newPost;
    }

}
