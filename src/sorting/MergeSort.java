package sorting;

import java.util.ArrayList;

// Recursively splits the array into halfs, until we have single element arrays, then it builds up a new array.
// Does comparions of the elements, and in the end we have a sorted array.
public class MergeSort<Type extends Comparable> {
    ArrayList<Type> data;

    public MergeSort(ArrayList<Type> data) {
        this.data = data;
    }

    public ArrayList<Type> sort(){
        // Split data array into halfs
        splitArr(data);
        return data;
    }

    // The merging happens here
    private void splitArr(ArrayList<Type> inArr){
        if(inArr.size() <= 1){
            // Can't split anymore
            return;
        }
        // Get the middle of the arraysize (we take first half into one array, other half into another one)
        int midIndex = inArr.size() / 2;
        ArrayList<Type> firstHalf = new ArrayList<>();
        ArrayList<Type> secondHalf = new ArrayList<>();

        for(int i = 0; i < midIndex; i++){
            if(i < midIndex) {
                // Removes and returns an element
                firstHalf.add(inArr.remove(0));
            }
        }
        // Have to do a while loop afterwards, since we are removing elements, and the size changes
        while(inArr.size() > 0){
            secondHalf.add(inArr.remove(0));
        }

        System.out.println("Splitting " + firstHalf.toString());
        System.out.println("Splitting " + secondHalf.toString());
        splitArr(firstHalf);
        splitArr(secondHalf);

        // Now for every 1 element array we take the element that's smallest, and puts first. (We sort it).
        while (firstHalf.size() > 0 && secondHalf.size() > 0){
            // If firstHalf's element is smaller than secondHalf's element.
            if(firstHalf.get(0).compareTo(secondHalf.get(0)) < 0){
                inArr.add(firstHalf.remove(0));
            }
            // If secondHalf's element is bigger
            else{
                inArr.add(secondHalf.remove(0));
            }
        }

        // Now we have lonely elements that couldn't get compared. We add that one.
        // Say the first element all had smaller numbers etc.
        while(firstHalf.size() > 0){
            inArr.add(firstHalf.remove(0));
        }
        while(secondHalf.size() > 0){
            inArr.add(secondHalf.remove(0));
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(85);
        arr.add(82);
        arr.add(4);
        arr.add(2);
        arr.add(12);
        arr.add(1);


        MergeSort<Integer> mergeSort = new MergeSort<>(arr);
        System.out.println(mergeSort.sort().toString());
    }
}
