package sorting;

import datatypes.MinHeap;

import java.util.ArrayList;

// Prob wrong this implementation first makes a min heap from the array(O(n))
// Then it removes an element and builds a new array (n*log(n) for every remove.
// This makes it n * nlogn ??

public class HeapSort<Type extends Comparable<Type>> {
    ArrayList<Type> data;

    public HeapSort(ArrayList<Type> data) {
        this.data = data;
    }

    public ArrayList<Type> sort(){
        // Split data array into halfs
        MinHeap<Type> minHeap = new MinHeap<>();
        ArrayList<Type> doneArr = new ArrayList<>();

        // Fill up heap with data from array
        for (Type t : data) {
            minHeap.add(t);
        }

        // Remove an element one at a time from the heap, and build a sorted array
        while (minHeap.size() > 0){
            doneArr.add(minHeap.remove());
        }
        return doneArr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(85);
        arr.add(82);
        arr.add(4);
        arr.add(2);
        arr.add(12);
        arr.add(1);


        HeapSort<Integer> heapSort = new HeapSort<>(arr);
        System.out.println(heapSort.sort().toString());
    }
}
