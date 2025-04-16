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
        validPostcodeCheck(postcode);
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

    public boolean search(String postcode) {
        validPostcodeCheck(postcode);
        if (root == null) {
            System.out.println("Tree is empty");
            return false;
        }
        Node current = root, parent = null;
        while (!current.postcode.equals(postcode)) {
            int cmp = postcode.compareTo(current.postcode);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            }
        }
        return false;
    }

    public boolean delete(String postcode) {

    }

    private void traverse(Node current) {}

    public void validPostcodeCheck(String postcode) {
        if (postcode == null) {
            throw new IllegalArgumentException("postcode cannot be null");
        }
        /*// London postcode regex pattern
        String pattern = "^([A-Z][A-HJ-Y]?\\d[A-Z\\d]? ?\\d[A-Z]{2}|GIR ?0A{2})$";
        pattern londonPostcodePattern = pattern.compile(pattern, pattern.CASE_INSENSITIVE);

        // Basic format check
        if (!londonPostcodePattern.matcher(postcode).matches()) {
            return false;
        }
        String outwardCode = postcode.split(" ")[0].toUpperCase();
        String[] londonDistricts = {
                "E", "EC", "N", "NW", "SE", "SW", "W", "WC",
                "BR", "CR", "DA", "EN", "HA", "IG", "KT", "RM",
                "SM", "TW", "UB", "WD"
        };

        for (String district : londonDistricts) {
            if (outwardCode.startsWith(district)) {
                return true;
            }
        } */
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
