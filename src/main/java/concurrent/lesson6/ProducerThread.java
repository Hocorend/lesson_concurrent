package concurrent.lesson6;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerThread extends Thread {

    List<Integer> list;
    Random random = new Random();

    ProducerThread(List<Integer> linkedList) {
        this.list = linkedList;
    }

    @Override
    public void run() {
        synchronized (list) {
            try {
                while (true) {
                    if (list.size() < 10) {
                        list.add(random.nextInt(10));
                        System.out.println("Добавлено число в List, текущий размер листа - " + list.size());
                    }
                    list.wait(TimeUnit.MILLISECONDS.toMillis(500));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
