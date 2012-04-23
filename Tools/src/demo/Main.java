package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
		BufferedReader in = null;
		for (int i = 20; i < 25; i++) {
			URL url = new URL(
					"http://satyam7-win7.corp.adobe.com/api/xml?action=event-register&sco-id=11159&password=1234&password-verify=1234&login=user" + i + "@adobe.com&name=newuser" + i);
			URLConnection yc = url.openConnection();
			in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
		}
		in.close();
	}
}
