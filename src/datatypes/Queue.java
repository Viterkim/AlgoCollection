package datatypes;

import java.util.Iterator;
import java.util.NoSuchElementException;

// A queue is a first in, first out. (The first element in the queue, is the first to go out as well)
public class Queue<Item> implements Iterable<Item>{
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        public Node(Item item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Queue(){
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return first.item;
    }

    public void enqueue(Item item){
        if (last == null) {
            first = last = new Node(item, null);
        } else {
            Node newNode = new Node(item, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        System.out.println(first.item);
        Item item = first.item;
        first = first.next;
        System.out.println(first.item);
        size--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator()  {
        return new ListIterator(first);
    }

    public static void main(String[] args) {
        Queue queue = new Queue<Integer>();
        queue.enqueue(2);
        System.out.println(queue.dequeue());
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(1);
        System.out.println(queue.toString());
    }
}
