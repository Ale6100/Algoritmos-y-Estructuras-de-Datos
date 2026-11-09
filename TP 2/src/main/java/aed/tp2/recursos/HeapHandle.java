package aed.tp2.recursos;

import java.util.ArrayList;

public class HeapHandle <T extends Comparable<T>> {

    public class Handle implements Comparable<Handle> {
        private T valor;
        private int pos;
        
        private Handle(T valor, int pos) {
            this.valor = valor;                            // O(1)
            this.pos = pos;                                 
        }
        
        @Override
        public int compareTo(Handle otro) {
            return this.valor.compareTo(otro.valor);            // O(1)
        }
    }
    

    private ArrayList<Handle> heap;
    

    public HeapHandle() {
        this.heap = new ArrayList<>();                    // O(1)
    }
    
    public int tamaño() {
        return this.heap.size();                            // O(1)
    }
    
    public boolean esVacio() {
        return this.heap.isEmpty();                            // O(1)
    }
    
    public Handle maximo() {                                    // O(1)
        if (!esVacio()) {
            return this.heap.get(0);                            // O(1)
        }
        return null;
    }

    

    public Handle encolar(T elem) {
        Handle nuevo = new Handle(elem, this.heap.size());        // O(1)
        this.heap.add(nuevo);                                    // O(1) amortizado
        siftUp(nuevo.pos);                                       // O(log n)
        return nuevo;                                            // O(1)
    }
    
    public T desencolar() {
        Handle maximo = maximo();
        borrar(maximo);                        // O(log n)
        return maximo.valor;
    }
    

    public void borrar(Handle h) {
        int i = h.pos;
        int ultimo = tamaño() -1;
        
        if (i == ultimo) {
            this.heap.remove(ultimo);            // O(1)
        } else {
            swap(i, ultimo);
            this.heap.remove(ultimo);            // O(1)
            siftUp(i);                            // O(log n)
            siftDown(i);                          // O(log n)
        }
    }
    

    public void modificarPrioridad(Handle h, T nuevoValor) {
        T viejoValor = h.valor;
        h.valor = nuevoValor;
        int i = h.pos;
        
        int comp = nuevoValor.compareTo(viejoValor);
        if (comp > 0) {
            siftUp(i);                                // O(log n)
        } else if (comp < 0) {
            siftDown(i);                                // O(log n)
        }
    }
    

    private int padre(int i) {                    // O(1)
        return (i-1)/2;
    }
    
    private int hijoIzq(int i) {                    // O(1)
        return 2*i +1;
    }
    
    private int hijoDer(int i) {                // O(1)
        return 2*i + 2;
    }
    
    private int comparar(int i, int j) {
        return this.heap.get(i).compareTo(this.heap.get(j));        // O(1)
    }
    
    private void swap(int i, int j) {            // O(1)
        Handle hI = this.heap.get(i);
        Handle hJ = this.heap.get(j);
        
        this.heap.set(i, hJ);
        this.heap.set(j, hI);
        
        hI.pos = j;
        hJ.pos = i;
    }
    
    private void siftUp(int i) {
        while (i > 0 && comparar(i, padre(i)) > 0) {     // log n veces (O (log n))
            swap(i, padre(i));
            i = padre(i);
        }
    }
    
    private void siftDown(int i) {
        boolean seCumpleHeap = false;
        while (hijoIzq(i) < this.heap.size() && !seCumpleHeap) {    // O(log n) (a lo sumo log n veces)
            int izq = hijoIzq(i);
            int der = hijoDer(i);
            int mayor = izq;
            
            if (der < this.heap.size() && comparar(der,izq) > 0) {
                mayor = der;
            }
            
            if (comparar(i, mayor) < 0) {
                swap(i, mayor);
                i = mayor;
            } else {
                seCumpleHeap = true;
            }
        }
    }
}