package concurrent.lesson5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CashboxExample {

    public static void main(String[] args) throws InterruptedException {
        Queue<Cashbox> queue = new ArrayDeque<>(List.of(new Cashbox(), new Cashbox()));

        List<Thread> threadStream = Stream.of(
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue),
                        new BuyerThread(queue)
                ).map(Thread::new)
                .peek(Thread::start)
                .toList();

        for (Thread thread : threadStream) {
            thread.join();
        }
    }

}
