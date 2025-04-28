package com.mycompany.londonpostcodemanager.terminal;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.mycompany.londonpostcodemanager.PostcodeFileLoader;
import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;
import com.mycompany.londonpostcodemanager.shared.DeletablePostcodeManager;
import com.mycompany.londonpostcodemanager.shared.ExtractablePostcodeManager;

public class TerminalMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PostcodeManagerInterface manager;

    public TerminalMenu(PostcodeManagerInterface manager) {
        this.manager = manager;
    }

    private static boolean isValidPostcode(String postcode) {
        if (postcode == null) {
            System.out.println("Postcode cannot be empty.");
            return false;
        }

        // London postcode regex pattern
        String pattern = "^([A-Z][A-HJ-Y]?\\d[A-Z\\d]? ?\\d[A-Z]{2}|GIR ?0A{2})$";
        Pattern londonPostcodePattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        // Format check
        if (!londonPostcodePattern.matcher(postcode).matches()) {
            System.out.println("Invalid format.");
            return false;
        }

        // Additional London-specific checks
        String outwardCode = postcode.split(" ")[0].toUpperCase();
        String[] londonDistricts = {
                "E", "EC", "N", "NW", "SE", "SW", "W", "WC",
                "BR", "CM", "CR", "DA", "EN", "HA", "IG", "SL", "TN", "KT", "RM",
                "SM", "TW", "UB", "WD"
        };

        for (String district : londonDistricts) {
            if (outwardCode.startsWith(district)) return true;
        }
        System.out.println("Please enter a postcode that is within London.");


        return false;

    }

    public void start() {
        while (true) {
            System.out.println("\n--- Postcode Management Menu ---");
            System.out.println("1. Load postcode file");
            System.out.println("2. Count postcodes");
            System.out.println("3. Add a postcode");
            System.out.println("4. Delete a postcode");
            System.out.println("5. Check postcode existence");
            System.out.println("6. Output postcodes to file (alphabetically)");
            System.out.println("7. Output to custom file");
            System.out.println("8. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> loadFile();

                case "2" -> System.out.println("Total postcodes: " + manager.count());

                case "3" -> {
                    String toAdd;
                    do {
                        System.out.print("Enter postcode to add: ");
                    } while (!isValidPostcode(toAdd = scanner.nextLine().trim().toUpperCase()));
                    try {
                        manager.insert(toAdd);
                        //System.out.println("Postcode added.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case "4" -> {
                    if (manager instanceof DeletablePostcodeManager treeManager) {
                        String postcode;
                        do {
                            System.out.print("Enter postcode to delete: ");
                        } while (!isValidPostcode(postcode = scanner.nextLine().trim().toUpperCase()));
                        boolean deleted = treeManager.delete(postcode);
                        System.out.println(deleted ? "Deleted." : "Postcode not found.");
                    } else if (manager instanceof ExtractablePostcodeManager heapManager) {
                        String removed = heapManager.extractMinimum();
                        System.out.println(removed != null ? "Deleted: " + removed : "Heap is empty.");
                    } else {
                        System.out.println("Delete operation not supported.");
                    }
                }

                case "5" -> {
                    String search;
                    do {
                        System.out.print("Enter postcode to search: ");
                    } while (!isValidPostcode(search = scanner.nextLine().trim().toUpperCase()));
                    if (search.isEmpty()) {
                        System.out.println("Invalid input. Postcode cannot be empty.");
                    } else {
                        boolean found = manager.search(search);
                        System.out.println(found ? "Postcode exists." : "Postcode not found.");
                    }
                }

                case "6" -> {
                    String filename = "output.txt";
                    outputPostcodesToFile(filename);
                }

                case "7" -> {
                    System.out.print("Enter custom filename (e.g., results.txt): ");
                    String custom = scanner.nextLine().trim();
                    if (!custom.endsWith(".txt")) custom += ".txt";
                    outputPostcodesToFile(custom);
                }

                case "8" -> {
                    System.out.println("Exiting application. Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option. Please enter a number between 1 and 8.");
            }
        }
    }

    private void loadFile() {
        System.out.println("Please choose a postcode file to load:");
        System.out.println("1. 1000_London_Postcodes.txt");
        System.out.println("2. 2000_London_Postcodes.txt");
        System.out.println("3. 4000_London_Postcodes.txt");
        System.out.println("4. 8000_London_Postcodes.txt");
        System.out.println("5. 16000_London_Postcodes.txt");
        System.out.print("Enter your choice: ");

        String input = scanner.nextLine().trim();
        String filename = switch (input) {
            case "2" -> "2000_London_Postcodes.txt";
            case "3" -> "4000_London_Postcodes.txt";
            case "4" -> "8000_London_Postcodes.txt";
            case "5" -> "16000_London_Postcodes.txt";
            default -> "1000_London_Postcodes.txt";
        };

        PostcodeFileLoader.loadPostcodesFromResource(filename, manager);
    }

    private void outputPostcodesToFile(String filename) {
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
    }
}
