package concurrent.pool;

import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        //Тред пулами являются Executors, хоть и называются они иначе

        Executors.newFixedThreadPool(5); // Фиксированное количество потоков
        Executors.newCachedThreadPool(); //Кеширует количество переданных потоков и их поддерживает, при увеличении количества
        // полученных потоков добавляет недостающие
        Executors.newSingleThreadExecutor(); //Однопоточный тред пул
        Executors.newScheduledThreadPool(10); // Для задач с задержкой или периодическим выполнением.
        Executors.newWorkStealingPool(); // Процессорный пул, смотрит свободные ресурсы потока и забирает их, но не более того,
        // что требуется для задач
    }
}
