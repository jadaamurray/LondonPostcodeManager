package com.mycompany.londonpostcodemanager;

import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;
import com.mycompany.londonpostcodemanager.terminal.TerminalMenu;
import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;
import com.mycompany.londonpostcodemanager.avlTree.AvlTree;
import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;

import java.util.Scanner;

public class LondonPostcodeManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PostcodeManagerInterface manager = null;

        System.out.println("==========================================");
        System.out.println("  London Postcode Manager - Data Structure");
        System.out.println("==========================================");

        System.out.println("Select the data structure to use:");
        System.out.println("  1 → Binary Search Tree (BST)");
        System.out.println("  2 → AVL Tree");
        System.out.println("  3 → Minimum Heap");
        System.out.print("Enter option (1/2/3): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                manager = new BinarySearchTree();
                System.out.println("Binary Search Tree selected (BST)");
            }
            case "2" -> {
                manager = new AvlTree(); //  Assign the AVL Tree implementation
                System.out.println("AVL Tree selected.");
            }
            case "3" -> {
                System.out.print("Enter max heap size (default is 20000): ");
                String input = scanner.nextLine().trim();

                int heapSize;
                try {
                    heapSize = Integer.parseInt(input);
                    if (heapSize <= 0) {
                        System.out.println("Invalid input. Using default size 20000.");
                        heapSize = 20000;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Using default size 20000.");
                    heapSize = 20000;
                }

                manager = new MinimumHeap(heapSize);
                System.out.println("Minimum Heap selected with size " + heapSize + ".");
            }

            default -> {
                System.out.println("Invalid input. Please restart and choose 1, 2, or 3.");
                return;
            }
        }

        if (manager != null) {
            new TerminalMenu(manager).start();
        } else {
            System.out.println("No data structure initialized. Exiting.");
        }
    }
}
