package com.mycompany.londonpostcodemanager.binaryTree;

import java.util.ArrayList;
import java.util.List;

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

        SearchResult result = findNodeAndParent(postcode);

        // If postcode exists already
        if (result.node != null && result.node.postcode.equals(postcode)) {
            System.out.println("Postcode " + postcode + " already exists");
            return;
        }

        if (result.parent == null) {
            throw new IllegalStateException("Parent cannot be null for non-root insertion");
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
    // private method for search results that returns the node, parent, and if the
    private SearchResult findNodeAndParent(String postcode) {
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

    private SearchResult findMinWithParent(Node start, Node parent) {
        boolean isLeftChild = false;
        while (start.left != null) {
            parent = start;
            start = start.left;
            isLeftChild = true;
        }
        return new SearchResult(start, parent, isLeftChild);
    }

    public boolean delete(String postcode) {
        validPostcodeCheck(postcode);
        SearchResult result = findNodeAndParent(postcode);
        if (result.node == null) { // postcode to delete not found
            return false;
        }

        Node toDelete = result.node;
        Node parent = result.parent;

        // node has no children
        if (toDelete.left == null && toDelete.right == null) {
            if (parent == null) {
                this.root = null; // Deleting the root
            } else if (result.isLeft) {
                parent.left = null;
                toDelete.postcode = null;
            } else {
                parent.right = null;
                toDelete.postcode = null;
            }

        // node has one child
        } else if (toDelete.left == null || toDelete.right == null) {
            Node child = (toDelete.left != null) ? toDelete.left : toDelete.right;
            if (parent == null) {
                root = child; // setting root to the only child
            } else if (result.isLeft) {
                parent.left = child;
                child.parent = null;
            } else {
                parent.right = child;
                child.parent = null;
            }
            child.parent = parent;
        }
        // node has two children
        else {
            // Find successor (minimum in right subtree)
            SearchResult successorResult = findMinWithParent(toDelete.right, toDelete);
            Node successor = successorResult.node;

            // Copy successor's data
            toDelete.postcode = successor.postcode;

            // Delete the successor (which has at most one right child)
            if (successorResult.isLeft) {
                successorResult.parent.left = successor.right;
            } else {
                successorResult.parent.right = successor.right;
            }

            if (successor.right != null) {
                successor.right.parent = successorResult.parent;
            }
        }
        this.size--;
        return true;
    }

    public String[] inOrder() {
        List<String> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderTraversal(Node node, List<String> result) {
        if (node != null) {
            inOrderTraversal(node.left, result);  // visit left subtree
            result.add(node.postcode);            // visit current node
            inOrderTraversal(node.right, result); // visit right subtree
        }
    }

    private void validPostcodeCheck(String postcode) {
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
