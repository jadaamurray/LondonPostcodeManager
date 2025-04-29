package com.mycompany.londonpostcodemanager.benchmark;

import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MinimumHeapBenchmark {

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 4000, 8000, 16000};  // Test edilecek postkod sayıları

        // Her boyut için benchmark işlemi yapılır
        for (int size : sizes) {
            String filename = size + "_London_Postcodes.txt";
            System.out.println("Benchmarking with file: " + filename);

            try {
                // Dosyadan postkodları okuma işlemi
                List<String> postcodes = readPostcodes(filename);

                // MinimumHeap nesnesi oluşturuluyor
                MinimumHeap heap = new MinimumHeap(size);

                // Insert işlemi için benchmark
                benchmarkInsert(postcodes, heap);

                // Rastgele bir postkodu arama işlemi için benchmark
                benchmarkSearch(postcodes, heap);

                // Rastgele bir postkodu silme işlemi için benchmark
                benchmarkDelete(heap);

                System.out.println("---------------------------");

            } catch (FileNotFoundException e) {
                System.err.println("Dosya bulunamadı: " + filename);
            } catch (IOException e) {
                System.err.println("Bir hata oluştu: " + e.getMessage());
            }
        }
    }

    // Dosyadan postkodları okuma metodu
    private static List<String> readPostcodes(String filename) throws IOException {
        InputStream inputStream = MinimumHeapBenchmark.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null) {
            throw new FileNotFoundException("Dosya bulunamadı: " + filename);
        }
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.toList());
    }

    // Insert işlemi için benchmark metodu
    private static void benchmarkInsert(List<String> postcodes, MinimumHeap heap) {
        long startTime = System.nanoTime();
        for (String postcode : postcodes) {
            heap.insert(postcode);
        }
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Postcodes inserted: %d | Time taken for insert: %.2f ms\n", postcodes.size(), durationMs);
    }

    // Search işlemi için benchmark metodu
    private static void benchmarkSearch(List<String> postcodes, MinimumHeap heap) {
        Random random = new Random();
        String randomPostcode = postcodes.get(random.nextInt(postcodes.size()));

        long startTime = System.nanoTime();
        boolean found = heap.search(randomPostcode);
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Search for postcode '%s' | Found: %b | Time taken for search: %.2f ms\n", randomPostcode, found, durationMs);
    }

    // Delete işlemi için benchmark metodu
    private static void benchmarkDelete(MinimumHeap heap) {
        long startTime = System.nanoTime();
        String deletedPostcode = heap.delete();
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        if (deletedPostcode != null) {
            System.out.printf("Delete for postcode '%s' | Time taken for delete: %.2f ms\n", deletedPostcode, durationMs);
        } else {
            System.out.println("No postcode to delete.");
        }
    }
}
