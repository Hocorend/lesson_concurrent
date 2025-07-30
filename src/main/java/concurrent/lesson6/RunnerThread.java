package concurrent.lesson6;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunnerThread {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new LinkedList<>();

        List<Thread> collect = Stream.of(
                        new ProducerThread(list),
                        new ConsumerThread(list)
                ).map(Thread::new)
                .peek(Thread::start)
                .toList();

        for (Thread thread : collect) {
            thread.join();
        }
    }
}
