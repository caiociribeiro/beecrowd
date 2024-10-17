import java.util.Scanner;

class CustomPriorityQueue<K extends Comparable<K>, V> {

    private Node<K, V> head;
    private Node<K, V> tail;
    private int size;

    public CustomPriorityQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public V getValue(int pos) {
        return node(pos).value;
    }

    private Node<K, V> node(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<K, V> node;
        if (size - pos >= pos) {
            node = head;
            for (int i = 0; i < pos; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > pos; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    public void insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            int pos = size;
            Node<K, V> p = head;

            for (int i = 0; i < size; i++) {
                if (key.compareTo(p.key) > 0) {
                    pos = i;
                    break;
                }
                p = p.next;
            }

            if (pos == 0) {
                newNode.next = head;

                head.prev = newNode;
                head = newNode;
            } else if (pos == size) {
                newNode.prev = tail;

                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.next = p;
                newNode.prev = p.prev;

                p.prev.next = newNode;
                p.prev = newNode;
            }

        }

        size++;

    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<K, V> p = head; p != null; p = p.next) {
            sb.append(p.key).append(" -> ").append(p.value).append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            CustomPriorityQueue<Integer, Integer> pq = new CustomPriorityQueue<>();

            int m = in.nextInt();
            for (int j = 0; j < m; j++) {
                int p = in.nextInt();
                pq.insert(p, j);
            }

            int c = m;
            for (int j = 0; j < m; j++) {
                if (pq.getValue(j) != j) {
                    c--;
                }
            }

            System.out.println(c);
        }
    }
}
