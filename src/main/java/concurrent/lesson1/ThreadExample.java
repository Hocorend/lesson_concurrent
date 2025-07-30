package concurrent.lesson1;

public class ThreadExample {

    public static void main(String[] args) {
        var simpleThread = new SimpleThread();
        System.out.println(simpleThread.getName() + " status is " + simpleThread.getState());

        simpleThread.start();
        System.out.println(simpleThread.getName() + " status is " + simpleThread.getState());

        var simpleRunnable = new SimpleRunnable();
        //Для вызова Runnable нужно создавать экземпляр Thread
        var threadRunnable = new Thread(simpleRunnable);
        threadRunnable.start();

        //Вариант с функциональным интерфейсом и лямбда-выражением
        var threadRunnableFI = new Thread(() -> {
            System.out.println("Thread runnable FI: " + Thread.currentThread().getName());
        });
        threadRunnableFI.start();

        try {
            simpleThread.join(); // Заставляет вызвавший поток ожидать завершения дочернего потока.
            System.out.println(simpleThread.getName() + " status is " + simpleThread.getState());

            threadRunnable.join();
            threadRunnableFI.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All thread ended");

    }
}
