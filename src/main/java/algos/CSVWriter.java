package algos;

import java.io.FileWriter;
import java.io.IOException;
import util.Metrics;

public class CSVWriter implements AutoCloseable {
    private final FileWriter w;
    public CSVWriter(String path) throws IOException {
        w = new FileWriter(path);
        w.write("algo,n,timeMs,maxDepth,comparisons,allocs\n");
    }
    public void append(String algo, int n, long timeMs, Metrics m) throws IOException {
        long comps = m == null ? 0 : m.getComparisons();
        long allocs = m == null ? 0 : m.getAllocations();
        int depth = m == null ? 0 : m.getMaxDepth();
        w.write(String.format("%s,%d,%d,%d,%d,%d\n",
                algo, n, timeMs, depth, comps, allocs));
        w.flush();
    }
    @Override public void close() throws IOException { w.close(); }
}

