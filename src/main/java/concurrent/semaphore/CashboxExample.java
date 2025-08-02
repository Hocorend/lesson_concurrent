package concurrent.semaphore;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class CashboxExample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore cashbox = new Semaphore(2, true);

        List<Thread> threadStream = Stream.of(
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox),
                        new BuyerThread(cashbox)
                ).map(Thread::new)
                .peek(Thread::start)
                .toList();

        for (Thread thread : threadStream) {
            thread.join();
        }
    }

}
