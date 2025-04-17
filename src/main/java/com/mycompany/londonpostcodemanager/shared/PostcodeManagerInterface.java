package com.mycompany.londonpostcodemanager.shared;


public interface PostcodeManagerInterface {
    void insert(String postcode);
    boolean search(String postcode);
    int count();
    String[] inOrder();

    // Removes and returns the root (minimum) element, then re-heapifies
    String extractMinimum();
}
