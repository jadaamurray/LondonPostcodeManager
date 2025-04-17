package com.mycompany.londonpostcodemanager;

import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;
import com.mycompany.londonpostcodemanager.terminal.TerminalMenu;
import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;
// import com.mycompany.londonpostcodemanager.bst.BSTImplementation;
// import com.mycompany.londonpostcodemanager.avl.AVLImplementation;

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
                // manager = new BSTImplementation();
                System.out.println("BST selected. (Not yet implemented)");
            }
            case "2" -> {
                // manager = new AVLImplementation();
                System.out.println("AVL Tree selected. (Not yet implemented)");
            }
            case "3" -> {
                manager = new MinimumHeap(20000);
                System.out.println("Minimum Heap selected.");
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
