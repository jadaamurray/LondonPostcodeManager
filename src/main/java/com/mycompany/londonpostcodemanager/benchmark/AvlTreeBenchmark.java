package com.mycompany.londonpostcodemanager.benchmark;

import com.mycompany.londonpostcodemanager.avlTree.AvlTree;
import com.mycompany.londonpostcodemanager.shared.DeletablePostcodeManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AvlTreeBenchmark {

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 4000, 8000, 16000};  // Test edilecek büyüklükler
        for (int size : sizes) {
            String filename = size + "_London_Postcodes.txt";
            System.out.println("Benchmarking with file: " + filename);

            try {
                List<String> postcodes = readPostcodes(filename);
                if (postcodes.isEmpty()) {
                    System.out.println("Dosya boş veya okunamadı.");
                    continue;
                }

                DeletablePostcodeManager tree = new AvlTree();

                benchmarkInsert(postcodes, tree);
                benchmarkSearch(postcodes, tree);
                benchmarkDelete(postcodes, tree);

                System.out.println("---------------------------");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> readPostcodes(String filename) throws Exception {
        InputStream inputStream = AvlTreeBenchmark.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new IllegalArgumentException("Dosya bulunamadı: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    private static void benchmarkInsert(List<String> postcodes, DeletablePostcodeManager tree) {
        long start = System.nanoTime();
        for (String postcode : postcodes) {
            tree.insert(postcode);
        }
        long end = System.nanoTime();
        System.out.println("Insert Time: " + (end - start) + " ns");
    }

    private static void benchmarkSearch(List<String> postcodes, DeletablePostcodeManager tree) {
        String searchTarget = getRandomElement(postcodes);
        long start = System.nanoTime();
        boolean found = tree.search(searchTarget);
        long end = System.nanoTime();
        System.out.println("Search '" + searchTarget + "': " + found + " in " + (end - start) + " ns");
    }

    private static void benchmarkDelete(List<String> postcodes, DeletablePostcodeManager tree) {
        String deleteTarget = getRandomElement(postcodes);
        long start = System.nanoTime();
        boolean deleted = tree.delete(deleteTarget);
        long end = System.nanoTime();
        System.out.println("Delete '" + deleteTarget + "': " + deleted + " in " + (end - start) + " ns");
    }

    private static String getRandomElement(List<String> list) {
        Collections.shuffle(list);
        return list.get(0);
    }
}
