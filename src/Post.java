import Helpers.Menu;
import Helpers.Named;

import java.util.ArrayList;

public class Post implements Likeable, Named, Menu, Reportable {
    ArrayList<User> likes = new ArrayList<>();
    ArrayList<User> reports = new ArrayList<>();
    int id;
    String content;
    String destination;
    boolean reported;
    User postedBy;
    User lastEditBy = null;
    public ArrayList<User> getLikes() {
        return likes;
    }

    public ArrayList<User> getReports() {
        return reports;
    }

    Post(String content, String destination) {
        this.content = content;
        this.destination = destination;
        this.postedBy = SocialNetwork.getInstance().getCurrentUser();
        id = this.hashCode();
    }

    @Override
    public String getName() {
        final int excerptLength = 20;
        StringBuilder name = new StringBuilder("\033[37m" + id + "\033[0m");
        name.append(" : \033[1m").append(postedBy.name).append("\033[0m posted to \033[4m").append(destination);
        name.append("\033[0m \033[34m\"");
        if (content.length() > excerptLength) {
            name.append(content, 0, excerptLength).append("...");
        } else {
            name.append(content);
        }
        name.append("\"\033[0m");
        return name.toString();
    }

    @Override
    public void menu() {
        // show, edit, delete, report, unreport, like, unlike, who liked, who reported

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\033[1m");
        sb.append(postedBy.name).append("\033[0m posted to \033[4m").append(destination).append("\033[0m:\n\033[34m");
        sb.append(content).append("\033[0m\n");
        if (lastEditBy != null) {
            sb.append("\033[37m(Last edited by \033[1m").append(lastEditBy.name).append("\033[22m)\n\033[0m");
        }
        sb.append("\033[1m\033[37m").append(likes.size()).append("\033[22m likes, \033[1m").append(reports.size()).append("\033[22m reports\033[0m");
        return sb.toString();
    }

    @Override
    public void reportMessage(User byWhom) {
        reported = true;
        if (!reports.contains(byWhom)) {
            reports.add(byWhom);
        }
    }

    @Override
    public boolean unReportMessage(User byWhom) {
        boolean result = false;
        if (reports.contains(byWhom))  {
            result = reports.remove(byWhom);
            System.out.println(result ? "Removed report by" + byWhom.name : "Failed to remove report.");
        } else if (byWhom instanceof Moderator || byWhom instanceof AdminUser) {
            reports.clear();
            result = true;
            System.out.println("All reports of this post are cleared.");
        }
        return result;
    }

    public boolean editPost(User byWhom, String newContent) {
        if (byWhom == postedBy || byWhom instanceof Moderator || byWhom instanceof AdminUser) {
            this.content = newContent;
            lastEditBy = byWhom;
            return true;
        }
        return false;
    }

    @Override
    public boolean isReported() {
        return reported;
    }
}
