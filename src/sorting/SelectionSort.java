package sorting;

import java.util.ArrayList;

// Keeps count of the latest lowest value, and the index we found it.
// Then we loop through THE ENTIRE ARRAY everytime, and then we swap the lowest value index with the next lowest element.
// O(n^2) since we have to go through every element every element
public class SelectionSort<Type extends Comparable> {
    ArrayList<Type> data;

    public SelectionSort(ArrayList<Type> data) {
        this.data = data;
    }

    public void exchange(int i, int j){
        Type oldValue = data.get(i);
        data.set(i, data.get(j));
        data.set(j, oldValue);
    }

    public ArrayList<Type> sort(){
        // Store lowest value for the iteration & it's location
        Type currentLowest;
        int currentLowestI;
        // How far we have sorted the array
        int sortedI = 0;
        // Until we are done sorting
        while(sortedI < data.size() - 1){
            currentLowest = data.get(sortedI);
            currentLowestI = sortedI;

            // Look through every element until the end of the array.
            for(int i = sortedI + 1; i < data.size(); i++){
                // If it's smaller, set the currentLowest values to that.
                if(data.get(i).compareTo(currentLowest) < 0){
                    currentLowest = data.get(i);
                    currentLowestI = i;
                }
            }
            exchange(sortedI, currentLowestI);
            sortedI++;
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

        SelectionSort<Integer> selectionSort = new SelectionSort<>(arr);
        System.out.println(selectionSort.sort().toString());
    }
}
