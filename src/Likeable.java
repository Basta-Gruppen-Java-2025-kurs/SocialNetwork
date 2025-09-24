import java.util.ArrayList;

public interface Likeable {
    default boolean like(User likedBy) {
        return getLikes().add(likedBy);
    }
    default boolean unlike(User unlikedBy) {
        return getLikes().remove(unlikedBy);
    }
    default int likesCount() {
        return getLikes().size();
    }
    ArrayList<User> getLikes();
}
