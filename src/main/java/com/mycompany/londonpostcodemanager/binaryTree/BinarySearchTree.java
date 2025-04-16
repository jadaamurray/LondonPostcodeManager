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
        this.root = null;
        this.size = 0;
    }

    public int count() {
        return size;
    }

    public void insert(String postcode) {
        if (postcode == null) {
            throw new IllegalArgumentException("postcode cannot be null");
        }
        if (root == null) {
            root = new Node(postcode);
            this.size ++;
            return;
        }
        Node current = root, parent = null; // starting at the root
        while (current != null) {
            parent = current;
            int cmp = postcode.compareTo(current.postcode);
            if (cmp == 0) { // postcode to insert is already in the tree
                System.out.println("Postcode " + postcode + " already exists");
                return;
            } else if (cmp < 0) { // if postcode is smaller than the current node, move to the left
                current = current.left;
            } else { // if bigger, move to the right
                current = current.right;
            }
        }
        Node newNode = new Node(postcode); // creating new node and linking it to the tree
        int cmp = postcode.compareTo(current.postcode);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
