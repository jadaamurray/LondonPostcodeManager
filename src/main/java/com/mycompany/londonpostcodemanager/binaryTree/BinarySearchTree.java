package com.mycompany.londonpostcodemanager.binaryTree;

public class BinarySearchTree {
    private Node root;
    private int size;

    private class Node {
        private String postcode;
        Node left, right;

        public Node(String postcode) {
            this.postcode = postcode;
            this.left = this.right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }
}
