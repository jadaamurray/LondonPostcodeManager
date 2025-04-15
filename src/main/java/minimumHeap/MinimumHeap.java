package minimumHeap;

public class MinimumHeap {
    private String[] heap;
    private int size;
    private int maxSize;

    public MinimumHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new String[maxSize];
    }

    public int Count() {
        return size;
    }

    public void Insert(String postcode) {
        if (postcode == null || postcode.trim().isEmpty())
            throw new IllegalArgumentException("Invalid postcode.");

        if (size >= maxSize) {
            throw new IllegalStateException("Cannot insert: Heap is full.");
        }

        heap[size] = postcode;
        siftUp(size);
        size++;
    }
    public String ExtractMinimum() {
        if (size == 0) return null;

        String min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    public boolean Search(String postcode) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equalsIgnoreCase(postcode)) {
                return true;
            }
        }
        return false;
    }

    public String[] InOrder() {
        MinimumHeap copy = new MinimumHeap(maxSize);
        for (int i = 0; i < size; i++) {
            copy.Insert(heap[i]);
        }

        String[] sorted = new String[size];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = copy.ExtractMinimum();
        }
        return sorted;
    }

    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index].compareTo(heap[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void siftDown(int index) {
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
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        String temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

