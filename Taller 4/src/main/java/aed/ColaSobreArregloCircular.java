package aed;

public class ColaSobreArregloCircular implements Cola {
    private int[] A;
    private int head;
    private int tail;
    private int size;

    public ColaSobreArregloCircular(int capacity) {
        this.A = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    // Inserta en el final (tail)
    public void enqueue(int elem) {
        if (this.A == null) return;
        if (this.isFull()) throw new RuntimeException("enqueue? no");

        this.A[this.tail] = elem;
        this.tail = (this.tail + 1) % this.A.length;
        this.size += 1;
    }

    // Obtiene el elemento del frente (head)
    public int dequeue() {
        if (this.isEmpty()) throw new RuntimeException("dequeue? no");

        int elem = this.A[this.head];
        this.head = (this.head + 1) % this.A.length;
        this.size -= 1;

        return elem;
    }

    // Obtiene el elemento del frente (head)
    public int front() {
        if (this.isEmpty()) throw new RuntimeException("front? no");
        return this.A[this.head];
    }

    // Obtiene el elemento del final (tail)
    public int rear() {
        if (this.isEmpty()) throw new RuntimeException("rear? no");

        int pos = this.tail - 1;
        if (pos < 0) {
            pos = this.A.length - 1;
        }
        return this.A[pos];
    }

    public boolean isEmpty() {
        if (this.A == null) return true;
        if (this.size == 0) return true;
        return false;
    }

    public boolean isFull() {
        if (this.A == null) return false;
        if (this.size == this.A.length) return true;
        return false;
    }
}
