package aed.tp2.recursos;

import java.util.ArrayList;

// Métodos:

// Insertar     -> Si insertás un valor en un nodo que ya tiene valor, se ignora
// Obtener
// Eliminar     -> Hace que el valor pase a ser null
// Pertenece

public class Trie<T> {
    private Nodo _raiz;

    private class Nodo {
        T valor;
        ArrayList<Nodo> hijos;

        Nodo(T v) { // O(1)
            this.valor = v;     // O(1)
            this.hijos = null;  // O(1)
        }
    }

    public Trie() { // O(1)
        Nodo nuevoNodo = new Nodo(null);                        // O(1)
        nuevoNodo.hijos = this.crearArrayDeHijos();             // O(1)
        this._raiz = nuevoNodo;                                 // O(1)
    }

    public void insertar(String clave, T elem) { // O(|L|)
        Nodo puntero = this._raiz;      // O(1)

        int i = 0;                      // O(1)
        while (i < clave.length()) {    // |L| * O(1) = O(|L|)
            int indiceASCII = clave.charAt(i);                          // O(1)

            if (!this.tieneHijos(puntero)) {                            // O(1)
                puntero.hijos = this.crearArrayDeHijos();               // O(1)
            }

            puntero = puntero.hijos.get(indiceASCII);                   // O(1)
            i++;                                                        // O(1)
        }

        if (puntero.valor == null) {    // O(1)
            puntero.valor = elem;       // O(1)
        }
    }

    public T obtener(String clave) { // O(|L|)
        Nodo puntero = this.obtenerNodo(clave); // O(|L|)

        if (puntero == null) {                  // O(1)
            return null;                        // O(1)
        }
        return puntero.valor;                   // O(1)
    }

    public void eliminar(String clave) { // O(|L|)
        Nodo puntero = this.obtenerNodo(clave); // O(|L|)

        if (puntero == null) {                  // O(1)
            return;                             // O(1)
        }

        puntero.valor = null;                   // O(1)
    }

    public boolean pertenece(String clave) { // O(|L|)
        Nodo puntero = this.obtenerNodo(clave);         // O(|L|)

        if (puntero == null || puntero.valor == null) { // O(1)
            return false;                               // O(1)
        }

        return true;                                    // O(1)
    }

    private boolean tieneHijos(Nodo puntero) { // O(1)
        return puntero.hijos != null;   // O(1)
    }

    private Nodo obtenerNodo(String clave) { // O(|L|)
        Nodo puntero = this._raiz;                                      // O(1)

        int i = 0;                                                      // O(1)
        while (i < clave.length() && this.tieneHijos(puntero)) {        // |L| * O(1) = O(|L|)
            int indiceASCII = clave.charAt(i);          // O(1)

            puntero = puntero.hijos.get(indiceASCII);   // O(1)
            i++;                                        // O(1)
        }

        boolean llegoALaClave = i == clave.length();                    // O(1)
        boolean tieneValor = puntero != null && puntero.valor != null;  // O(1)

        if (llegoALaClave && tieneValor) {                              // O(1)
            return puntero;                                             // O(1)
        }

        return null;                                                    // O(1)
    }

    private ArrayList<Nodo> crearArrayDeHijos() { // O(1)
        ArrayList<Nodo> hijos = new ArrayList<Nodo>(256);    // O(1)
        for (int i = 0; i < 256; i++) {                      // 256 * O(1) = O(1)
            Nodo nodoCaracter = new Nodo(null);    // O(1)
            hijos.add(nodoCaracter);               // O(1)
        }
        return hijos;                                        // O(1)
    }
}
