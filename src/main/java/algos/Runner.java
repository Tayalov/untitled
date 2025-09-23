package algos;

import java.util.Random;
import java.util.Arrays;
public class Runner {
    public static void main(String[] args) {
        String algo = "all";
        int n = 5000;
        int trials = 3;
        String out = "results.csv";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo": algo = args[++i]; break;
                case "--size": n = Integer.parseInt(args[++i]); break;
                case "--trials": trials = Integer.parseInt(args[++i]); break;
                case "--out": out = args[++i]; break;
            }
        }

        Random rnd = new Random(42);

        try (CSVWriter csv = new CSVWriter(out)) {
            Metrics m = new Metrics();

            for (int t = 0; t < trials; t++) {
                // generate array with duplicates
                int[] base = new int[n];
                for (int i = 0; i < n; i++) base[i] = rnd.nextInt(Math.max(1, n / 2));

                if (algo.equals("mergesort") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    m.reset(); long st = System.currentTimeMillis();
                    MergeSort.sort(a, m);
                    long time = System.currentTimeMillis() - st;
                    if (!Utils.isSorted(a)) throw new RuntimeException("MergeSort failed");
                    csv.append("mergesort", n, time, m);
                    System.out.printf("mergesort n=%d time=%.3fms depth=%d comps=%d allocs=%d%n",
                            n, time/1.0, m.maxDepth, m.comparisons, m.allocations);
                }

                if (algo.equals("quicksort") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    m.reset(); long st = System.currentTimeMillis();
                    QuickSort.sort(a, m);
                    long time = System.currentTimeMillis() - st;
                    if (!Utils.isSorted(a)) throw new RuntimeException("QuickSort failed");
                    csv.append("quicksort", n, time, m);
                    System.out.printf("quicksort n=%d time=%.3fms depth=%d comps=%d allocs=%d%n",
                            n, time/1.0, m.maxDepth, m.comparisons, m.allocations);
                }

                if (algo.equals("select") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    m.reset(); long st = System.currentTimeMillis();
                    int k = a.length / 2;
                    int val = DeterministicSelect.select(a, k, m);
                    long time = System.currentTimeMillis() - st;
                    int[] b = Utils.copy(base); Arrays.sort(b);
                    if (b[k] != val) throw new RuntimeException("Select incorrect");
                    csv.append("select", n, time, m);
                    System.out.printf("select n=%d time=%.3fms depth=%d comps=%d allocs=%d%n",
                            n, time/1.0, m.maxDepth, m.comparisons, m.allocations);
                }

                if (algo.equals("closest") || algo.equals("all")) {
                    Point[] pts = new Point[n];
                    for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble()*n, rnd.nextDouble()*n);
                    m.reset(); long st = System.currentTimeMillis();
                    double d = ClosestPair.closestPairDistance(pts, m);
                    long time = System.currentTimeMillis() - st;
                    // correctness: for small n one could compare with brute force; here just output
                    csv.append("closest", n, time, m);
                    System.out.printf("closest n=%d time=%.3fms d=%.6f depth=%d%n",
                            n, time/1.0, d, m.maxDepth);
                }
            }

            System.out.println("Results written to " + out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
