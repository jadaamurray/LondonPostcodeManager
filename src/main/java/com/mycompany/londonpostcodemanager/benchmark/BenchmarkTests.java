package com.mycompany.londonpostcodemanager.benchmark;

import com.mycompany.londonpostcodemanager.PostcodeFileLoader;
import com.mycompany.londonpostcodemanager.avlTree.AvlTree;
import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;
import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;
import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;

public class BenchmarkTests {

    private static final String[] FILES = {
            "1000_London_Postcodes.txt",
            "2000_London_Postcodes.txt",
            "4000_London_Postcodes.txt",
            "8000_London_Postcodes.txt",
            "16000_London_Postcodes.txt"
    };

    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("             Benchmarking Postcode Manager         ");
        System.out.println("===================================================");

        for (String file : FILES) {
            System.out.println("\n Benchmarking with file: " + file);
            benchmarkStructure(new BinarySearchTree(), "BST", file);
            benchmarkStructure(new AvlTree(), "AVL", file);
            benchmarkStructure(new MinimumHeap(20000), "MinHeap", file);
        }
    }

    private static void benchmarkStructure(PostcodeManagerInterface manager, String type, String file) {
        System.out.printf("\n[%s] Testing with file: %s\n", type, file);

        long startInsert = System.nanoTime();
        PostcodeFileLoader.loadPostcodesFromResource(file, manager);
        long endInsert = System.nanoTime();
        double insertTimeMs = (endInsert - startInsert) / 1_000_000.0;

        long startSearch = System.nanoTime();
        boolean found = manager.search("E1 6AN"); // Random example postcode
        long endSearch = System.nanoTime();
        double searchTimeMs = (endSearch - startSearch) / 1_000_000.0;

        System.out.printf("→ Insert Time: %.2f ms\n", insertTimeMs);
        System.out.printf("→ Search Time (1 element): %.5f ms (found: %s)\n", searchTimeMs, found);
    }
}


