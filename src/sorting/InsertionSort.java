package sorting;

import java.util.ArrayList;

// For every element, we compare it to our sorted array, and insert into the right spot.
// This means we are doing O(n^2) first n being our sorted list, second n being the unsorted array.
public class InsertionSort<Type extends Comparable> {
    ArrayList<Type> data;

    public InsertionSort(ArrayList<Type> data) {
        this.data = data;
    }

    public void exchange(int i, int j){
        Type oldValue = data.get(i);
        data.set(i, data.get(j));
        data.set(j, oldValue);
    }

    public ArrayList<Type> sort(){
        // We say first element is sorted
        int i = 1;
        // Look through every element
        while (i < data.size()){
            // For every element, we compare every previous sorted element
            int j = i;
            // When we find one that is smaller, we exchange it
            while (j > 0 && data.get(j-1).compareTo(data.get(j)) > 0)
            {
                exchange(j, j-1);
                j--;
            }
            i++;
        }
        return data;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(85);
        arr.add(82);
        arr.add(4);
        arr.add(2);
        arr.add(12);
        arr.add(1);


        InsertionSort<Integer> insertionSort = new InsertionSort<>(arr);
        System.out.println(insertionSort.sort().toString());
    }
}
