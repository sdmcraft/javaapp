/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author satyam
 */
public class MultiThreadHarness {

    private Task t;

    public MultiThreadHarness(Task t) {
        this.t = t;
    }

    public void doWork(int numThreads) {
        Thread[] list = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            list[i] = new Thread(t, "Thread:" + i);
            list[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                list[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
