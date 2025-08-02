package concurrent.barrier;

import concurrent.latch.RocketDetail;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BarrierDemo {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ракета готовится к пуску");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(RocketDetail.values().length, () -> System.out.println("Пуск"));

        ExecutorService executorService = Executors.newCachedThreadPool();

        Arrays.stream(concurrent.barrier.RocketDetail.values())
                .map(detail -> new RocketDetailRunnable(cyclicBarrier, detail))
                .forEach(executorService::submit);

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
}
