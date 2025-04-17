package com.mycompany.londonpostcodemanager.shared;


public interface PostcodeManagerInterface {
    void insert(String postcode);         // Add a postcode
    boolean search(String postcode);      // Search by string
    int count();                          // Return number of entries
    String delete();                      // Deletes minimum or exact depending on structure
    String[] inOrder();                   // Return alphabetical list
}
