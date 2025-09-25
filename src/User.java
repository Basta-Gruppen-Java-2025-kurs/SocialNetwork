import Helpers.Menu;
import Helpers.Named;

public abstract class User implements Menu, Named {

    public enum UserRoles implements Named {
        REGULAR(RegularUser.class),
        MODERATOR(Moderator.class),
        ADMIN(AdminUser.class);

        final Class<? extends User> userClass;

        UserRoles(Class<? extends User> userClass) {
            this.userClass = userClass;
        }

        @Override
        public String getName() {
            return this.toString();
        }
    }

    String name;
    String nickname;
    String email;
    public abstract Post postMessage(String message, String destination);
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }
}
