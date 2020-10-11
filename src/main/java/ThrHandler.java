public class ThrHandler
{
    synchronized public void  thrWait()
    {
        try
        {
            this.wait();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    synchronized public void thrNotify()
    {
        this.notify();
    }
    synchronized  public void thrNotifyAll()
    {
        this.notifyAll();
    }
}
