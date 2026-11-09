package aed.tp2;

import aed.tp2.recursos.*;
import java.util.ArrayList;

public class AirMiles implements SistemaAirMiles {
    
    // private ABB<ID:int, valoresUsuario: (millasTotales: double, millasPerdidas: double, millasDisponibles: double, token:int)> usuarios;
    
    // private Trie<nombrePromo:String, valoresPromo: (factor:double, tope:double)> promos;
    
    private ABB<Usuario> usuarios;
    
    private Trie<Promocion> promos;
    
    // Se van actualizando por inserción en cada proc correspondiente, por ejemplo con una transferencia o acumulación se pregunta si supera al mas acumulador y en caso afirmativo se reemplaza
    private int elMasCanjeador;
    private int elMasAcumulador;
    
    private HeapHandle<ElementoRanking> ranking; // Ordenado por cantidad de millas disponibles
    private ABB<NodoHandle> handlesRanking;
    

    public AirMiles() {
        // Inicializa el sistema en O(1)
        this.usuarios = new ABB<Usuario>();                                            // O(1)
        this.ranking = new HeapHandle<ElementoRanking>();                                         // O(1)
        this.elMasCanjeador = -1;                                                      // O(1)
        this.elMasAcumulador = -1;                                                     // O(1)
        
        this.promos = new Trie<Promocion>();                                      // O(1)
        this.promos.insertar("NoPromo", new Promocion(1, -1));                    // O(|"NoPromo"| = 7) = O(1)
        
        this.handlesRanking = new ABB<NodoHandle>();
    }
    

    public void registrarUsuario(int id) {
        // Registra el usuario en O(log(U)) en promedio y O(U) en peor caso si solo hay una rama.
        // Requiere id > 0 y no registrado
        Usuario nuevo = new Usuario(id);                                          // O(1)
        
        this.usuarios.insertar(nuevo);                                                 // O(log U)
        ElementoRanking elemRank = new ElementoRanking(id, 0);                        // O(1)
        HeapHandle<ElementoRanking>.Handle h = this.ranking.encolar(elemRank);         // O(log U)
        this.handlesRanking.insertar(new NodoHandle(id, h));                           // O (log U) 
        
        if (this.elMasAcumulador == -1) {                                             // O(1)
            this.elMasAcumulador = id;
        }
        
        if (this.elMasCanjeador == -1) {                                             // O(1)
            this.elMasCanjeador = id;
        }
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        // Registra el usuario en O(log(U)) en promedio y O(U) en peor caso
        // Agrega las 500 millas a cada uno
        Usuario nuevo = new Usuario(id);                                          // O(1)
        Usuario referidor = this.devolverUsuario(ref);                   // O(log U)
        
        this.usuarios.insertar(nuevo);                                // O(log U)

        nuevo.millasTotales = 500;                                                 // O(1)
        nuevo.millasDisponibles = 500;                                             // O(1)
        referidor.millasTotales += 500;                                             // O(1)
        referidor.millasDisponibles += 500;                                         // O(1)
        
        HeapHandle<ElementoRanking>.Handle hRef = obtenerHandle(ref);                // O(log U)
        this.ranking.modificarPrioridad(hRef, new ElementoRanking(ref, referidor.millasDisponibles));    // O(log U)
        
        HeapHandle<ElementoRanking>.Handle hNuevo = this.ranking.encolar(new ElementoRanking(id, 500));    // O(log U)
        this.handlesRanking.insertar(new NodoHandle(id, hNuevo));                        // O(log U)
        
        Usuario maximoActual = this.devolverUsuario(this.elMasAcumulador);                // O(log U)                 
        
        if (referidor.millasTotales > maximoActual.millasTotales) {                 // O(1)
            this.elMasAcumulador = ref;                                                 // O(1)
        }
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        Usuario u = this.devolverUsuario(id);                        // O(log U)
        
        double factorCategoria = u.factorCategoria();                // O(1)
        
        Promocion promo = this.promos.obtener(nombrePromo);               // O(|L|)

        if (promo == null) {
            promo = this.promos.obtener("NoPromo");                        // O(1)
        }
        
        double factorPromo = promo.factor;                            // O(1)
        
        double millasGanadas = distancia*factorCategoria*factorPromo;        // O(1)
        
        if (millasGanadas <= promo.tope && promo.tope > 0) {                // O(1)
            promo.tope -= millasGanadas;
            u.millasTotales += millasGanadas;
            u.millasDisponibles += millasGanadas;
            
            HeapHandle<ElementoRanking>.Handle hU = obtenerHandle(id);            // O(log U)
            this.ranking.modificarPrioridad(hU, new ElementoRanking(id, u.millasDisponibles));        // O(log U)
            
            if (promo.tope == 0) {
                this.promos.eliminar(nombrePromo);            // O(|L|)
            }
        } else if (promo.tope == -1) {
                u.millasTotales += millasGanadas;
                u.millasDisponibles += millasGanadas;
                
                HeapHandle<ElementoRanking>.Handle hU = obtenerHandle(id);            // O(log U)
                this.ranking.modificarPrioridad(hU, new ElementoRanking(id, u.millasDisponibles));        // O(log U)
            }
            
            Usuario maximoActual = this.devolverUsuario(this.elMasAcumulador);            // O(log U)
            
            if (u.millasTotales > maximoActual.millasTotales) {                // O(1)
                this.elMasAcumulador = id;
            }
    }

