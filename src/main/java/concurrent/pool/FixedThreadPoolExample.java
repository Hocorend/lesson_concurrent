package concurrent.pool;

import java.util.concurrent.*;

public class FixedThreadPoolExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<String> future = threadPool.submit(() -> {
            System.out.println("task start");
            Thread.sleep(2000L);
            return "task complete";
        });

        threadPool.shutdown(); // Ждет выполнения всех задач
//        List<Runnable> tasks = threadPool.shutdownNow();// Завершает ThreadPool не ожидая и возвращает невыполненные задачи

        System.out.println("Result: " + future.get()); // Выполняется ожидание получения результата future, метод блокирующий, т.к. выполняется в текущем потоке
        System.out.println("main run");

        threadPool.awaitTermination(1, TimeUnit.HOURS); //Ожидание завершения не более указанного времени

        System.out.println("main end"); // ожидает завершения thread pool
    }
}
