package dev.sitconsulting.metrics.worker;

import lombok.Getter;

public class Worker {
    @Getter
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
