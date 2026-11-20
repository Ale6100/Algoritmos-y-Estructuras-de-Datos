package aed.tp2.recursos;

// Ver si falta/sobra alguno
public interface Diccionario<K, T> {
    public int cardinal();

    public void insertar(K clave, T valor);

    public boolean pertenece(K clave);

    public void eliminar(K clave);

    // public T obtener(K clave);
}
