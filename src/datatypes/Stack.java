package datatypes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

// A stack is last in, first out. (The last element you put on the stack, is the first out).
public class Stack<Item> implements Iterable<Item>{

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    private Node<Item> first;
    private int size;


    public Stack() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void push(Item item){
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public Item pop(){
        if (isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        Item itemInNode = first.item;
        // Set first element in stack to the next one
        first = first.next;
        size--;
        return itemInNode;
    }

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Stack is empty");
        }
        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    @Override
    public void forEach(Consumer<? super Item> action) {

    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }

}
