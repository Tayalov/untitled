package algos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
    private final PrintWriter pw;

    public CSVWriter(String filename) throws IOException {
        pw = new PrintWriter(new FileWriter(filename, true));
        pw.println("Algorithm,Size,Comparisons,Allocations,MaxDepth");
    }

    public void writeMetrics(String algo, int size, int comps, int allocs, int depth) {
        pw.println(algo + "," + size + "," + comps + "," + allocs + "," + depth);
        pw.flush();
    }

    public void printCSV() {
        System.out.println("See " + pw.toString());
    }

    public void close() {
        pw.close();
    }
}


