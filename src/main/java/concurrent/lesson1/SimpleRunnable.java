package concurrent.lesson1;

public class SimpleRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable thread: " + Thread.currentThread().getName());
    }
}
