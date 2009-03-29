package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JConsoleDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		while (true) {
			System.out.println("1. Heavy memory usage ");
			System.out.println("2. Heavy CPU usage ");
			System.out.println("3. Heavy thread usage ");
			System.out.println("4. Deadlock ");
			System.out.println("5. Exit ");
			System.out.println("Enter your choice: ");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String choice = null;
			choice = br.readLine();
			int ch = Integer.parseInt(choice);
			switch (ch) {
			case 1:
				hogMemory();
				break;
			case 2:
				hogCPU();
				break;
			case 3:
				hogThreads();
				break;
			case 4:
				deadlock(new Object(),new Object());
				break;												
			case 5:
				System.exit(0);
			}
		}

	}

	public static void hogMemory() {
		Object[] uselessArray = new Object[1000000];
		for (int i = 0; i < uselessArray.length; i++)
			uselessArray[i] = new Object();
	}
	
	public static void hogCPU() {		
		for (int i = 0; i < 10000000; i++)
		{
			double d = Math.random() * Math.random();
		}
	}

	public static void hogThreads() {		
		for (int i = 0; i < 100; i++)
		{
			Thread t = new Thread()
			{
				@Override
				public void run()
				{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
	}

	public static void deadlock(final Object lock1, final Object lock2) throws InterruptedException
	{
		Thread t1 = new Thread("Thread1")
		{
			@Override
			public void run()
			{
				synchronized (lock1) {
					try {
						System.out.println(Thread.currentThread().getName() + "got lock1");
						Thread.sleep(1000);
						synchronized (lock2) {
							System.out.println(Thread.currentThread().getName() + "got lock2");	
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t2 = new Thread("Thread2")
		{
			@Override
			public void run()
			{
				synchronized (lock2) {
					try {
						System.out.println(Thread.currentThread().getName() + "got lock2");
						Thread.sleep(1000);
						synchronized (lock1) {
							System.out.println(Thread.currentThread().getName() + "got lock1");	
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
