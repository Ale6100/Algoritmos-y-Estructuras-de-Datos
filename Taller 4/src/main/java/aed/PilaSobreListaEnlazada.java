package aed;

public class PilaSobreListaEnlazada implements Pila {
    private Node nodo;

    public PilaSobreListaEnlazada() {
        //
    }

    public void push(int elem) {
        Node nuevo = new Node(elem);
        nuevo.next = this.nodo;
        this.nodo = nuevo;
    }

    public int pop() {
        if (this.nodo == null) throw new RuntimeException("pop? no");
        int ultimo = this.nodo.data;

        this.nodo = this.nodo.next;

        return ultimo;
    }

    public int top() {
        return this.nodo.data;
    }

    public boolean isEmpty() {
        if (this.nodo == null) return true;
        return false;
    }

    public boolean isFull() {
        return false;
    }
}
