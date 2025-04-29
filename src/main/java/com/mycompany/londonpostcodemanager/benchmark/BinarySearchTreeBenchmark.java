package com.mycompany.londonpostcodemanager.benchmark;

import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;
import java.io.FileNotFoundException;

public class BinarySearchTreeBenchmark {

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 4000, 8000, 16000};  // Test edilecek büyüklükler
        for (int size : sizes) {
            String filename = size + "_London_Postcodes.txt";
            System.out.println("Benchmarking with file: " + filename);

            try {

                List<String> postcodes = readPostcodes(filename);

                // creating BinarySearchTree

                BinarySearchTree bst = new BinarySearchTree();

                // Insert benchmark
                benchmarkInsert(postcodes, bst);

                // Using a random postcode to search benchmark
                benchmarkSearch(postcodes, bst);

                // Using a random postcode to delete benchmark

                benchmarkDelete(postcodes, bst);

                System.out.println("---------------------------");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> readPostcodes(String filename) throws Exception {
        InputStream inputStream = BinarySearchTreeBenchmark.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new FileNotFoundException("Dosya bulunamadı: " + filename);
        }
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.toList());
    }

    // Insert benchmark method
    private static void benchmarkInsert(List<String> postcodes, BinarySearchTree bst) {
        long startTime = System.nanoTime();
        for (String postcode : postcodes) {
            bst.insert(postcode);
        }
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Postcodes inserted: %d | Time taken for insert: %.2f ms\n", postcodes.size(), durationMs);
    }

    // Search benchmark method
    private static void benchmarkSearch(List<String> postcodes, BinarySearchTree bst) {
        Random random = new Random();
        String randomPostcode = postcodes.get(random.nextInt(postcodes.size()));

        long startTime = System.nanoTime();
        boolean found = bst.search(randomPostcode);
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Search for postcode '%s' | Found: %b | Time taken for search: %.2f ms\n", randomPostcode, found, durationMs);
    }

    // Delete benchmark method
    private static void benchmarkDelete(List<String> postcodes, BinarySearchTree bst) {
        Random random = new Random();
        String randomPostcode = postcodes.get(random.nextInt(postcodes.size()));

        long startTime = System.nanoTime();
        boolean deleted = bst.delete(randomPostcode);
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Delete for postcode '%s' | Deleted: %b | Time taken for delete: %.2f ms\n", randomPostcode, deleted, durationMs);
    }
}
