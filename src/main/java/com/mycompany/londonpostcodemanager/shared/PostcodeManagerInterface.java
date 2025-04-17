package com.mycompany.londonpostcodemanager.shared;


public interface PostcodeManagerInterface {
    void insert(String postcode);
    boolean search(String postcode);
    int count();
    String[] inOrder();

}
