package dec.sitconsulting.metrics.scheduler;

import dec.sitconsulting.metrics.stats.ItemCache;
import dec.sitconsulting.metrics.worker.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class ItemScheduler {
    private final ThreadPoolTaskExecutor executor;
    private final Random random;
    private final ItemCache itemCache;

    @Autowired
    public ItemScheduler() {
        this.executor = new ThreadPoolTaskExecutor();
        this.executor.setCorePoolSize(2);      // mindestens 2 Threads
        this.executor.setMaxPoolSize(100);       // max. 100 Threads
        this.executor.setQueueCapacity(10);    // Warteschlange für Tasks
        this.executor.setThreadNamePrefix("schedule-thread-");
        this.executor.initialize();

        this.random = new Random();

        this.itemCache = new ItemCache(0);
    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    private void handleOrder() {
        executor.execute(() -> {
            try {
                int i = random.nextInt(20) + 10;
                System.out.println("Start handling orders in Thread " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(i);
                System.out.println("Finish handling orders after " + i  + " seconds in Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    private void buildMinutely() {
        executor.execute(() -> {
            try {
                int i = new Random().nextInt(50) + 50;
                System.out.println("Start building in Thread " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(i);
                System.out.println("Finish building after " + i  + " seconds in Thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    private void checkInventory() {
        itemCache.checkCache();
    }


    @Scheduled(cron = "0 0 9 * * MON-FRI")
    private void startShift() {
        executor.execute(() -> {
            Worker w = new Worker("worker_001");
            try {
                TimeUnit.HOURS.sleep(8);
            } catch (InterruptedException e) {
                System.err.println("Arbeiter ist Krank und geht früher heim.");
            }
            w.stop();
        });
    }

    @Scheduled(initialDelay = 0)
    private void startShiftDelayed(){
        LocalTime now = LocalTime.now();
        LocalTime nineAM = LocalTime.of(9, 0);

        if (now.isAfter(nineAM)) {
            System.out.println("Es ist nach 9 Uhr. Verspäteter Schichtbeginn.");
            startShift();
        }
    }

}
