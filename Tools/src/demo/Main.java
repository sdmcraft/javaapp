package demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

	private static class Task implements Callable {
		public String call() {
			try {
				for (int i = 0; i < 100; i++) {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + "---"
							+ i);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return "nothing";
		}
	}

	public static void executorServiceDemo()
	{
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		FutureTask<String> futures[] = new FutureTask[10];
		for(int i=0;i<10;i++)
		{
			futures[i] = new FutureTask<String>(new Task());
			threadPool.execute(futures[i]);
		}
		for(int i=0;i<10;i++)
		{
			System.out.println(futures[i].isDone());			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executorServiceDemo();
//		try {
//			String sURL = "https://www.google.com/accounts/ServiceLogin?service=mail";
//			URL url = new URL(sURL);
//			URLConnection httpConn = url.openConnection();
//			httpConn.setDoInput(true);
//			httpConn.connect();
//			InputStream in = httpConn.getInputStream();
//			BufferedInputStream bufIn = new BufferedInputStream(in);
//			int nbytes;
//			do {
//				// Echo the response on the Java Console.
//				// This is crude, and just for demo purposes.
//				byte buf[] = new byte[8192];
//				nbytes = bufIn.read(buf, 0, 8192);
//				System.out.println(new String(buf, "US-ASCII"));
//			} while (nbytes > 0);
//
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//
	}

}
