package demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import dataStructuresV2.Graph;
import dataStructuresV2.utils.GraphFactory;

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
		String[] values = { "a", "b", "c", "d" };
		Set<String> valueSet = new HashSet<String>(Arrays.asList(values));

		Graph graph = GraphFactory.getGraph(valueSet, 4);
		System.out.println(graph.getDiagram());
	}
}
