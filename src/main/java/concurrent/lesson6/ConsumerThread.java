package concurrent.lesson6;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ConsumerThread extends Thread{
    List<Integer> list;

    ConsumerThread(List<Integer> linkedList) {
        this.list = linkedList;
    }

    @Override
    public void run() {
        synchronized (list) {
            try {
                while (true) {
                    if (!list.isEmpty()) {
                        Integer remove = list.remove(0);
                        System.out.println("Считано число " + remove + " из List, текущий размер листа - " + list.size());
                    }
                    list.wait(TimeUnit.SECONDS.toMillis(1));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
