package concurrent.lesson5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CashboxExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Cashbox> queue = new ArrayBlockingQueue<>(2, true, List.of(new Cashbox(), new Cashbox())); //fair - это
        // справедливая ли очередь, в каком порядке пришел, в таком и обработан

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
