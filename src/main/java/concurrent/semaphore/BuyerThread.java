package concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class BuyerThread implements Runnable {

    Semaphore cashbox;
    public BuyerThread(Semaphore cashbox) {
        this.cashbox = cashbox;
    }

    @Override
    public void run() {
        try {
            cashbox.acquire();
            System.out.println("Поток " + Thread.currentThread().getName() + " обслуживается в какой-то очереди");
            Thread.sleep(5L);
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил обслуживание в очереди");
            cashbox.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