    public void canjearMillas(int id, double cantidad) {
        Usuario u = this.devolverUsuario(id);                      // O(log U) - O(n)
        
        u.millasDisponibles -= cantidad;                                     // O(1)
        u.millasPerdidas += cantidad;                                        // O(1)
        
        HeapHandle<ElementoRanking>.Handle hU = obtenerHandle(id);            // O(log U)
        this.ranking.modificarPrioridad(hU, new ElementoRanking(id, u.millasDisponibles));         // O(log U)
        
 
        Usuario maximoActual = this.devolverUsuario(this.elMasCanjeador);      // O(log U) - O(n)
        
        if (u.millasPerdidas > maximoActual.millasPerdidas) {                 // O(1)
            this.elMasCanjeador = id;                                                    // O(1)
        }
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        Usuario o = this.devolverUsuario(origen);                // O(log U)
        Usuario d = this.devolverUsuario(destino);                // O(log U)
        
        o.millasDisponibles -= cantidad;                    // O(1)
        o.millasPerdidas += cantidad;
        d.millasDisponibles += cantidad;
        
        this.ranking.modificarPrioridad(obtenerHandle(origen), new ElementoRanking(origen, o.millasDisponibles));        // O(log U)
        this.ranking.modificarPrioridad(obtenerHandle(destino), new ElementoRanking(destino, d.millasDisponibles));        // O(log U)
        
        Usuario maximoActual = this.devolverUsuario(this.elMasCanjeador);                // O(log U)
        
        if (o.millasPerdidas > maximoActual.millasPerdidas) {            // O(1)
            this.elMasCanjeador = origen;
        }
        
        registrarTransferencia(o, d);            // O(log U)
    }

    public double consultarSaldo(int id) {
        Usuario usuario = this.devolverUsuario(id);     // O(log U)        // O(log U) en promedio O(U) peor caso
        
        return usuario.millasDisponibles;        // O(1)
    }

    public String consultarCategoria(int id) {
       Usuario usuario = this.devolverUsuario(id);    // O(log U)                         // O(log U)
       return usuario.nombreCategoria();            // O(1)
    }

    public int[] rankingMillas(int n) {
        int k = Math.min(n, this.ranking.tamaño());                // O(1)
        int[] res = new int[k];                                      // O(k)
        ArrayList<ElementoRanking> extraidos = new ArrayList<>();        // O(1)
        
        for (int i = 0; i < k; i++) {                                // O(k)
            ElementoRanking elem = this.ranking.desencolar();        // O(log U)
            res[i] = elem.id;
            extraidos.add(elem);                                    // O(1) amortizado
        }
        
        for (ElementoRanking elem : extraidos) {            // O(k)
            HeapHandle<ElementoRanking>.Handle nuevoH = this.ranking.encolar(elem);        // O(log U)
            NodoHandle nh = this.handlesRanking.devolver(new NodoHandle(elem.id, null));    // O(log U)
            
            if (nh != null) {
                nh.handle = nuevoH;            // O(1)
            }
        }
        
        return res;                                        // O(1)
    }

