import Helpers.Menu;
import Helpers.Named;

public abstract class User implements Menu, Named {

    public enum UserRoles {
        REGULAR(User.class),
        MODERATOR(Moderator.class),
        ADMIN(User.class);

        final Class<? extends User> userClass;

        UserRoles(Class<? extends User> userClass) {
            this.userClass = userClass;
        }
    }

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
