package sorting;

import java.util.ArrayList;

public class QuickSort<Type extends Comparable> {
    ArrayList<Type> data;

    // Swaps elements
    public void exchange(int i, int j){
        Type oldValue = data.get(i);
        data.set(i, data.get(j));
        data.set(j, oldValue);
    }

    public QuickSort(ArrayList<Type> data) {
        this.data = data;
    }

    // Calls quickSort on the entire array
    public ArrayList<Type> sort(){
        quickSort(0, data.size());
        return data;
    }

    // QuickSort has 2 "pointers" , one start low and increments, other starts high and decreases
    // Until they overlap we get the middle part(pivot index), and call quicksort on both halfs (left and right).
    public void quickSort(int low, int high){
        if(low < high){
            int middleIndex = partition(low, high);
            quickSort(low, middleIndex);
            quickSort(middleIndex + 1, high);
        }
    }

    public int partition(int low, int high){
        // Find middle point (pivot)
        // In this case we always use the lowest value
        // This is ONLY because it's easiest
        Type pivot = data.get(low);

        // Makes the 2 points and makes them run up
        int i = low;
        int j = high;
        while (i < j){
            // Keep incrementing the left(i) until we find a value that isn't smaller
            do{
                i++;
            }while(data.get(i).compareTo(pivot) <= 0 && i < data.size() -  1);
            // When we find a value that isn't smaller, we go to the end, and keep going left until we find a value that isn't bigger
            do{
                j--;
            }while(data.get(j).compareTo(pivot) > 0);
            // When we have both values, we swap them so they are in "the right spot"
            if(i < j){
                exchange(i, j);
            }
        }
        // Imagine the pivot isn't even in the set itself. When we are done, every element on the right side is bigger
        // And every element on the left is smaller, we can then swap it into J, which is the "start" of the bigger side
        // And now we know, that both sides of the pivot, are either smaller or bigger
        exchange(low, j);
        return j;
    }


    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(85);
        arr.add(82);
        arr.add(4);
        arr.add(2);
        arr.add(12);
        arr.add(24);
        arr.add(23);
        arr.add(11);
        arr.add(5);
        arr.add(1);


        QuickSort<Integer> quickSort = new QuickSort<>(arr);
        System.out.println(quickSort.sort().toString());
    }
}
