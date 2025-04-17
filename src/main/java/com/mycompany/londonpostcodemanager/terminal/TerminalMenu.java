package com.mycompany.londonpostcodemanager.terminal;

import java.io.*;
import java.util.Scanner;
import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;
import com.mycompany.londonpostcodemanager.shared.DeletablePostcodeManager;
import com.mycompany.londonpostcodemanager.shared.ExtractablePostcodeManager;

public class TerminalMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PostcodeManagerInterface manager;

    public TerminalMenu(PostcodeManagerInterface manager) {
        this.manager = manager;
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Postcode Management Menu ---");
            System.out.println("1. Count postcodes");
            System.out.println("2. Check postcode existence");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Output postcodes to file (alphabetically)");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Total postcodes: " + manager.count());
                    break;

                case "2":
                    System.out.print("Enter postcode to search: ");
                    String search = scanner.nextLine().trim().toUpperCase();
                    if (search.isEmpty()) {
                        System.out.println("Invalid input. Postcode cannot be empty.");
                    } else {
                        boolean found = manager.search(search);
                        System.out.println(found ? "Postcode found." : "Postcode not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter postcode to add: ");
                    String toAdd = scanner.nextLine().trim().toUpperCase();
                    if (toAdd.isEmpty()) {
                        System.out.println("Invalid postcode. Try again.");
                    } else {
                        try {
                            manager.insert(toAdd);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case "4":
                    if (manager instanceof DeletablePostcodeManager treeManager) {
                        System.out.print("Enter postcode to delete: ");
                        String postcode = scanner.nextLine().trim().toUpperCase();
                        boolean deleted = treeManager.delete(postcode);
                        System.out.println(deleted ? "Deleted." : "Postcode not found.");
                    } else if (manager instanceof ExtractablePostcodeManager heapManager) {
                        String removed = heapManager.extractMinimum();
                        System.out.println(removed != null ? "Deleted: " + removed : "Heap is empty.");
                    } else {
                        System.out.println("Delete operation not supported.");
                    }
                    break;


                case "5":
                    System.out.print("Enter filename to save sorted postcodes (e.g., output.txt): ");
                    String filename = scanner.nextLine().trim();
                    if (!filename.endsWith(".txt")) {
                        filename += ".txt";
                    }

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                        String[] sorted = manager.inOrder();
                        if (sorted.length == 0) {
                            System.out.println("No postcodes to write.");
                        } else {
                            for (String code : sorted) {
                                writer.write(code);
                                writer.newLine();
                            }
                            System.out.println("Sorted postcodes written to " + filename);
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case "6":
                    System.out.println("Exiting application. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
        }
    }
}
