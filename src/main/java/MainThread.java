public class MainThread extends Thread
{
    private ThrHandler mainThrHandler = new ThrHandler();
    private static ThrHandler writeThrHandler;
    private static ThrHandler readThrHandler;
    //    private static Object synchrWriting;
//    private static Object synchrReading;
    private static boolean isWriting = false;
    private static boolean isReading = false;
    private static ThrHandler some;
    private String code;

    static
    {
        writeThrHandler = new ThrHandler();
        readThrHandler = new ThrHandler();
        some = new ThrHandler();
//        synchrWriting = new Object();
//        synchrReading = new Object();
    }

    public MainThread(String code)
    {
        this.code = code;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                synchronized (mainThrHandler)
                {
                    mainThrHandler.thrWait();
                }
                if (this.code.contentEquals("write"))
                {
                    synchronized (writeThrHandler)
                    {
                        if (isReading)
                        {
                            writeThrHandler.thrWait();
                        }
                        isWriting = true;
                        System.out.println("start " + this.code);
                        Thread.sleep(1000);
                        System.out.println("end " + this.code);
                        synchronized (writeThrHandler)
                        {
                            isWriting = false;
                        }
                        readThrHandler.thrNotify();
                    }
                } else if (this.code.contentEquals("read"))
                {
                    if (isWriting)
                    {
                        synchronized (readThrHandler)
                        {
                            readThrHandler.thrWait();
                            this.isReading = true;
                        }
                    }
                    System.out.println("start " + this.code);
                    Thread.sleep(2000);
                    System.out.println("end " + this.code);
                    synchronized (readThrHandler)
                    {
                        isReading = false;
                    }
                } else
                {
                    System.out.println("some start");
                    some.thrWait();
                    System.out.println("some");
                }
            }
        }
        catch (InterruptedException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    synchronized public void thrNotifyAll()
    {
        mainThrHandler.thrNotifyAll();
    }

    synchronized public void thrNotify()
    {
        mainThrHandler.thrNotify();
    }
    synchronized public void someNotify()
    {
        some.thrNotifyAll();
    }
}
