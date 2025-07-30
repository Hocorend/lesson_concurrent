package concurrent.lesson5;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class BuyerThread implements Runnable {

    private final Queue<Cashbox> queue;

    public BuyerThread(Queue<Cashbox> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            synchronized (queue) {
                while (true) {
                    String thread = Thread.currentThread().toString();
                    if (!queue.isEmpty()) {
                        Cashbox cashbox = queue.remove();
                        System.out.println("Поток " + thread + " обслуживается в очереди " + cashbox);

                        queue.wait(5L);

                        System.out.println("Поток " + thread + " закончил обслуживание в очереди " + cashbox);
                        queue.add(cashbox);
                        queue.notify();
                        break;
                    } else {
                        System.out.println("Поток " + thread + " ожидает обслуживание");
                        queue.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
