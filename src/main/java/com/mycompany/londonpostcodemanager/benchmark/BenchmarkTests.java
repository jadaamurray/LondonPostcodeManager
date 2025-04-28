package com.mycompany.londonpostcodemanager.benchmark;

import com.mycompany.londonpostcodemanager.avlTree.AvlTreeTest;
import com.mycompany.londonpostcodemanager.binaryTree.BstTest;
import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeapTest;

public class BenchmarkTests {

    private static final int RUNS = 1000;

    public static void main(String[] args) {
        BenchmarkTests benchmark = new BenchmarkTests();
        benchmark.runBenchmarks();
    }

    public void runBenchmarks() {
        double avlTime = benchmark(() -> AvlTreeTest.main(null), "AvlTreeTest");
        double bstTime = benchmark(() -> BstTest.main(null), "BstTest");
        double heapTime = benchmark(() -> MinimumHeapTest.main(null), "MinimumHeapTest");

        System.out.println("\n--- Benchmark Results (Average over " + RUNS + " runs) ---");
        System.out.printf("AvlTreeTest.main() average time: %.2f ns = %.9f seconds\n", avlTime, avlTime / 1_000_000_000.0);
        System.out.printf("BstTest.main() average time: %.2f ns = %.9f seconds\n", bstTime, bstTime / 1_000_000_000.0);
        System.out.printf("MinimumHeapTest.main() average time: %.2f ns = %.9f seconds\n", heapTime, heapTime / 1_000_000_000.0);
    }

    private double benchmark(Runnable method, String methodName) {
        long totalTime = 0;
        for (int i = 0; i < RUNS; i++) {
            long start = System.nanoTime();
            method.run();
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return (double) totalTime / RUNS;
    }
}
