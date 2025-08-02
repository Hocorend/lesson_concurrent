package concurrent.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolExample {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Check"), 0, 1, TimeUnit.SECONDS);
//        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("main end");
    }
}
