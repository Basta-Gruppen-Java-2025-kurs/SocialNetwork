public interface Reportable
{
    abstract void reportMessage(User byWhom);
    abstract boolean unReportMessage(User byWhom);
    abstract boolean isReported();
}