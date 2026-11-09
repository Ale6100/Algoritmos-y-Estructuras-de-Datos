package aed.tp2.recursos;

public class ElementoRanking implements Comparable<ElementoRanking> {
    public int id;
    public double millasDisponibles;
    
    public ElementoRanking (int id, double millasDisponibles) {
        this.id = id;
        this.millasDisponibles = millasDisponibles;
    }
    
    @Override
    public int compareTo(ElementoRanking otro) {
        if (this.millasDisponibles != otro.millasDisponibles) {
            return Double.compare(this.millasDisponibles, otro.millasDisponibles);
        }
        return Integer.compare(otro.id, this.id); // Desempate por ID
    }
}