public class Moderator extends User implements Reportable
{
    public Moderator(String name, String email)
    {
        super(name, email);
    }

    @Override
    public Post postMessage(String message, String destination)
    {
        System.out.println(this.name + ": " + message);
        return new Post(message, destination);
    }

    @Override
    public void menu()
    {

    }

    @Override
    public void ReportMessage()
    {

    }
}
