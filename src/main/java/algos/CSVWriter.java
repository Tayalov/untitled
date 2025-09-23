package algos;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter implements AutoCloseable {
    private final FileWriter w;
    public CSVWriter(String path) throws IOException {
        w = new FileWriter(path);
        w.write("algo,n,timeMs,maxDepth,comparisons,allocs\n");
    }
    public void append(String algo, int n, long timeMs, Metrics m) throws IOException {
        w.write(String.format("%s,%d,%d,%d,%d,%d\n",
                algo, n, timeMs, m.maxDepth, m.comparisons, m.allocations));
        w.flush();
    }
    @Override public void close() throws IOException { w.close(); }
}

