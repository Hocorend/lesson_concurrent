package concurrent.lesson5;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public class BuyerThread implements Runnable {

    private final BlockingQueue<Cashbox> queue;

    public BuyerThread(BlockingQueue<Cashbox> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Cashbox cashbox = queue.take(); // Ожидает, если элемента нет
            System.out.println("Поток " + Thread.currentThread().getName() + " обслуживается в очереди " + cashbox);
            Thread.sleep(5L);
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил обслуживание в очереди " + cashbox);
            queue.put(cashbox);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
