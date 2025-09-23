package algos;

public class Metrics {
    public long comparisons;
    public long allocations;
    public int maxDepth;
    private int currentDepth;

    public Metrics() { reset(); }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        currentDepth = 0;
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
}

