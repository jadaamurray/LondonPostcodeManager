package com.mycompany.londonpostcodemanager;

import com.mycompany.londonpostcodemanager.avlTree.AvlTree;
import com.mycompany.londonpostcodemanager.binaryTree.BinarySearchTree;
import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;
import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;
import com.mycompany.londonpostcodemanager.terminal.TerminalMenu;
import com.mycompany.londonpostcodemanager.benchmark.BenchmarkTests;
import java.util.Scanner;

public class LondonPostcodeManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("  London Postcode Manager - Data Structure");
        System.out.println("==========================================");

        System.out.println("Please select the data structure you want to use:");
        System.out.println("1. Binary Search Tree (BST)");
        System.out.println("2. AVL Tree");
        System.out.println("3. Min Heap");
        System.out.println("4. Benchmarking Tool");
        System.out.println("5. Exit");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.println("Using Binary Search Tree...");
                PostcodeManagerInterface manager = new BinarySearchTree();
                launchMenu(scanner, manager, "BST");
            }

            case "2" -> {
                System.out.println("Using AVL Tree...");
                PostcodeManagerInterface manager = new AvlTree();
                launchMenu(scanner, manager, "AVL");
            }

            case "3" -> {
                System.out.println("Using Min Heap...");
                PostcodeManagerInterface manager = new MinimumHeap(20000);
                launchMenu(scanner, manager, "Heap");
            }

            case "4" -> {
                System.out.println("\n=== Running Benchmark Tests ===\n");
                BenchmarkTests.main(null);
            }

            case "5" -> {
                System.out.println("Exiting. Goodbye!");
                return;
            }

            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }
    }

    private static void launchMenu(Scanner scanner, PostcodeManagerInterface manager, String type) {
        TerminalMenu menu = new TerminalMenu(manager);
        System.out.println("\n== " + type + " Postcode Manager Menu ==\n");
        menu.start();
    }
}
