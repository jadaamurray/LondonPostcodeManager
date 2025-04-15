package com.mycompany.londonpostcodemanager.avlTree;

import java.util.ArrayList;
import java.util.List;

public class avlTree {
    private class avlNode {
        String key;
        int height;
        avlNode left;
        avlNode right;

        avlNode(String key) {
            this.key = key;
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
    public void insert(String key) {
        this.root = insert(this.root, key);
    }

    private avlNode insert (avlNode node, String key) {
       if (node == null) return new avlNode(key);

       int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insert(node.left, key);
       else if (key.compareTo(node.key) > 0) node.right = insert(node.right, key);
       else return node; // duplicate, do not insert

        node.height = 1 + Math.max(countNodes(node.left), countNodes(node.right))
        return rebalance(node, key);
    }

    // 3. Delete postcode
    public boolean delete(String key) {
        if (!Search(key)) return false;
        root = delete(root, key);
        return true;
    }

    private avlNode delete(avlNode node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null && node.right == null) {
                node = (node.left == null ? node.right : node.left);
            } else {
                avlNode successor = minValueNode(node.right);
                node.key = successor.key;
                node.right = delete(node.right, successor.key);
            }
        }

        if (node == null) return null;
        node.height = 1 + Math.max(countNodes(node.left), countNodes(node.right));
        return rebalanceAfterDelete(node);
    }

    // 4. Search postcode
    public boolean Search(String key) {
        return search(root, key);
    }

    private boolean search(avlNode node, String key) {
        if (node == null) return false;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return true;
        return cmp < 0 && search(node.left, key);
    }

    // 5. Return postcodes in order
    public String[] inOrder() {
        List<String> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result.toArray(new String[0]);
    }

    private void inOrderTraversal(avlNode node, List<String> result) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.key);
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

    private avlNode rightRotate(avlNode node) {
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

    private avlNode rebalance(avlNode node, String key) {
        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
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
