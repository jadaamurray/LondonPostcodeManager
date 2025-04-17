package com.mycompany.londonpostcodemanager.minimumHeap;

public class MinimumHeapTest {
    public static void main(String[] args) {
        // Create a new MinimumHeap with a max size of 10
        MinimumHeap heap = new MinimumHeap(10);

        System.out.println("=== MinimumHeap Basic API Test Program ===");

        // Test insert()
        heap.insert("W5 2AB");
        heap.insert("E1 4ZY");
        heap.insert("N1 0TT");
        System.out.println("Inserted 3 postcodes.");

        // Test count()
        System.out.println("Total postcodes (expected 3): " + heap.count());

        // Test search()
        System.out.println("Search for 'E1 4ZY' (expected true): " + heap.search("E1 4ZY"));
        System.out.println("Search for 'SW1A 1AA' (expected false): " + heap.search("SW1A 1AA"));

        // Test delete() (extract minimum)
        String min = heap.delete();
        System.out.println("Deleted minimum (expected 'E1 4ZY'): " + min);

        // Test inOrder()
        heap.insert("W1A 1AA");
        heap.insert("EC1A 1BB");
        System.out.println("Inserted 'W1A 1AA' and 'EC1A 1BB'");

        String[] sorted = heap.inOrder();
        System.out.println("Postcodes in alphabetical order (heap is now empty):");
        for (String code : sorted) {
            System.out.println(code);
        }

        // Test delete from empty heap
        System.out.println("Delete from empty heap (expected null): " + heap.delete());

        // Test insert() with full heap
        System.out.println("\nTesting heap full condition:");
        MinimumHeap smallHeap = new MinimumHeap(2);
        smallHeap.insert("W9 1AA");
        smallHeap.insert("N1 1BB");
        try {
            smallHeap.insert("E3 2CC");  // Should throw exception
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("=== End of Test ===");
    }
}
