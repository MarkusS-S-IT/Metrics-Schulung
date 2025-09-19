package dec.sitconsulting.metrics.stats;

public class ItemCache {
    private long checks;

    public ItemCache(long startCheckCount) {
        this.checks = startCheckCount;
    }

    public long getCheckCount() {
        return checks;
    }

    public void checkCache() {
        ++checks;
    }
}
