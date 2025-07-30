package concurrent.lesson4;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class VolatileExample {

    public static volatile boolean flag = false; // volatile(изменчивый) запрещает использовать оптимизацию(кеширование)
                                                // гарантирует только для примитивных типов


    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (!flag) {
                System.out.println("still false");
            }
        });

        thread1.start();

        TimeUnit.MILLISECONDS.sleep(5L);

        Thread thread2 = new Thread(() -> {
            flag = true;
            System.out.println("Set flag true");
        });
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
