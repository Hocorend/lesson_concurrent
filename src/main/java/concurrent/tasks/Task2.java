package concurrent.tasks;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Task2 {

    public static void main(String[] args) throws InterruptedException {
        int[] values = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(300) + 1;
        }

        trackTime(() -> findMax(values));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        trackTime(() -> {
            try {
                return findMaxParallel(values, executorService);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        /**
         * Параллельное вычисление не всегда ускоряет процесс, в данном случае профит появляется только ближе к 100М цифр.
         */
    }

    private static int trackTime(Supplier<Integer> task) {
        long startTime = System.currentTimeMillis();
        Integer i = task.get();
        long endTime = System.currentTimeMillis();
        System.out.println("Затраченное время " + ((endTime - startTime) / 1000.0));
        return i;
    }

    private static int findMax(int[] arr) {

        return IntStream.of(arr)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private static int findMaxParallel(int[] arr, ExecutorService executorService) throws ExecutionException,
            InterruptedException {
        Future<Integer> submit = executorService.submit(() -> IntStream.of(arr)
                .parallel()
                .max()
                .orElse(Integer.MIN_VALUE));
        return submit.get();
    }
}
