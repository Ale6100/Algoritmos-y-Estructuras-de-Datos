package aed;
import java.util.ArrayList;

public class SistemaPedidos {
    ArrayList<Handle<Pedido>> pedidosPorId;
    ListaEnlazada<Pedido> pedidosPorLlegada;

    public SistemaPedidos() {
        this.pedidosPorId = new ArrayList<Handle<Pedido>>();
        this.pedidosPorLlegada = new ListaEnlazada<Pedido>(); 
    }

    public void agregarPedido(Pedido pedido){
        Handle<Pedido> hand = this.pedidosPorLlegada.agregarAtras(pedido);
        this.agregarOrdenado(hand);
    }

    private void agregarOrdenado(Handle<Pedido> p) {
        ArrayList<Handle<Pedido>> nuevoArray = new ArrayList<Handle<Pedido>>();

        if (this.pedidosPorId.size() == 0) {
            this.pedidosPorId.add(p);
            return;
        }

        for (int i = 0; i < this.pedidosPorId.size(); i++) {
            Handle<Pedido> elem = this.pedidosPorId.get(i);

            if (nuevoArray.contains(p)) {
                nuevoArray.add(elem);
            } else {
                int compare = p.valor().compareTo(elem.valor());

                if (compare < 0) { // < 0 implica que p.valor es más grande que elem.valor
                    nuevoArray.add(p);
                }

                nuevoArray.add(elem);
            }
        }

        if (!nuevoArray.contains(p)) {
            nuevoArray.add(p);
        }

        this.pedidosPorId = nuevoArray;
    }

    public Pedido proximoPedidoPorId() {
        Handle<Pedido> pedidoRemov = this.pedidosPorId.remove(0);

        pedidoRemov.eliminar();

        return pedidoRemov.valor();
    }

    public Pedido proximoPedidoPorLlegada() {
        Pedido prox = this.pedidosPorLlegada.obtener(0);
        this.pedidosPorLlegada.eliminar(0);

        ArrayList<Handle<Pedido>> nuevoArray = new ArrayList<Handle<Pedido>>();

        for (int i = 0; i <= this.pedidosPorLlegada.longitud(); i++) {
            Handle<Pedido> elem = this.pedidosPorId.get(i);
            
            if (prox.compareTo(elem.valor()) != 0) {
                nuevoArray.add(elem);
            }
        }

        this.pedidosPorId = nuevoArray;

        return prox;
    }

    public Pedido pedidoMenorId(){
        return this.pedidosPorId.get(0).valor();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        return this.pedidosPorLlegada.toString();
    }

    public String obtenerPedidosOrdenadosPorId(){
        return this.pedidosPorId.toString();
    }
}
