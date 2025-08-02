package concurrent.pool;

import java.util.Optional;
import java.util.Queue;

/**
 * Пример  тред пула, который отрабатывает таски поочередно при их наличии в бесконечном потоке
 */
public class ThreadPool extends Thread {
    private final Queue<Runnable> tasks;

    public ThreadPool(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            Optional<Runnable> task = Optional.empty();
            synchronized (task){
                if (!tasks.isEmpty()) {
                    task = Optional.of(tasks.remove());
                }
            }
            task.ifPresent(Runnable::run);
        }
    }
}
