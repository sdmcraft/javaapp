package misc;

public class ThreadNotifyDemo {

	public static Object lock = new Object();

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new Waiter());
		Thread t2 = new Thread(new Notifier());
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}

class Waiter implements Runnable {

	@Override
	public void run() {

		synchronized (ThreadNotifyDemo.lock) {

			System.out.println("Going for wait on lock");
			try {
				ThreadNotifyDemo.lock.wait();
				System.out.println("Wait over on lock");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Notifier implements Runnable {

	@Override
	public void run() {
		synchronized (ThreadNotifyDemo.lock) {
			System.out.println("Notifying all on lock");
			ThreadNotifyDemo.lock.notifyAll();
		}
	}

}