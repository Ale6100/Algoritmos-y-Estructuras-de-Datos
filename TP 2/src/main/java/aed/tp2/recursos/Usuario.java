package aed.tp2.recursos;

public class Usuario implements Comparable<Usuario> {
    public int id;
    public double millasTotales;
    public double millasPerdidas;
    public double millasDisponibles;
    public int tokenTransferidor;

    public Usuario(int id) {    // O(1)
        this.id = id;               // O(1)
        this.millasTotales = 0;     // O(1)
        this.millasPerdidas = 0;    // O(1)
        this.millasDisponibles = 0; // O(1)
        this.tokenTransferidor = 0; // O(1)
    }

    public double factorCategoria() {   // O(1)
        if (0 <= this.millasTotales && this.millasTotales < 10000) {                // O(1)
            return 1.0;                                                             // O(1)
        } else if (10000 <= this.millasTotales && this.millasTotales < 50000) {     // O(1)
            return 1.5;                                                             // O(1)
        } else if (50000 <= this.millasTotales && this.millasTotales < 1000000) {   // O(1)
            return 2.0;                                                             // O(1)
        } else if (this.millasTotales >= 1000000) {                                 // O(1)
            return 0.0;                                                             // O(1)
        }
        return 0.0;     // Esto va porque sino Java chilla si no devuelve un double // O(1)
    }
    
    public String nombreCategoria() {   // O(1)
        if (0 <= this.millasTotales && this.millasTotales < 10000) {                // O(1)
            return "Bronce";                                                             // O(1)
        } else if (10000 <= this.millasTotales && this.millasTotales < 50000) {     // O(1)
            return "Plata";                                                             // O(1)
        } else if (50000 <= this.millasTotales && this.millasTotales < 1000000) {   // O(1)
            return "Oro";                                                             // O(1)
        } else if (this.millasTotales >= 1000000) {                                 // O(1)
            return "Platino";                                                             // O(1)
        }                                                             // O(1)
        return "Platino"; // Esto va porque sino Java chilla si no devuelve un string // O(1)
    }

    public int compareTo(Usuario otroUsuario) { // O(1)
        return this.id - otroUsuario.id; // O(1)
    }
}
