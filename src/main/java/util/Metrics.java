package util;

/**
 * Simple instance-based metrics collector.
 * Create a new Metrics() for each run and pass to algorithms.
 */
public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
    }

    public void incComparisons() { comparisons++; }
    public void incAllocations(long x) { allocations += x; }

    public void enter() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exit() {
        currentDepth--;
        if (currentDepth < 0) currentDepth = 0;
    }

    public long getComparisons() { return comparisons; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }
}
