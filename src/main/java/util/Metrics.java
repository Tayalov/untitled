package util;

public class Metrics {
    private static long startTime;
    private static int depth;
    private static int maxDepth;
    private static int comparisons;
    private static int allocations;

    public static void reset() {
        startTime = System.currentTimeMillis();
        depth = 0;
        maxDepth = 0;
        comparisons = 0;
        allocations = 0;
    }

    public static void enter() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public static void exit() {
        depth--;
    }

    public static void incComparisons() { comparisons++; }
    public static void incAllocations() { allocations++; }
    public static void incAllocations(int n) { allocations += n; }

    public static long getTime() { return System.currentTimeMillis() - startTime; }
    public static int getDepth() { return maxDepth; }
    public static int getComparisons() { return comparisons; }
    public static int getAllocations() { return allocations; }
}

