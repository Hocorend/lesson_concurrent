package concurrent.lesson2;

public class CounterThread extends Thread {

    CounterSynchronized counterSynchronized = new CounterSynchronized();

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            counterSynchronized.increment();
            CounterSynchronized.incrementStatic();
        }
    }
}
