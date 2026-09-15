package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo ant;
        Nodo sig;

        Nodo(T v) {
            valor = v;
        }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public int longitud() {
        if (this.primero == null || this.ultimo == null) {
            return 0;
        }

        int contador = 0;
        Nodo actual = this.primero;
        while (actual != null) {
            actual = actual.sig;
            contador++;
        }

        return contador;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (this.longitud() == 0) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            Nodo actual = this.ultimo;
            while (actual.ant != null) {
                actual = actual.ant;
            }
            actual.ant = nuevo;
            nuevo.sig = actual;
            this.primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);

        if (this.longitud() == 0) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            Nodo actual = this.primero; // left to right
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;
            nuevo.ant = actual;
            this.ultimo = nuevo;
        }
    }

    public T obtener(int i) {
        if (this.longitud() == 0) {
            return null;
        }

        if (i < 0 || i >= this.longitud() ) {
            return null;
        }

        int indice = 0;
        Nodo actual = this.primero;
        while (indice != i) {
            actual = actual.sig;
            indice++;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        if (this.longitud() == 0) {
            return;
        }

        if (i < 0 || i >= this.longitud() ) {
            return;
        }

        Nodo actual = this.primero;
        Nodo prev = this.primero;

        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sig;
        }

        if (i == 0) {
            this.primero = actual.sig;
        } else {
            prev.sig = actual.sig;
            actual.sig = prev.sig;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        if (this.longitud() == 0) {
            return;
        }

        if (indice < 0 || indice >= this.longitud() ) {
            return;
        }

        Nodo nuevo = new Nodo(elem);

        Nodo actual = this.primero;
        Nodo prev = this.primero;

        for (int j = 0; j < indice; j++) {
            prev = actual;
            actual = actual.sig;
        }

        if (indice == 0) {
            nuevo.sig = actual.sig;
            this.primero = nuevo;
        } else {
            nuevo.ant = actual.ant;
            nuevo.sig = actual.sig;

            prev.sig = nuevo;
            actual.sig.ant = nuevo;
        }
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        for (int i = 0; i < lista.longitud(); i++) {
            this.agregarAtras(lista.obtener(i));
        }
    }

    @Override
    public String toString() {
        if (this.longitud() == 0) {
            return "";
        }

        String str = "[";

        Nodo actual = this.primero;
        for (int i = 0; i < this.longitud(); i++) {
            str+= actual.valor;
            if (i != this.longitud() - 1) {
                str+= ", ";
            }
            actual = actual.sig;
        }

        return str + "]";
    }

    public class ListaIterador{
        int dedito;

        public ListaIterador() {
            this.dedito = 0;
        }

        public boolean haySiguiente() {
            return this.dedito < ListaEnlazada.this.longitud();
        }

        public boolean hayAnterior() {
            return this.dedito > 0;
        }

        public T siguiente() {
            int i = this.dedito;
            this.dedito+= 1;
            return ListaEnlazada.this.obtener(i);
        }


        public T anterior() {
            int i = this.dedito;
            this.dedito-= 1;
            return ListaEnlazada.this.obtener(i-1);
        }
    }

    public ListaIterador iterador() {
	    return new ListaIterador();
    }
}
