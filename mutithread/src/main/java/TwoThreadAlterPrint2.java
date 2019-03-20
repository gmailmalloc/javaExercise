
/**
 * Created by zhanglei59 on 2019-03-19.
 */
public class TwoThreadAlterPrint2 {

//    private final  static  Object object = new Object();

    public static void main(String[] args) {

        PrintNumber printNumber = new PrintNumber();

        Thread printThread1 = new Thread(printNumber);

        Thread printThread2 = new Thread(printNumber);

        printThread1.start();

        printThread2.start();

    }

    static class PrintNumber implements Runnable {

        private int number = 0;

        public void run() {
            while (number < 100) {

                synchronized(this) {

                    notify();

                    number++;

                    System.out.println("current thread name: "
                            + Thread.currentThread().getName() + " counter :" + number);
                    try {
                        if (number < 100) {
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
