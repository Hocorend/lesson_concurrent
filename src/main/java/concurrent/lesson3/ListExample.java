package concurrent.lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListExample {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>()); // Костыльный способ синхронизации
        // непотокобезопасных коллекций. лучше делать рефакторинг на подокобезопасные коллекции

        ListThread thread1 = new ListThread(list);
        ListThread thread2 = new ListThread(list);
        ListThread thread3 = new ListThread(list);
        ListThread thread4 = new ListThread(list);
        ListThread thread5 = new ListThread(list);
        ListThread thread6 = new ListThread(list);
        ListThread thread7 = new ListThread(list);
        ListThread thread8 = new ListThread(list);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();

        System.out.println(list);
    }
}
