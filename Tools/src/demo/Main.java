package demo;

import java.io.File;
import java.io.IOException;
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

	public static void executorServiceDemo() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		FutureTask<String> futures[] = new FutureTask[10];
		for (int i = 0; i < 10; i++) {
			futures[i] = new FutureTask<String>(new Task());
			threadPool.execute(futures[i]);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(futures[i].isDone());
		}

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String channel = "Gtalk/connuser1@gmail.com-9ea7";

		System.out.println(channel.substring(channel.indexOf("Gtalk") + 6,
				channel.lastIndexOf(".com") + 4));
		// HashMap<String, Integer> myMap = new HashMap<String,Integer>();
		// HashMap<String, String> myMap2 = new HashMap<String,String>();
		//
		// Integer i = new Integer(5);
		// Integer j = new Integer(5);
		// myMap2.put("a1", "aa");
		// myMap2.put("a2", "aa");
		// System.out.println(myMap.get("a1")==myMap.get("a2"));
		// System.out.println(myMap2.get("a1")==myMap2.get("a2"));
	}
}
