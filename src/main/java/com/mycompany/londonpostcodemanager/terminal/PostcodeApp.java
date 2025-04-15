package com.mycompany.londonpostcodemanager.terminal;

import com.mycompany.londonpostcodemanager.minimumHeap.MinimumHeap;
import java.io.*;
import java.util.Scanner;

// this needs more error handling - eg. you should not be able to add an invalid postcode or if something is empty we print empty statement
// output file has to have extention txt I believe
// this terminal is for heap we need to make sure it accomodates all 3 data structures
public class PostcodeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the postcode file to load (e.g., 1000_London_Postcodes.txt): ");
        String filename = scanner.nextLine();

        // Load postcodes from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Insert(line.trim().toUpperCase());
            }
            System.out.println("Postcodes loaded successfully.");
        } catch (IOException e) {
            System.out.println("Could not read file. Starting empty.");
        }

        // User menu
        while (true) {
            System.out.println("\n--- Postcode Management Menu ---");
            System.out.println("1. Count the number of postcodes");
            System.out.println("2. Check if a postcode is in the list");
            System.out.println("3. Add a postcode to the list");
            System.out.println("4. Delete a postcode from the list");
            System.out.println("5. Output all postcodes to a text file in alphabetical order");
            System.out.println("6. Exit");

            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":


                case "2":


                case "3":


                case "4":


                case "5":
                    System.out.print("Enter filename to save sorted postcodes (e.g., output.txt): ");
                    String outputFile = scanner.nextLine();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                        String[] sorted = InOrder();
                        for (String code : sorted) {
                            writer.write(code);
                            writer.newLine();
                        }
                        System.out.println("Sorted postcodes written to " + outputFile);
                    } catch (IOException e) {
                        System.out.println("Error writing to file.");
                    }
                    break;

                case "6":
                    System.out.println("Exiting application.");
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1–6.");
            }
        }
    }
}
