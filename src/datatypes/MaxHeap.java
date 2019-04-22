package datatypes;

import java.util.ArrayList;

// Heap is useful if you need to have a data structure, where the parent is always higher than it's children.
// Makes you able to pop off an element from the top in n*logn
public class MaxHeap<Item extends Comparable<Item>> {
    // Parent = (pos - 1) / 2
    // Children = ((pos * 2) + 1) and ((pos * 2) + 2)

    private ArrayList<Item> items;

    public MaxHeap() {
        this.items = new ArrayList<>();
    }

    public int size(){
        return items.size();
    }


    public int parent(int i){
        if(i == 0){
            // No Parent
            return -1;
        }
        return (int) Math.floor((i - 1) / 2);
    }

    public int leftChild(int i){
        int childI = (i * 2) + 1;
        if (childI > (size() -1)) {
            // No Left Child
            return -1;
        }
        return childI;
    }

    public int rightChild(int i){
        int childI = (i * 2) + 2;
        if (childI > (size() -1)) {
            // No Right Child
            return -1;
        }
        return childI;
    }

    public void exchange(int i, int j){
        Item oldValue = items.get(i);
        items.set(i, items.get(j));
        items.set(j, oldValue);
    }

    public void add(Item item){
        // Find correct spot, recursively
        items.add(item);

        // Swim up until if it's in the right spot
        swim(size() - 1);
    }

    public Item remove(){
        // Remove first element
        Item itemToRemove = items.get(0);
        Item lowestItem = items.get(size() - 1);
        // Set last element, at index 0
        items.set(0, lowestItem);
        // Remove the old location
        items.remove(size() - 1);

        // Sink until the lowest element is at the right spot again.
        sink(0);
        return itemToRemove;
    }

    private void sink(int itemI){
        // Find the largest child, calls sink recursively
        int leftChildI = leftChild(itemI);
        Item leftChild = leftChildI >= 0 ? items.get(leftChildI) : null;
        int rightChildI = rightChild(itemI);
        Item rightChild = rightChildI >= 0 ? items.get(rightChildI) : null;
        Item currentItem = size() > 0 ? items.get(itemI) : null;

        // If no children exists
        if(leftChildI == -1 || leftChild.compareTo(currentItem) <= 0){
            return;
        }
        // If there only is a left child
        // or
        // If leftChild is bigger than right
        else if(rightChild == null || leftChild.compareTo(rightChild) > 0){
            exchange(itemI, leftChildI);
            sink(leftChildI);
        }
        // If rightChild is bigger than left
        else if(rightChild.compareTo(currentItem) > 0){
            exchange(itemI, rightChildI);
            sink(rightChildI);
        }
    }

    private void swim(int itemI){
        // If item is greater than parent
        Item item = items.get(itemI);
        int parentI = parent(itemI);
        Item parent =  parentI >= 0 ? items.get(parentI) : null;
        if (parent != null && item.compareTo(parent) > 0){
            exchange(itemI, parentI);
            swim(parentI);
        }
    }

    public String toString(){
        String str = "";
        for (Item i : items) {
            str += i.toString() + ", ";
        }
        return str;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap();
        maxHeap.add(15);
        maxHeap.add(85);
        maxHeap.add(23);
        maxHeap.add(11);
        maxHeap.add(25);
        maxHeap.add(23);
        maxHeap.add(22);
        maxHeap.add(10);
        maxHeap.add(18);
        maxHeap.add(36);

        System.out.println(maxHeap.toString());
        // Should be 85, 36, 23, 18, 25, 23, 22, 10, 11, 15,
        maxHeap.remove();
        System.out.println(maxHeap.toString());
        // Should be 36, 25, 23, 18, 15, 23, 22, 10, 11,
        maxHeap.remove();
        System.out.println(maxHeap.toString());
        // Should be 25, 18, 23, 11, 15, 23, 22, 10,
    }
}
