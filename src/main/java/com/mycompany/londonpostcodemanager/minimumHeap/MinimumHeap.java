package com.mycompany.londonpostcodemanager.minimumHeap;

import com.mycompany.londonpostcodemanager.shared.ExtractablePostcodeManager;

public class MinimumHeap implements ExtractablePostcodeManager {

    private String[] heap;
    private int size;
    private final int maxSize;

    // Constructor to initialize an empty heap
    public MinimumHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new String[maxSize];
    }

    // Returns the number of postcodes currently in the heap
    @Override
    public int count() {
        return size;
    }

    // Inserts a postcode into the heap and maintains heap property
    @Override
    public void insert(String postcode) {
        if (postcode == null || postcode.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid postcode.");
        }

        if (size >= maxSize) {
            throw new IllegalStateException("Cannot insert: Heap is full.");
        }
        if (search(postcode)) {
            System.out.println("Postcode " + postcode + " already exists");
        } else {
            heap[size] = postcode;
            heapifyUp(size);
            size++;
            System.out.println("Postcode added.");
        }
    }

    // Deletes and returns the minimum (alphabetically first) postcode
    public String delete() {
        return extractMinimum();
    }

    // Returns true if the given postcode is found in the heap
    @Override
    public boolean search(String postcode) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equalsIgnoreCase(postcode)) {
                return true;
            }
        }
        return false;
    }

    // Returns all postcodes in alphabetical order and clears the heap
    @Override
    public String[] inOrder() {
        String[] sorted = new String[size];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = extractMinimum(); // Mutates the heap
        }
        return sorted;
    }

    // Removes and returns the root (minimum) element, then re-heapifies
    @Override
    public String extractMinimum() {
        if (size == 0) return null;

        String min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // Restores heap order from bottom to top after insert
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index].compareTo(heap[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Restores heap order from top to bottom after delete
    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }
        if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Swaps elements at two indices in the heap
    private void swap(int i, int j) {
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

