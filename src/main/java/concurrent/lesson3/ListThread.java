package concurrent.lesson3;

import java.util.List;

public class ListThread extends Thread{

    List<Integer> list;

    public ListThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            list.add(i);
        }
    }
}
