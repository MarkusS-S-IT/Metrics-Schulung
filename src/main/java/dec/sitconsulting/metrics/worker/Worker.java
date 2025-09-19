package dec.sitconsulting.metrics.worker;

public class Worker {
    private final String workerId;
    private final long startTimerMillis;

    public Worker(String workerId) {
        this.workerId = workerId;
        this.startTimerMillis = System.currentTimeMillis();
    }

    public long getShiftTime() {
        return System.currentTimeMillis() - startTimerMillis;
    }

    public void stop() {

    }
}
