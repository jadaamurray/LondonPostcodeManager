/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.londonpostcodemanager;
import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;

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
        System.out.println("Count: " + bst.count()); // Should be 3

        // Test Search
        System.out.println("Search NW1: " + bst.search("NW1")); // true
        System.out.println("Search N1: " + bst.search("N1")); // false

        // Test InOrder
        //String[] ordered = bst.InOrder();
        //System.out.println("InOrder: " + Arrays.toString(ordered));

        // Test Delete
        //System.out.println("Delete SW1: " + bst.Delete("SW1")); // true
        //System.out.println("Delete XX1: " + bst.Delete("XX1")); // false
        //System.out.println("Count after delete: " + bst.Count()); // Should be 2
    }
}
