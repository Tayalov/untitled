package algos;

import util.Metrics;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        CSVWriter writer = new CSVWriter("metrics.csv");

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Run predefined sizes and write to CSV");
            System.out.println("2 - Input your own array and run interactively");
            System.out.println("3 - Exit");
            System.out.print("Your choice: ");
            int mode = scanner.nextInt();

            switch (mode) {
                case 1:
                    int[] sizes = {100, 500, 1000, 5000, 10000};
                    Random rand = new Random();
                    for (int n : sizes) {
                        int[] a = rand.ints(n, 0, 100000).toArray();

                        // MergeSort
                        Metrics.reset();
                        MergeSort.sort(a.clone(), 0, a.length - 1);
                        writer.writeMetrics("MergeSort", n, Metrics.getComparisons(), Metrics.getAllocations(), Metrics.getDepth());

                        // QuickSort
                        Metrics.reset();
                        QuickSort.sort(a.clone(), 0, a.length - 1);
                        writer.writeMetrics("QuickSort", n, Metrics.getComparisons(), Metrics.getAllocations(), Metrics.getDepth());

                        // Select (median)
                        Metrics.reset();
                        DeterministicSelect.select(a.clone(), n / 2);
                        writer.writeMetrics("Select", n, Metrics.getComparisons(), Metrics.getAllocations(), Metrics.getDepth());

                        // ClosestPair
                        Metrics.reset();
                        Point[] pts = new Point[n];
                        for (int i = 0; i < n; i++) {
                            pts[i] = new Point(rand.nextInt(100000), rand.nextInt(100000));
                        }
                        ClosestPair.closestPair(pts);
                        writer.writeMetrics("ClosestPair", n, Metrics.getComparisons(), Metrics.getAllocations(), Metrics.getDepth());
                    }
                    System.out.println("Metrics written to metrics.csv");
                    break;

                case 2:
                    System.out.print("Enter array size: ");
                    int n = scanner.nextInt();
                    int[] arr = new int[n];
                    System.out.println("Enter " + n + " integers:");
                    for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();

                    System.out.println("Choose algorithm:\n1 - MergeSort\n2 - QuickSort\n3 - Select (k-th smallest)\n4 - ClosestPair (2D)");
                    int choice = scanner.nextInt();

                    Metrics.reset();
                    switch (choice) {
                        case 1:
                            MergeSort.sort(arr, 0, arr.length - 1);
                            System.out.println("Sorted array: " + Arrays.toString(arr));
                            break;
                        case 2:
                            QuickSort.sort(arr, 0, arr.length - 1);
                            System.out.println("Sorted array: " + Arrays.toString(arr));
                            break;
                        case 3:
                            System.out.print("Enter k (1-based): ");
                            int k = scanner.nextInt();
                            int kth = DeterministicSelect.select(arr, k);
                            System.out.println("k-th smallest element: " + kth);
                            break;
                        case 4:
                            Point[] points = new Point[n];
                            System.out.println("Enter " + n + " points as x y:");
                            for (int i = 0; i < n; i++) {
                                double x = scanner.nextDouble();
                                double y = scanner.nextDouble();
                                points[i] = new Point(x, y);
                            }
                            double minDist = ClosestPair.closestPair(points);
                            System.out.println("Closest distance: " + minDist);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            continue;
                    }

                    System.out.println("\n📊 Metrics:");
                    System.out.println("Comparisons: " + Metrics.getComparisons());
                    System.out.println("Allocations: " + Metrics.getAllocations());
                    System.out.println("Max recursion depth: " + Metrics.getDepth());
                    writer.writeMetrics(choiceToString(choice), n, Metrics.getComparisons(), Metrics.getAllocations(), Metrics.getDepth());
                    break;

                case 3:
                    System.out.println("Exiting...");
                    writer.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static String choiceToString(int choice) {
        return switch (choice) {
            case 1 -> "MergeSort";
            case 2 -> "QuickSort";
            case 3 -> "Select";
            case 4 -> "ClosestPair";
            default -> "Unknown";
        };
    }
}