    public void iniciarPromocion(String nombre, double factor) {                 // O(|L|)
        Promocion promo = new Promocion(factor, -1);                            // O(1)
        this.promos.insertar(nombre, promo);                                    // O(|L|)
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {         // O(|L|)
        Promocion promo = new Promocion(factor, tope);                                      // O(1)
        this.promos.insertar(nombre, promo);                                                // O(|L|)
    }

    public void finalizarPromocion(String nombre) {                                         // O(|L|)
        this.promos.eliminar(nombre);                                                       // O(|L|)
    }

    public int elMasAcumulador() {
        return elMasAcumulador;                                                             // O(1)
    }

    public int elMasCanjeador() {
        return elMasCanjeador;                                                              // O(1)
    }
    
    private boolean esTransferidorExclusivo(Usuario u) {    // O(1)
        return u.tokenTransferidor > 0;    // O(1)
    }
    
    private boolean miIdEsMasGrandeQueMiParExclusivo(Usuario u) {    // O(1)
        return u.id > u.tokenTransferidor;    // O(1)
    }

    public int[][] paresTransferidoresExclusivos() { // O(U)
        ArrayList<int[]> paresExclusivos = new ArrayList<>();                                           // O(1)

        ABB<Usuario>.ABB_Iterador iterador = this.usuarios.iterador();                                  // O(log U) - O(U)

        while (iterador.haySiguiente()) {                                                               // U * O(1) = O(U)
            Usuario u = iterador.siguiente();                                                     // O(1)

            if (this.esTransferidorExclusivo(u) && this.miIdEsMasGrandeQueMiParExclusivo(u)) {    // O(1)
                paresExclusivos.add(new int[]{u.id, u.tokenTransferidor});                        // O(1)
            }
        }

        int tamanioFinal = paresExclusivos.size();                                                      // O(1)

        int[][] res = new int[tamanioFinal][2];                                                         // O(1)
        for (int i = 0; i < tamanioFinal; i++) {                                                        // U * O(1) = O(U)
            res[i] = paresExclusivos.get(i);    // O(1)
        }

        return res;                                                                                     // O(1)
    }

    // Metodo auxiliar para buscar/devolver un usuario
    private Usuario devolverUsuario(int id) { // O(log U) - O(U)
        return this.usuarios.devolver(new Usuario(id)); // O(log U) - O(U)
    }
    
    // Metodo auxiliar para obtener un handle usando devolver de ABB
    
    private HeapHandle<ElementoRanking>.Handle obtenerHandle(int id) {
        NodoHandle nodoBuscado = new NodoHandle(id, null);                                            // O(1)
        NodoHandle nodoEncontrado = this.handlesRanking.devolver(nodoBuscado);                        // O(log U)
        
        if (nodoEncontrado != null) {
            return nodoEncontrado.handle;                                            // O(1)
        }
        return null;                                                                    // O(1)
    }
    
    private void registrarTransferencia(Usuario origen, Usuario destino) {
        
        // Si es la primera transferencia de ambos, forman un par exclusivo.
        
        if (origen.tokenTransferidor == 0 && destino.tokenTransferidor == 0) {            // O(1)
            origen.tokenTransferidor = destino.id;
            destino.tokenTransferidor = origen.id;
            return;
        }
                
        // Caso ya son pareja exclusiva: si ya son transferidores exclusivos no pasa nada
        
        if (origen.tokenTransferidor == destino.id && destino.tokenTransferidor == origen.id) {        // O(1)
            return;
        }
        
        // Si no entró a ningun if anterior, no son exclusivos.
        // Todos los involucrados tanto en la transferencia como en los tokens dejan de ser exclusivos.
        romperExclusividad(origen);                                                                // O(log U)
        romperExclusividad(destino);
        origen.tokenTransferidor = -1;                                                                // O(1)
        destino.tokenTransferidor = -1;
    }
    
    private void romperExclusividad(Usuario usuario) {
        if (usuario.tokenTransferidor > 0) {                                                                // O(1)
            Usuario parejaAnterior = this.devolverUsuario(usuario.tokenTransferidor);                    // O(log U)
            if (parejaAnterior.tokenTransferidor == usuario.id) {                                            // O(1)
                parejaAnterior.tokenTransferidor = -1;                                                       // O(1)
            }
        }
    }
}
