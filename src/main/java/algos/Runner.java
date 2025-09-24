package algos;

import util.Metrics;
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
            for (int t = 0; t < trials; t++) {
                int[] base = new int[n];
                for (int i = 0; i < n; i++) base[i] = rnd.nextInt(Math.max(1, n / 2));

                if (algo.equals("mergesort") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    Metrics m = new Metrics(); m.reset();
                    long st = System.currentTimeMillis();
                    MergeSort.sort(a, m);
                    long time = System.currentTimeMillis() - st;
                    if (!isSorted(a)) throw new RuntimeException("MergeSort failed");
                    csv.append("mergesort", n, time, m);
                    System.out.printf("mergesort n=%d time=%dms depth=%d comps=%d allocs=%d%n",
                            n, time, m.getMaxDepth(), m.getComparisons(), m.getAllocations());
                }

                if (algo.equals("quicksort") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    Metrics m = new Metrics(); m.reset();
                    long st = System.currentTimeMillis();
                    QuickSort.sort(a, m);
                    long time = System.currentTimeMillis() - st;
                    if (!isSorted(a)) throw new RuntimeException("QuickSort failed");
                    csv.append("quicksort", n, time, m);
                    System.out.printf("quicksort n=%d time=%dms depth=%d comps=%d allocs=%d%n",
                            n, time, m.getMaxDepth(), m.getComparisons(), m.getAllocations());
                }

                if (algo.equals("select") || algo.equals("all")) {
                    int[] a = Utils.copy(base);
                    Metrics m = new Metrics(); m.reset();
                    int k = a.length / 2;
                    long st = System.currentTimeMillis();
                    int val = DeterministicSelect.select(a, k, m);
                    long time = System.currentTimeMillis() - st;
                    int[] b = Utils.copy(base); Arrays.sort(b);
                    if (b[k] != val) throw new RuntimeException("Select incorrect");
                    csv.append("select", n, time, m);
                    System.out.printf("select n=%d time=%dms depth=%d comps=%d allocs=%d%n",
                            n, time, m.getMaxDepth(), m.getComparisons(), m.getAllocations());
                }

                if (algo.equals("closest") || algo.equals("all")) {
                    Point[] pts = new Point[n];
                    for (int i = 0; i < n; i++) pts[i] = new Point(rnd.nextDouble()*n, rnd.nextDouble()*n);
                    Metrics m = new Metrics(); m.reset();
                    long st = System.currentTimeMillis();
                    double d = ClosestPair.closestPairDistance(pts, m);
                    long time = System.currentTimeMillis() - st;
                    csv.append("closest", n, time, m);
                    System.out.printf("closest n=%d time=%dms d=%.6f depth=%d comps=%d allocs=%d%n",
                            n, time, d, m.getMaxDepth(), m.getComparisons(), m.getAllocations());
                }
            }

            System.out.println("Results written to " + out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i-1] > a[i]) return false;
        return true;
    }
}

