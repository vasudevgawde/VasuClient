package practice;

public class TestThread {
public static void main(String args[])
{
	Thread t = new myThread()
	{
		public void run()
		{
			System.out.print("foo");
		}
	};
	t.start();
}
}

class myThread extends Thread
{
	myThread()
	{
		System.out.print("My Thread");
	}
	public void run()
	{
		System.out.print("bar");
	}
	public void run(String s)
	{
		System.out.print("baz");
	}
}