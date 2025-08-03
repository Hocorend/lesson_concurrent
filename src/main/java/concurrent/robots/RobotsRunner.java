package concurrent.robots;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Двое безумных учёных устроили соревнование, кто из них соберёт больше роботов за 100 ночей.
 * Для этого каждую ночь каждый из них отправляет своего прислужника на свалку фабрики роботов за деталями.
 * <p>
 * Чтобы собрать одного робота им нужно:
 * Голова, Тело, Левая рука, Правая рука, Левая нога, Правая нога, CPU, RAM, HDD
 * <p>
 * В первую ночь на свалке находится 20 случайных деталей.
 * Каждую ночь фабрика выбрасывает на свалку от 1 до 4 случайных деталей.
 * <p>
 * В то же самое время прислужники обоих учёных отправляются на свалку, и каждый собирает от 1 до 4 случайных деталей.
 * Если на свалке деталей нет – прислужник уходит ни с чем.
 * Затем они возвращаются домой и отдают детали хозяевам.
 * Учёные пытаются собрать как можно больше роботов из деталей, которые они получили.
 * <p>
 * Написать программу, симулирующую этот процесс.
 * Для симуляции принять, что каждая ночь наступает через 100мс.
 * <p>
 * Фабрика и два прислужника действуют в одно и то же время.
 * После 100 ночей (около 10 секунд) определить победителя соревнования
 */
public class RobotsRunner {
    private static final Random random = new Random();
    private static Map<RobotDetail, Integer> scientist1 = new HashMap<>();
    private static Map<RobotDetail, Integer> scientist2 = new HashMap<>();
    private static BlockingQueue<RobotDetail> junkyard = new ArrayBlockingQueue<>(50);

    public static void main(String[] args) throws InterruptedException {


        generateDetail(20);

        ScheduledExecutorService executorServiceJunkyard = Executors.newScheduledThreadPool(1);
        executorServiceJunkyard.scheduleAtFixedRate(() -> generateDetail(random.nextInt(4)), 0, 100,
                TimeUnit.MILLISECONDS);

        ScheduledExecutorService checkCount = Executors.newScheduledThreadPool(1);
        checkCount.scheduleAtFixedRate(() -> {
            Integer countRobotsScientist1 = scientist1.values().stream().min(Integer::compareTo).get();
            boolean scientistRobotFull1 = scientist1.keySet().size() == RobotDetail.values().length;
            Integer countRobotsScientist2 = scientist2.values().stream().min(Integer::compareTo).get();
            boolean scientistRobotFull2 = scientist1.keySet().size() == RobotDetail.values().length;
            if (scientistRobotFull1) {
                System.out.println("У первого ученого собрано роботов: " + countRobotsScientist1);
            }
            if (scientistRobotFull2) {
                System.out.println("У второго ученого собрано роботов: " + countRobotsScientist2);
            }
        }, 100, 100, TimeUnit.MILLISECONDS);

        ScheduledExecutorService building = Executors.newScheduledThreadPool(2);
        building.scheduleAtFixedRate(() -> buildRobots(scientist2), 100, 100, TimeUnit.MILLISECONDS);
        building.scheduleAtFixedRate(() -> buildRobots(scientist1), 100, 100, TimeUnit.MILLISECONDS);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        executorServiceJunkyard.awaitTermination(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        building.awaitTermination(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        checkCount.awaitTermination(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
        ).join();

        executorServiceJunkyard.shutdownNow();
        building.shutdownNow();
        checkCount.shutdownNow();

    }

    private static void generateDetail(int count) {
        for (int i = 0; i < 4; i++) {
            RobotDetail[] values = RobotDetail.values();
            RobotDetail robotDetail = values[random.nextInt(values.length)];
            junkyard.add(robotDetail);
            System.out.println("Добавлена деталь: " + robotDetail.name());
        }
    }

    private static void buildRobots(Map<RobotDetail, Integer> scientist) {
//        int count = random.nextInt(4);

        for (int j = 0; j < 2; j++) {
            RobotDetail detail;
            if (!junkyard.isEmpty()) {
                detail = junkyard.remove();
            } else break;
            putDetail(scientist, detail);

        }
    }

    private static void putDetail(Map<RobotDetail, Integer> scientist, RobotDetail detail) {
        if (scientist.containsKey(detail)) {
            Integer count = scientist.get(detail);
            scientist.put(detail, ++count);
        } else scientist.put(detail, 1);
    }
}
