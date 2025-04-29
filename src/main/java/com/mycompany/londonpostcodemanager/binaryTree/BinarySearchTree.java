package com.mycompany.londonpostcodemanager.binaryTree;
import com.mycompany.londonpostcodemanager.shared.DeletablePostcodeManager;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree implements DeletablePostcodeManager {
    private Node root;
    private int size;

    private static class Node {
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

    @Override
    public int count() {
        return size;
    }

    @Override
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

    @Override
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

    @Override
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
                toDelete.parent = null;
            } else {
                parent.right = null;
                toDelete.postcode = null;
            }

        // node has one child
        } else if (toDelete.left == null || toDelete.right == null) {
            Node child = (toDelete.left != null) ? toDelete.left : toDelete.right;
            if (parent == null) {
                root = child; // setting root to the only child
                toDelete.parent = null;
            } else if (result.isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
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

    @Override
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
    }
}
