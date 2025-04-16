package com.mycompany.londonpostcodemanager.avlTree;

import java.util.ArrayList;
import java.util.List;

public class avlTree {
    private class avlNode {
        String postcode;
        int height;
        avlNode left;
        avlNode right;

        avlNode(String postcode) {
            this.postcode = postcode;
            this.height = 1;
        }
    }

    private avlNode root;

    // constructor
    public avlTree() {
        this.root = null;
    }

    // 1. count postcodes
    public int Count() {
        return countNodes(root);
    }

    private int countNodes(avlNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // 2. insert postcode
    public void insert(String postcode) {
        if (!Search(postcode)) {
            this.root = insert(this.root, postcode);
        }
    }

    private avlNode insert(avlNode node, String postcode) {
        if (node == null) return new avlNode(postcode);

        int cmp = postcode.compareTo(node.postcode);
        if (cmp < 0) node.left = insert(node.left, postcode);
        else if (cmp > 0) node.right = insert(node.right, postcode);
        else return node; // duplicate, do not insert

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalance(node, postcode);
    }

    // 3. Delete postcode
    public boolean delete(String postcode) {
        if (!Search(postcode)) return false;
        root = delete(root, postcode);
        return true;
    }

    private avlNode delete(avlNode node, String postcode) {
        if (node == null) return null;

        int cmp = postcode.compareTo(node.postcode);
        if (cmp < 0) node.left = delete(node.left, postcode);
        else if (cmp > 0) node.right = delete(node.right, postcode);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            avlNode successor = minValueNode(node.right);
            node.postcode = successor.postcode;
            node.right = delete(node.right, successor.postcode);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalanceAfterDelete(node);
    }

    // 4. Search postcode
    public boolean Search(String postcode) {
        return search(root, postcode);
    }

    private boolean search(avlNode node, String postcode) {
        if (node == null) return false;

        int cmp = postcode.compareTo(node.postcode);
        if (cmp == 0) return true;
        else if (cmp < 0) return search(node.left, postcode);
        else return search(node.right, postcode);
    }

    // 5. Return postcodes in order
    public String[] inOrder() {
        List<String> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderTraversal(avlNode node, List<String> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.postcode);
            inOrderTraversal(node.right, list);
        }
    }

    // Helper methods

    private int height(avlNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(avlNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private avlNode rightRotate(avlNode y) {
        avlNode x = y.left;
        avlNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    private avlNode leftRotate(avlNode x) {
        avlNode y = x.right;
        avlNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    private avlNode rebalance(avlNode node, String postcode) {
        int balance = getBalance(node);

        if (balance > 1 && postcode.compareTo(node.left.postcode) < 0)
            return rightRotate(node);

        if (balance < -1 && postcode.compareTo(node.right.postcode) > 0)
            return leftRotate(node);

        if (balance > 1 && postcode.compareTo(node.left.postcode) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && postcode.compareTo(node.right.postcode) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private avlNode rebalanceAfterDelete(avlNode node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private avlNode minValueNode(avlNode node) {
        avlNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }
}
