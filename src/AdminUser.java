public class AdminUser extends User {

    public AdminUser(String name, String email) {
        super(name, email);
    }

    @Override
    public Post postMessage(String message, String destination) {
        Post newPost = new Post();

        System.out.println("Admin " + name + " posted to " + destination + ": " + message);

        return newPost;
    }

    public void deletePost(Post post) {
        //lägg till logic för att ta bort från en lista

        System.out.println("Admin" + name + " deleted post: " + post);
    }

}
