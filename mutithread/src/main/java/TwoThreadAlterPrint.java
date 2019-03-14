/**
 * Created by zhanglei59 on 2019-03-14.
 */
public class TwoThreadAlterPrint {
    static int counter = 0;

    public static Object object = new Object();

    public static void main(String[] args) {
        CardinalThread cardinalThread = new CardinalThread();

        new Thread(cardinalThread).start();

        EvenThread evenThread = new EvenThread();

        new Thread(evenThread).start();

    }

    public static class CardinalThread implements Runnable {

        public void run() {

            while (counter < 100) {
                synchronized(object) {
                    if (counter % 2 == 0) {

                        counter++;
                        System.out.println("current: " + counter + " from CardinalThread");

                        object.notify();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }
    }

    public static class EvenThread implements Runnable {

        public void run() {
            while (counter <= 100) {

                synchronized(object) {

                    if (counter % 2 == 1) {

                        counter++;
                        System.out.println("current: " + counter + " from EvenThread");

                        object.notify();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

}
