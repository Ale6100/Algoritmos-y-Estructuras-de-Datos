package aed.tp2.recursos;

public interface Conjunto<T> {

    public int cardinal();

    public void insertar(T elem);

    public boolean pertenece(T elem);

    public void eliminar(T elem);

}
