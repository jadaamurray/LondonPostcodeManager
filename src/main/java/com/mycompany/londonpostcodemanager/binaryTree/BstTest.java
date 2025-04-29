package com.mycompany.londonpostcodemanager.binaryTree;

import java.util.Arrays;

public class BstTest {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        System.out.println("=== Binary Search Tree Basic API Test Program ===");

        // Test Insert and Count
        System.out.println("Adding postcode NW1");
        bst.insert("NW1");
        System.out.println("Adding postcode SW1");
        bst.insert("SW1");
        System.out.println("Adding postcode E1");
        bst.insert("E1");
        System.out.println("Adding postcode SE15");
        bst.insert("SE15");
        System.out.println("Adding postcode N5");
        bst.insert("N5");
        System.out.println("Adding postcode E14");
        bst.insert("E14");
        System.out.println("Adding postcode WC1");
        bst.insert("WC1");


        System.out.println("Count (expected 7): " + bst.count());

        // Test Search
        System.out.println("Search NW1 (expected true): " + bst.search("NW1")); // true
        System.out.println("Search N1 (expected false): " + bst.search("N1")); // false

        // Test InOrder
        String[] ordered = bst.inOrder();
        System.out.println("InOrder: " + Arrays.toString(ordered));

        // Test Delete
        System.out.println("Delete SW1 (expected true): " + bst.delete("SW1"));
        System.out.println("Delete E1 (expected true): " + bst.delete("E1"));
        System.out.println("Delete N5 (expected true): " + bst.delete("N5"));
        System.out.println("Delete XX1 (expected false): " + bst.delete("XX1"));
        System.out.println("Count after delete (expected 4): " + bst.count());

        // Test InOrder after deletes
        String[] deletedOrdered = bst.inOrder();
        System.out.println("InOrder after delete: " + Arrays.toString(deletedOrdered));

        System.out.println("=== End of Test ===");
    }
}
