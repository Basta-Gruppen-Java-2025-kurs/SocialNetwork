public abstract class User {
    String name;
    String nickname;
    String email;
    public abstract Post postMessage(String message, String destination);
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
