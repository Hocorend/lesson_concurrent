package concurrent.lesson2;

public class CounterSynchronized {

    int counter;

    static int staticCounter;

    public int getCounter() {
        return counter;
    }

    public synchronized void increment(){ // Синхронизация на уровне метода
        counter++;
    }

    public void decrement(){
        synchronized (this){  // Синхронизация на уровне экземпляра класса
            counter--;
        }
    }

    public static synchronized void incrementStatic(){ // Синхронизация на уровне метода
        staticCounter++;
    }

    public static void decrementStatic(){
        synchronized (CounterSynchronized.class) { // Синхронизация на уровне класса
            staticCounter--;
        }
    }
}
