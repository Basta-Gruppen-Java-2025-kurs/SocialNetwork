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
        return "" + id;
    }

    @Override
    public void menu() {
        // show, edit, delete, report, unreport, like, unlike, who liked, who reported

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(postedBy.name).append(" posted to ").append(destination).append(":\n");
        sb.append(content).append("\n");
        if (lastEditBy != null) {
            sb.append("(Last edited by ").append(lastEditBy.name).append(")\n");
        }
        sb.append(likes.size()).append(" likes, ").append(reports.size()).append(" reports");
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
