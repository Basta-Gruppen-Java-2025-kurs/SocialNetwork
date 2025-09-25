import Helpers.Menu;
import Helpers.Named;

public abstract class User implements Menu, Named {

    public enum UserRoles implements Named {
        REGULAR(RegularUser.class, "Regular user"),
        MODERATOR(Moderator.class, "Moderator"),
        ADMIN(AdminUser.class, "Administrator");

        final Class<? extends User> userClass;
        private final String className;

        UserRoles(Class<? extends User> userClass, String className) {
            this.userClass = userClass;
            this.className = className;
        }

        @Override
        public String getName() {
            return this.className;
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
