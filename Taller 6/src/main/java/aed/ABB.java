package aed;
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) {
            this.valor = v;
            this.izq = null;
            this.der = null;
            this.padre = null;
        }
    }

    public ABB() {
        this._raiz = null;
        this._cardinal = 0;
    }

    public int cardinal() {
        return this._cardinal;
    }

    public T minimo() {
        Nodo puntero = this._raiz;

        while (puntero.izq != null) {
            puntero = puntero.izq;
        }

        return puntero.valor;
    }

    private T minimoMasGrandeQue(T elem) {
        Nodo puntero = this.devolver(elem);

        if (this.puedoIrALaDerecha(puntero)) {
            Nodo punteroMinimoSucesorDerecho = this.puntoMinimoSucesorDerecho(puntero);
            return punteroMinimoSucesorDerecho.valor;
        }

        while (this.soyHijoDerecho(puntero)) {
            puntero = puntero.padre;
        }

        if (this.soyHijoIzquierdo(puntero)) {
            puntero = puntero.padre;
        }

        return puntero.valor;
    }

    public T maximo() {
        Nodo puntero = this._raiz;

        while (puntero.der != null) {
            puntero = puntero.der;
        }

        return puntero.valor;
    }

    public void insertar(T elem) {
        if (this.pertenece(elem)) return;

        Nodo puntero = this._raiz;

        if (puntero == null) {
            this._raiz = new Nodo(elem);
            this._cardinal = 1;
            return;
        }

        while(true) {
            if (this.quieroIrALaDerecha(elem, puntero)) {
                if (this.puedoIrALaDerecha(puntero)) {
                    puntero = this.voyALaDerecha(puntero);
                    continue;
                }

                Nodo nuevoNodo = new Nodo(elem);
                nuevoNodo.padre = puntero;

                puntero.der = nuevoNodo;

                this._cardinal+= 1;
                return;
            }

            if (this.puedoIrALaIzquierda(puntero)) {
                puntero = this.voyALaIzquierda(puntero);
                continue;
            }

            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.padre = puntero;

            puntero.izq = nuevoNodo;

            this._cardinal+= 1;
            return;
        }
    }

    private boolean esHoja(Nodo n) {
        return n.izq == null && n.der == null;
    }

    private boolean quieroIrALaDerecha (T elem, Nodo puntero) {
        int compare = elem.compareTo(puntero.valor);

        return compare > 0;
    }

    private boolean puedoIrALaDerecha(Nodo puntero) {
        return puntero.der != null;
    }

    private Nodo voyALaDerecha(Nodo puntero) {
        return puntero.der;
    }

    private boolean puedoIrALaIzquierda(Nodo puntero) {
        return puntero.izq != null;
    }

    private Nodo voyALaIzquierda(Nodo puntero) {
        return puntero.izq;
    }

    private Nodo devolver(T elem) {
        Nodo puntero = this._raiz;

        while(puntero.valor.compareTo(elem) != 0) {
            if (this.quieroIrALaDerecha(elem, puntero)) {
                puntero = this.voyALaDerecha(puntero);
                continue;
            }

            puntero = this.voyALaIzquierda(puntero);
        }

        return puntero;
    }

    public boolean pertenece(T elem) {
        Nodo puntero = this._raiz;

        if (puntero == null) return false;

        while (!this.esHoja(puntero)) {
            if (puntero.valor.compareTo(elem) == 0) return true;

            if (this.quieroIrALaDerecha(elem, puntero)) {
                if (this.puedoIrALaDerecha(puntero)) {
                    puntero = this.voyALaDerecha(puntero);
                    continue;
                }

                return false;
            }

            if (this.puedoIrALaIzquierda(puntero)) {
                puntero = this.voyALaIzquierda(puntero);
                continue;
            }

            return false;
        }

        return puntero.valor.compareTo(elem) == 0;
    }

    boolean soyHijoIzquierdo(Nodo puntero) {
        if (puntero == null) return false;
        if (puntero.padre == null) return false;
        return puntero.padre.valor.compareTo(puntero.valor) > 0;
    }

    boolean soyHijoDerecho(Nodo puntero) {
        if (puntero == null) return false;
        if (puntero.padre == null) return false;
        return puntero.padre.valor.compareTo(puntero.valor) < 0;
    }

    boolean soyRaiz(Nodo puntero) {
        return puntero.padre == null;
    }

    private void eliminarHoja(Nodo puntero) {
        if (this.soyRaiz(puntero)) {
            this._raiz = null;
        } else if (this.soyHijoIzquierdo(puntero)) {
            puntero.padre.izq = null;
        } else {
            puntero.padre.der = null;
        }
        this._cardinal -= 1;
    }

    private Nodo puntoMinimoSucesorDerecho(Nodo puntero) {
        Nodo punteroMinimoSucesorDerecho = puntero.der;

        while(this.puedoIrALaIzquierda(punteroMinimoSucesorDerecho)) {
            punteroMinimoSucesorDerecho = this.voyALaIzquierda(punteroMinimoSucesorDerecho);
        }

        return punteroMinimoSucesorDerecho;
    }

    public void eliminar(T elem){
        if (!this.pertenece(elem)) return;

        Nodo puntero = this._raiz;

        while(puntero.valor.compareTo(elem) != 0) {
            if (this.quieroIrALaDerecha(elem, puntero)) {
                puntero = this.voyALaDerecha(puntero);
                continue;
            }

            puntero = this.voyALaIzquierda(puntero);
        }

        // Caso 1/3: es hoja
        if (this.esHoja(puntero)) {
            this.eliminarHoja(puntero);
            return;
        }

        boolean pID = this.puedoIrALaDerecha(puntero);
        boolean pII = this.puedoIrALaIzquierda(puntero);

        // Caso 2/3: tiene un hijo
        if ((pID && !pII) || (!pID && pII)) {
            if (pID) {
                if (this.soyRaiz(puntero)) {
                    puntero.der.padre = null;
                    this._raiz = puntero.der;
                    this._cardinal -= 1;
                    return;
                }

                if (this.soyHijoIzquierdo(puntero)) {
                    puntero.padre.izq = puntero.der;
                    puntero.der.padre = puntero.padre;
                    this._cardinal -= 1;
                    return;
                }

                puntero.padre.der = puntero.der;
                puntero.der.padre = puntero.padre;
                this._cardinal -= 1;
                return;
            }

            if (this.soyRaiz(puntero)) {
                puntero.izq.padre = null;
                this._raiz = puntero.izq;
                this._cardinal -= 1;
                return;
            }

            if (this.soyHijoIzquierdo(puntero)) {
                puntero.padre.izq = puntero.izq;
                puntero.izq.padre = puntero.padre;
                this._cardinal -= 1;
                return;
            }

            puntero.padre.der = puntero.izq;
            puntero.izq.padre = puntero.padre;
            this._cardinal -= 1;
            return;
        }

        // Caso 3/3: tiene dos hijos
        Nodo punteroMinimoSucesorDerecho = this.puntoMinimoSucesorDerecho(puntero);
        T valorSucesor = punteroMinimoSucesorDerecho.valor;

        this.eliminar(valorSucesor);

        puntero.valor = valorSucesor;
    }

    public String toString(){
        if (this._raiz == null) return "{}";

        String res = "{";

        ABB_Iterador iterador = this.iterador();

        for(int i = 1; i <= this._cardinal; i++) {
            res += iterador.siguiente();

            if (i != this._cardinal) {
                res += ",";
            }
        }

        return res + "}";
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public ABB_Iterador() {
            if (ABB.this._cardinal == 0) {
                this._actual = null;
                return;
            }
            this._actual = ABB.this.devolver(ABB.this.minimo());
        }

        // public boolean haySiguiente() {
        //     //
        // }

        public T siguiente() {
            T valorActual = this._actual.valor;
            this._actual = ABB.this.devolver(ABB.this.minimoMasGrandeQue(this._actual.valor));
            return valorActual;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }
}
