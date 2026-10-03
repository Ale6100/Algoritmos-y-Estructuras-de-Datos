package aed;

public class ColaSobreListaEnlazada implements Cola {
    private Node head;
    private Node tail;

    public ColaSobreListaEnlazada() {
        //
    }

    public void enqueue(int elem) {
        Node nuevo = new Node(elem);
        if (this.tail == null) {
            this.head = nuevo;
            this.tail = nuevo;
            return;
        }

        this.tail.next = nuevo;
        this.tail = nuevo;
    }

    public int dequeue() {
        if (this.head == null) throw new RuntimeException("dequeue? no");
        int cabeza = this.head.data;

        this.head = this.head.next;

        return cabeza;
    }

    public int front() {
        return this.head.data;
    }

    public int rear() {
        return this.tail.data;
    }

    public boolean isEmpty() {
        if (this.head == null) return true;
        return false;
    }

    public boolean isFull() {
        return false;
    }
}
