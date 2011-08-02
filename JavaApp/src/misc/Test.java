/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

/**
 * 
 * @author satyam
 */
public class Test {
	public static void main(String args[]) {

		Counter time = new Counter();
		TimerThread tt = new TimerThread(time);
		MsgPrinterThread mp = new MsgPrinterThread(time);
		mp.start();

		tt.start();

	}

}

/******* Message printer Thread ************/

class MsgPrinterThread extends Thread {

	Counter time;

	public MsgPrinterThread(Counter t) {

		time = t;

	}

	public void run() {

		synchronized (time) {
			while (true) {

				System.out.println("Time:" + time.elapsed);
				try {
					if (time.elapsed > 5) {
						System.out.println("The same message again and again");
					} else {
						System.out.println(time.elapsed);
						time.notifyAll();
						time.wait();

					}
				} catch (InterruptedException e) {
					return;
				}
			}

		}
	}

}

/********** Timer thread *************/

class TimerThread extends Thread {

	Counter time;

	public TimerThread(Counter t) {

		time = t;

	}

	public synchronized void run() {

		synchronized (time) {
			while (true) {

				try {
					Thread.sleep(1000);
					time.elapsed++;
					System.out.println("Current time is:" + time.elapsed);
					time.notifyAll();
					time.wait();
				} catch (InterruptedException e) {
					return;
				}

			}
		}

	}

}

/******* This is the time counter *************/
class Counter {

	int elapsed = 0;

}
