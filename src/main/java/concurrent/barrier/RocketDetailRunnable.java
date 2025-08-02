package concurrent.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class RocketDetailRunnable implements Runnable{

    private final CyclicBarrier cyclicBarrier;
    private final RocketDetail rocketDetail;

    public RocketDetailRunnable(CyclicBarrier cyclicBarrier, RocketDetail rocketDetail) {
        this.cyclicBarrier = cyclicBarrier;
        this.rocketDetail = rocketDetail;
    }

    @Override
    public void run() {
        System.out.println("Готовится деталь " + rocketDetail);
        try {
            Thread.sleep(1000);
            System.out.println("Деталь " + rocketDetail + " готова");
            cyclicBarrier.await();
            System.out.println("Деталь " + rocketDetail + " использована");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
