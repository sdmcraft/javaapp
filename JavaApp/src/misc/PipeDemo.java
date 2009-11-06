package misc;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeDemo {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		PipedInputStream pin = new PipedInputStream();
		PipedOutputStream pout = new PipedOutputStream();
		pin.connect(pout);
		new Thread(new Producer(pout)).start();
		new Thread(new Consumer(pin)).start();
	}

}

class Producer implements Runnable {
	PipedOutputStream out;

	public Producer(PipedOutputStream out) {
		this.out = out;
	}

	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				Thread.sleep(1000);
				out.write(i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	PipedInputStream in;

	public Consumer(PipedInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			int b;
			while ((b = in.read()) != -1)
				System.out.println(b);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
