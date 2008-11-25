/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author satyam
 */
public class Task implements Runnable {

    Integer race = 0;
    /* safeRace is a thread local member. It is a wrapper over race. If race is
     * set or get using this wrapper, the changes will be visible in the thread 
     * only. Basically safeRace makes sure each thread has its own working copy 
     * of race
     */
    ThreadLocal<Integer> safeRace = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return race;
        }
    };

    public void run() {
        race++; 
        /* Changes happening to race outside this thread are not visible here if
         * safe race is used
         */
        while (true) {
            System.out.println("I am " + Thread.currentThread().getName() + " For me race=" + race + " & safeRace=" + safeRace.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
