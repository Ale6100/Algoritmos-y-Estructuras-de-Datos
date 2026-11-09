package aed.tp2.recursos;

public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) { // O(1)
            this.valor = v;     // O(1)
            this.izq = null;    // O(1)
            this.der = null;    // O(1)
            this.padre = null;  // O(1)
        }
    }

    public ABB() {  // O(1)
        this._raiz = null;  // O(1)
        this._cardinal = 0; // O(1)
    }

    public int cardinal() { // O(1)
        return this._cardinal;  // O(1)
    }

    public T minimo() { // O(log n) - O(n) (caso promedio - peor caso, donde n es la cardinalidad)
        Nodo puntero = this._raiz;                  // O(1)

        while (this.puedoIrALaIzquierda(puntero)) { // log n * O(1) = O(log n) - n * O(1) = O(n)
            puntero = this.voyALaIzquierda(puntero);    // O(1)
        }

        return puntero.valor;                       // O(1)
    }

    public T maximo() { // O(log n) - O(n)
        Nodo puntero = this._raiz;                  // O(1)

        while (this.puedoIrALaDerecha(puntero)) {   // log n * O(1) = O(log n) - n * O(1) = O(n)
            puntero = this.voyALaDerecha(puntero);      // O(1)
        }

        return puntero.valor;                       // O(1)
    }

    public void insertar(T elem) {  // O(log n) - O(n)
        Nodo puntero = this._raiz;                                                                      // O(1)

        if (puntero == null) {                                                                          // O(1)
            this._raiz = new Nodo(elem);                                                                // O(1)
            this._cardinal = 1;                                                                         // O(1)
            return;                                                                                     // O(1)
        }

        int i = 0;                                                                                      // O(1)
        while (i < this.cardinal() && this.puedoAvanzar(elem, puntero) && !this.sonIguales(puntero.valor, elem)) { // log n * O(1) = O(log n) - n * O(1) = O(n)
            if (this.quieroIrALaDerecha(elem, puntero)) {       // O(1)
                puntero = this.voyALaDerecha(puntero);          // O(1)
            } else {                                            // O(1)
                puntero = this.voyALaIzquierda(puntero);        // O(1)
            }
            i++;                                                // O(1)
        }

        if (this.sonIguales(puntero.valor, elem)) {                                                     // O(1)
            return;                                                                                     // O(1)
        }

        Nodo nuevoNodo = new Nodo(elem);                                                                // O(1)
        nuevoNodo.padre = puntero;                                                                      // O(1)

        if (this.quieroIrALaDerecha(elem, puntero)) {                                                   // O(1)
            puntero.der = nuevoNodo;                                                                    // O(1)
        } else {
            puntero.izq = nuevoNodo;                                                                    // O(1)
        }

        this._cardinal+= 1;                                                                             // O(1)
    }

    private boolean puedoAvanzar(T elem, Nodo puntero) {    // O(1)
        if (this.quieroIrALaDerecha(elem, puntero)) {   // O(1)
            return this.puedoIrALaDerecha(puntero);     // O(1)
        }
        return this.puedoIrALaIzquierda(puntero);       // O(1)
    }

    private boolean esHoja(Nodo n) {    // O(1)
        return n.izq == null && n.der == null;  // O(1) + O(1) = O(1)
    }

    private boolean quieroIrALaDerecha(T elem, Nodo puntero) {
        return elem.compareTo(puntero.valor) > 0;
    }

    private boolean puedoIrALaDerecha(Nodo puntero) { // O(1)
        return puntero.der != null; // O(1)
    }

    private Nodo voyALaDerecha(Nodo puntero) {  // O(1)
        return puntero.der; // O(1)
    }

    private boolean puedoIrALaIzquierda(Nodo puntero) { // O(1)
        return puntero.izq != null; // O(1)
    }

    private Nodo voyALaIzquierda(Nodo puntero) { // O(1)
        return puntero.izq; // O(1)
    }

    private Nodo obtenerNodo(T elem) { // O(log n) - O(n)
        Nodo puntero = this._raiz;                                                                                          // O(1)

        if (puntero == null) {                                                                                              // O(1)
            return null;                                                                                                    // O(1)
        }

        int i = 0;                                                                                                          // O(1)

        while (i < this.cardinal() && puntero != null && !this.sonIguales(puntero.valor, elem) && !this.esHoja(puntero)) {  // log n * O(1) = O(log n) - n * O(1) = O(n)
            if (this.quieroIrALaDerecha(elem, puntero)) {   // O(1)
                puntero = this.voyALaDerecha(puntero);      // O(1)
            } else {
                puntero = this.voyALaIzquierda(puntero);    // O(1)
            }
            i++;                                            // O(1)
        }

        boolean llegoALNodoYTieneValor = puntero != null && this.sonIguales(puntero.valor, elem);                           // O(1)

        if (llegoALNodoYTieneValor) {                                                                                       // O(1)
            return puntero;                                                                                                 // O(1)
        }

        return null;                                                                                                        // O(1)
    }

    public T devolver(T elem) { // O(log n) - O(n)
        Nodo puntero = this.obtenerNodo(elem);  // O(log n) - O(n)

        if (puntero == null) {                  // O(1)
            return null;                        // O(1)
        }

        return puntero.valor;                   // O(1)
    }

    private boolean sonIguales(T a, T b) {  // O(1)
        return a.compareTo(b) == 0; // O(1)
    }

    public boolean pertenece(T elem) { // O(log n) - O(n)
        Nodo puntero = this.obtenerNodo(elem);          // O(log n) - O(n)

        if (puntero == null || puntero.valor == null) { // O(1) + O(1) = O(1)
            return false;                               // O(1)
        }

        return true;                                    // O(1)
    }

    private boolean soyHijoIzquierdo(Nodo puntero) {    // O(1)
        return puntero.padre.valor.compareTo(puntero.valor) > 0;    // O(1)
    }

    private boolean soyHijoDerecho(Nodo puntero) {
        if (puntero.padre == null) {
            return false;
        }
        return puntero.padre.valor.compareTo(puntero.valor) < 0;
    }

    private boolean soyRaiz(Nodo puntero) { // O(1)
        return puntero.padre == null;   // O(1)
    }

    private void eliminarHoja(Nodo puntero) {   // O(1)
        if (this.soyRaiz(puntero)) {                    // O(1)
            this._raiz = null;                          // O(1)
        } else if (this.soyHijoIzquierdo(puntero)) {    // O(1)
            puntero.padre.izq = null;                   // O(1)
        } else {
            puntero.padre.der = null;                   // O(1)
        }
        this._cardinal -= 1;                            // O(1)
    }

    private Nodo puntoMinimoSucesorDerecho(Nodo puntero) {  // O(log n) - O(n)
        Nodo punteroMinimoSucesorDerecho = puntero.der;                 // O(1)

        while(this.puedoIrALaIzquierda(punteroMinimoSucesorDerecho)) {  // log n * O(1) = O(log n) - n * O(1) = O(n)
            punteroMinimoSucesorDerecho = this.voyALaIzquierda(punteroMinimoSucesorDerecho);    // O(1)
        }

        return punteroMinimoSucesorDerecho;                             // O(1)
    }

    public void eliminar(T elem){   // O(log n) - O(n)
        Nodo puntero = this.obtenerNodo(elem);                                      // O(log n) - O(n)

        if (puntero == null) {                                                      // O(1)
            return;                                                                 // O(1)
        }

        // Caso 1/3: es hoja
        if (this.esHoja(puntero)) {                                                 // O(1)
            this.eliminarHoja(puntero);                                             // O(1)
            return;                                                                 // O(1)
        }

        boolean pID = this.puedoIrALaDerecha(puntero);                              // O(1)
        boolean pII = this.puedoIrALaIzquierda(puntero);                            // O(1)

        // Caso 2/3: tiene un hijo
        if ((pID && !pII) || (!pID && pII)) {                                       // O(1) + O(1) + O(1) + O(1) = O(1)
            if (pID) {                                                              // O(1)
                if (this.soyRaiz(puntero)) {                                        // O(1)
                    puntero.der.padre = null;                                       // O(1)
                    this._raiz = puntero.der;                                       // O(1)
                    this._cardinal -= 1;                                            // O(1)
                    return;                                                         // O(1)
                }

                if (this.soyHijoIzquierdo(puntero)) {                               // O(1)
                    puntero.padre.izq = puntero.der;                                // O(1)
                    puntero.der.padre = puntero.padre;                              // O(1)
                    this._cardinal -= 1;                                            // O(1)
                    return;                                                         // O(1)
                }

                puntero.padre.der = puntero.der;                                    // O(1)
                puntero.der.padre = puntero.padre;                                  // O(1)
                this._cardinal -= 1;                                                // O(1)
                return;                                                             // O(1)
            }

            if (this.soyRaiz(puntero)) {                                            // O(1)
                puntero.izq.padre = null;                                           // O(1)
                this._raiz = puntero.izq;                                           // O(1)
                this._cardinal -= 1;                                                // O(1)
                return;                                                             // O(1)
            }

            if (this.soyHijoIzquierdo(puntero)) {                                   // O(1)
                puntero.padre.izq = puntero.izq;                                    // O(1)
                puntero.izq.padre = puntero.padre;                                  // O(1)
                this._cardinal -= 1;                                                // O(1)
                return;                                                             // O(1)
            }

            puntero.padre.der = puntero.izq;                                        // O(1)
            puntero.izq.padre = puntero.padre;                                      // O(1)
            this._cardinal -= 1;                                                    // O(1)
            return;                                                                 // O(1)
        }

        // Caso 3/3: tiene dos hijos
        Nodo punteroMinimoSucesorDerecho = this.puntoMinimoSucesorDerecho(puntero); // O(log n) - O(n)
        T valorSucesor = punteroMinimoSucesorDerecho.valor;                         // O(1)

        this.eliminar(valorSucesor);                                                // O(log n) - O(n) (entra al caso 1 o 2, ambas con complejidad O(log n) - O(n))

        puntero.valor = valorSucesor;                                               // O(1)
    }
    
    public class ABB_Iterador {
        private Nodo _actual;

        public ABB_Iterador() { // O(log n) - O(n)
            if (ABB.this.cardinal() == 0) {                                                     // O(1)
                this._actual = null;                                                            // O(1)
                return;                                                                         // O(1)
            }

            Nodo puntero = ABB.this._raiz;                                                      // O(1)
            while (ABB.this.puedoIrALaIzquierda(puntero)) {                                     // O(log n) - O(n)
                puntero = ABB.this.voyALaIzquierda(puntero);    // O(1)
            }
            this._actual = puntero;                                                             // O(1)
        }

        public boolean haySiguiente() { // O(1)
            return this._actual != null;                                                        // O(1)
        }

        private Nodo minimoMasGrandeQue(Nodo puntero) { // O(log n) - O(n)
            if (ABB.this.puedoIrALaDerecha(puntero)) {                                          // O(1)
                return ABB.this.puntoMinimoSucesorDerecho(puntero);                             // O(log n) - O(n)
            }

            while (ABB.this.soyHijoDerecho(puntero)) {                                          // O(log n) - O(n)
                puntero = puntero.padre;    // O(1)
            }

            return puntero.padre;                                                               // O(1)
        }

        public T siguiente() {  // O(log n) - O(n)
            T valorActual = this._actual.valor;                                                 // O(1)
            this._actual = this.minimoMasGrandeQue(this._actual);                               // O(log n) - O(n)
            return valorActual;                                                                 // O(1)
        }
    }

    public ABB_Iterador iterador() {    // O(n)
        return new ABB_Iterador();
    }
}
