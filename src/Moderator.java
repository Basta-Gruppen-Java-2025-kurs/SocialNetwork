public class Moderator extends User
{
    Moderator(String name, String email)
    {
        super(name, email);
    }

    @Override
    public void postMessage(String message, String destination) {

        System.out.println(this.name + ": " + message);
    }
}
