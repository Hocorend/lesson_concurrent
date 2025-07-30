package concurrent.lesson1;

public class SimpleThread extends Thread{

    @Override
    public void run() {
        System.out.println("Thread: " + getName());
    }
}
