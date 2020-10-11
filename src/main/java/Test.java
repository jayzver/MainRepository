public class Test
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!!!");
        MainThread thr1 = new MainThread("1111");
        thr1.start();
        MainThread thr2 = new MainThread("read_1");
        thr2.start();
        MainThread thr3 = new MainThread("read_2");
        thr3.start();
        MainThread thr4 = new MainThread("read_3");
        thr4.start();
        MainThread thr5 = new MainThread("write_2");
        thr5.start();
        MainThread thr6 = new MainThread("read_5");
        thr6.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thr1.thrNotify();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thr1.someNotify();
//        thr1.thrNotify();
//        thr2.thrNotify();
//        thr3.thrNotify();
//        thr4.thrNotify();
//        thr5.thrNotify();
//        thr6.thrNotify();
    }
}
