import Helpers.Menu;
import Helpers.Named;

public abstract class User implements Menu, Named {
    String name;
    String nickname;
    String email;
    public abstract Post postMessage(String message, String destination);
    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }
}
