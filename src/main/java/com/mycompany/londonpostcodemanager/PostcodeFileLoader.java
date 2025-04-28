package com.mycompany.londonpostcodemanager;

import com.mycompany.londonpostcodemanager.shared.PostcodeManagerInterface;

import java.io.InputStream;
import java.util.Scanner;

public class PostcodeFileLoader {

    public static void loadPostcodesFromResource(String filename, PostcodeManagerInterface manager) {
        int count = 0;

        try (InputStream input = PostcodeFileLoader.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.err.println(" Could not find file: " + filename);
                return;
            }

            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim().toUpperCase();
                if (!line.isEmpty()) {
                    manager.insert(line);
                    count++;
                }
            }

            System.out.println(" Loaded " + count + " postcodes from " + filename);
        } catch (Exception e) {
            System.err.println(" Failed to load postcodes from file: " + filename);
            e.printStackTrace();
        }
    }
}
