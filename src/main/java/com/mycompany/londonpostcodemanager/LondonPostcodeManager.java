/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.londonpostcodemanager;
import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;

import java.util.Arrays;

/**
 *
 * @author jadamurray
 */
public class LondonPostcodeManager {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Test Insert and Count
        bst.insert("NW1");
        bst.insert("SW1");
        bst.insert("E1");
        bst.insert("UB6");
        bst.insert("IG10");
        bst.insert("E14");
        bst.insert("IG14");


        System.out.println("Count: " + bst.count()); // Should be 3

        // Test Search
        System.out.println("Search NW1: " + bst.search("NW1")); // true
        System.out.println("Search N1: " + bst.search("N1")); // false

        // Test InOrder
        String[] ordered = bst.inOrder();
        System.out.println("InOrder: " + Arrays.toString(ordered));

        // Test Delete
        System.out.println("Delete SW1: " + bst.delete("SW1")); // true
        System.out.println("Delete UB6: " + bst.delete("UB6")); // true
        System.out.println("Delete IG10: " + bst.delete("IG10")); // true
        System.out.println("Delete XX1: " + bst.delete("XX1")); // false
        System.out.println("Count after delete: " + bst.count()); // Should be 2

        // Test InOrder after deletes
        String[] deltedOrdered = bst.inOrder();
        System.out.println("InOrder after delete: " + Arrays.toString(ordered));
    }
}
