package aed;

public class PilaSobreArreglo implements Pila {
    private int[] A;
    private int top;

    public PilaSobreArreglo(int capacity) {
        this.A = new int[capacity];
        this.top = -1;
    }

    public void push(int elem) {
        if (this.A == null) return;
        if (this.A.length == this.top) throw new RuntimeException("push? no");

        this.A[this.top+1] = elem;
        this.top += 1;
    }

    public int pop() {
        if (this.A == null) return 0;
        if (this.A.length <= 0) throw new RuntimeException("pop? no");

        int elem = this.A[this.top];
        this.top -= 1;
        return elem;
    }

    public int top() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public boolean isEmpty() {
        if (this.A == null) return true;
        if (this.top == -1) return true;
        return false;
    }

    public boolean isFull() {
        if (this.A == null) return false;
        if (this.top == this.A.length-1) return true;
        return false;
    }
}
