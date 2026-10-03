package aed;

public class ColaSobreArregloCircular implements Cola {
    private int[] A;
    private int end;

    public ColaSobreArregloCircular(int i) {
        this.A = new int[i];
        this.end = -1;
    }

    // Inserta en el final (tail)
    public void enqueue(int elem) {
        if (this.A == null) return;
        if (this.A.length == this.end) throw new RuntimeException("enqueue? no");

        this.A[this.end+1] = elem;
        this.end += 1;
    }

    // Obtiene el elemento del frente (head)
    public int dequeue() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    // Obtiene el elemento del frente (head)
    public int front() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    // Obtiene el elemento del final (tail)
    public int rear() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("No implementada aun");
    }
}
