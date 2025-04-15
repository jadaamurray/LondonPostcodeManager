package com.mycompany.londonpostcodemanager.minimumHeap;

public class MinimumHeapTest {
    public static void main(String[] args) {
        // Create a new MinimumHeap with a max size of 10
        MinimumHeap heap = new MinimumHeap(10);

        System.out.println("=== MinimumHeap Basic API Test Program ===");

        // Test Insert()
        heap.Insert("W5 2AB");
        heap.Insert("E1 4ZY");
        heap.Insert("N1 0TT");
        System.out.println("Inserted 3 postcodes.");

        // Test Count()
        System.out.println("Total postcodes (expected 3): " + heap.Count());

        // Test Search()
        System.out.println("Search for 'E1 4ZY' (expected true): " + heap.Search("E1 4ZY"));
        System.out.println("Search for 'SW1A 1AA' (expected false): " + heap.Search("SW1A 1AA"));

        // Test ExtractMinimum()
        String min = heap.ExtractMinimum();
        System.out.println("Extracted minimum (expected 'E1 4ZY'): " + min);

        // Test InOrder()
        heap.Insert("W1A 1AA");
        heap.Insert("EC1A 1BB");
        System.out.println("Inserting 'W1A 1AA' and 'EC1A 1BB'");

        String[] sorted = heap.InOrder();
        System.out.println("Postcodes in alphabetical order (heap is now empty):");
        for (String code : sorted) {
            System.out.println(code);
        }

        // Test Extract from empty heap
        System.out.println("Extract from empty heap (expected null): " + heap.ExtractMinimum());

        // Test Insert with full heap
        System.out.println("\nTesting heap full condition:");
        MinimumHeap smallHeap = new MinimumHeap(2);
        smallHeap.Insert("W9 1AA");
        smallHeap.Insert("N1 1BB");
        try {
            smallHeap.Insert("E3 2CC");  // Should throw exception
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("=== End of Test ===");
    }
}
