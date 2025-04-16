package com.mycompany.londonpostcodemanager.binaryTree;

public class BinarySearchTree {
    private Node root;
    private int size;

    private class Node {
        private String postcode;
        Node parent, left, right;

        public Node(String postcode) {
            this.postcode = postcode;
            this.left = this.right = null;
            this.parent = null;
        }
    }

    // private helper class for search results
    private static class SearchResult {
        final Node node;
        final Node parent;
        final boolean isLeft;

        SearchResult(Node node, Node parent, boolean isLeft) {
            this.node = node;
            this.parent = parent;
            this.isLeft = isLeft;
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

        if (root == null) { // if tree is empty, insert at the root
            root = new Node(postcode);
            this.size++;
            return;
        }

        SearchResult result = searchWithParent(postcode);

        // If postcode exists already
        if (result.node != null && result.node.postcode.equals(postcode)) {
            System.out.println("Postcode " + postcode + " already exists");
            return;
        }

        // creating new node and linking it to the tree
        Node newNode = new Node(postcode);
        newNode.parent = result.parent;
        int cmp = postcode.compareTo(result.parent.postcode);
        if (cmp < 0) {
            result.parent.left = newNode;
        } else if (cmp > 0) {
            result.parent.right = newNode;
        }
        this.size++;
    }

    public boolean search(String postcode) {
        validPostcodeCheck(postcode);
        Node current = root;
        while (current != null) {
            int cmp = postcode.compareTo(current.postcode);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) { // if postcode is smaller than the current node, move to the left
                current = current.left;
            } else {
                current = current.right; // if bigger, move to the right
            }
        }
        return false;
    }

    private SearchResult searchWithParent(String postcode) {
        validPostcodeCheck(postcode);
        Node current = root;
        Node parent = null;
        boolean isLeft = false;

        while (current != null) {
            int cmp = postcode.compareTo(current.postcode);
            if (cmp == 0) {
                return new SearchResult(current, parent, isLeft); // postcode found
            }

            parent = current;
            if (cmp < 0) { // if postcode is smaller than the current node, move to the left
                current = current.left;
                isLeft = true;
            } else {
                current = current.right; // if bigger, move to the right
                isLeft = false;
            }
        }
        // If not found, return the parent where it should be inserted
        return new SearchResult(null, parent, isLeft); // not found
    }

    public boolean delete(String postcode) {
        validPostcodeCheck(postcode);
        SearchResult result = searchWithParent(postcode);
        if (result == null) { // postcode to delete not found
            return false;
        }

        Node toDelete = result.node;
        Node parent = result.parent;

        // Node has no children
        if (toDelete.left == null && toDelete.right == null) {
            if (parent == null) {
                root = null; // Deleting the root
            } else if (result.isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        }
        return false;
        //



        /*int cmp = postcode.compareTo(result.parent.postcode);
        if (result.node.postcode.equals(postcode)) {
            if (cmp < 0) { // if postcode to delete is on the left of the parent (smaller)
                result.parent.left = null;
            } else {
                result.parent.right = null;
            }
            this.size--;
        }*/


    }

    private void traverse(Node current) {
    }

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
