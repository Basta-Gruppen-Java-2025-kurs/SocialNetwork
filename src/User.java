public abstract class User {
    String name;
    String email;
    public abstract void postMessage(String message, String destination);
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
