package com.mycompany.londonpostcodemanager.avlTree;

public class AvlTreeTest {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        // Insert postcodes
        tree.insert("W1A 1AA");
        tree.insert("EC1A 1BB");
        tree.insert("N1 9GU");
        tree.insert("SW1A 1AA");
        tree.insert("E1 6AN");

        // Try inserting a duplicate
        tree.insert("W1A 1AA"); // should not be added again

        // Search
        System.out.println("Search 'SW1A 1AA': " + tree.search("SW1A 1AA")); // true
        System.out.println("Search 'XYZ 123': " + tree.search("XYZ 123"));   // false

        // Count
        System.out.println("Total postcodes: " + tree.count()); // should be 5 (no duplicate)

        // In-order traversal
        String[] sortedPostcodes = tree.inOrder();
        System.out.println("In-order postcodes:");
        for (String postcode : sortedPostcodes) {
            System.out.println(postcode);
        }

        // Delete a postcode
        tree.delete("EC1A 1BB");
        System.out.println("After deletion:");
        for (String postcode : tree.inOrder()) {
            System.out.println(postcode);
        }

        // Final count
        System.out.println("Final postcode count: " + tree.count());
    }
}
