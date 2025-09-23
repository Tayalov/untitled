# Assignment 1: Divide and Conquer Algorithms
This project implements several classic divide-and-conquer algorithms:
- Merge Sort
- Quick Sort
- Deterministic Selection (Median of Medians)
- Closest Pair of Points

Additionally, the project collects metrics such as recursion depth, running time, and number of comparisons.

# Assignment 1: Divide and Conquer Algorithms

## 1. Project Overview

This project implements several classic divide-and-conquer algorithms. The goal is to practice safe recursion patterns, analyze their efficiency using recurrence relations (Master Theorem and Akra–Bazzi method), and validate theoretical results with experimental measurements.

The implementation is written in **Java**. The project is organized into multiple classes, each responsible for a specific algorithm or utility.

## 2. Architecture

The project contains the following classes:

* **Main.java** – entry point to run experiments.
* **MergeSort.java** – implementation of merge sort algorithm.
* **QuickSort.java** – implementation of quick sort algorithm.
* **DeterministicSelect.java** – median of medians algorithm for deterministic selection.
* **Point.java** – simple class to represent a 2D point.
* **ClosestPair.java** – divide-and-conquer solution for the closest pair of points problem.
* **RecurrenceSolver.java** – helper for analyzing recurrence relations.
* **ExperimentRunner.java** – runs benchmarks, measures runtime, recursion depth, and number of operations.
* **Utils.java** – helper functions (array generation, printing, etc.).

This modular structure ensures clarity, reusability, and separation of concerns.

## 3. Recurrence Relations

### Merge Sort

Recurrence:
T(n) = 2T(n/2) + O(n)
By Master Theorem (case 2):
T(n) = O(n log n)

### Quick Sort (average case)

Recurrence:
T(n) = 2T(n/2) + O(n)
→ O(n log n)
Worst case: T(n) = O(n²)

### Deterministic Select (Median of Medians)

Recurrence:
T(n) = T(n/5) + T(7n/10) + O(n)
By Akra–Bazzi:
T(n) = O(n)

### Closest Pair of Points

Recurrence:
T(n) = 2T(n/2) + O(n log n)
By Master Theorem:
T(n) = O(n log² n)
(optimized version achieves O(n log n)).

## 4. Experiments and Graphs

We collected the following metrics for each algorithm:

* Runtime (milliseconds).
* Recursion depth.
* Number of comparisons/allocations.

The experiments were run on randomly generated input arrays and point sets of increasing size.

**Graphs included:**

* Runtime vs Input Size.
* Recursion Depth vs Input Size.
* Comparisons vs Input Size.

## Results

Below are the plots comparing the performance of different algorithms:

![Performance Graph](results.png)


## 5. Results and Conclusions

* Merge Sort shows stable O(n log n) performance.
* Quick Sort performs well on average but degrades to O(n²) in worst-case input.
* Deterministic Select achieves linear time as predicted.
* Closest Pair of Points algorithm follows O(n log² n), with possible optimizations to O(n log n).

**Conclusion:** The theoretical recurrence relations match the experimental data. Divide-and-conquer algorithms demonstrate efficiency but are highly dependent on recursion structure and base case optimization.


