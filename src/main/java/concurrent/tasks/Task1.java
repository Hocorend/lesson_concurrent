package concurrent.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        Scanner scanner = new Scanner(System.in);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<String, Integer> timeSleep = new HashMap<>();
        while (true) {
            int i = scanner.nextInt();
            if (i < 0) {
                break;
            }
            executorService.submit(() -> {
                String threadName = Thread.currentThread().getName();
                Thread.sleep(i * 1000L);
                System.out.println(String.format("Поток %s уснул на %d секунд", threadName, i));
                if (timeSleep.containsKey(threadName)) {
                    timeSleep.put(threadName, timeSleep.get(threadName) + i);
                } else timeSleep.put(threadName, i);

                Integer counterTime = threadLocal.get();
                threadLocal.set(counterTime == null? 1: ++counterTime);
                System.out.println(String.format("Поток %s обработал %d задачу", threadName, threadLocal.get()));
                return i;
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        timeSleep.entrySet()
                .forEach(entry -> {
                    System.out.println(String.format("Поток %s проспал %d секунд", entry.getKey(), entry.getValue()));
                });
    }
}
