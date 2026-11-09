package aed.tp2.recursos;

public class NodoHandle implements Comparable<NodoHandle> {
    public int id;
    public HeapHandle<ElementoRanking>.Handle handle;
    
    public NodoHandle(int id, HeapHandle<ElementoRanking>.Handle handle) {
        this.id = id;
        this.handle = handle;
    }
    
    @Override
    public int compareTo(NodoHandle otro) {
        return Integer.compare(this.id, otro.id);
    }
}