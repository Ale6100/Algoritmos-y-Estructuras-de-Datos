package aed.tp2;

import org.junit.jupiter.api.Test;
import aed.tp2.recursos.HeapHandle;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HeapHandleTests {
    private HeapHandle<Integer> miHeap;

    @BeforeEach
    void setUp() {
        miHeap = new HeapHandle<Integer>();
    }

    @Test
    void nuevo_heap_vacio() {
        assertTrue(miHeap.esVacio());
        assertEquals(0, miHeap.tamaño());
    }

    @Test
    void encolar_y_desencolar_un_elemento() {
        miHeap.encolar(42);

        assertEquals(1, miHeap.tamaño());
        assertTrue(miHeap.desencolar() == 42);
        assertEquals(0, miHeap.tamaño());
        assertTrue(miHeap.esVacio());
    }

    @Test
    void encolar_y_desencolar_un_varios_elementos() {
        miHeap.encolar(12);
        miHeap.encolar(36);
        miHeap.encolar(7);
        miHeap.encolar(0);
        miHeap.encolar(-2);

        assertEquals(5, miHeap.tamaño());
        assertTrue(miHeap.desencolar() == 36);
        assertEquals(4, miHeap.tamaño());
        assertTrue(miHeap.desencolar() == 12);
        assertEquals(3, miHeap.tamaño());
        miHeap.desencolar();
        miHeap.desencolar();
        miHeap.desencolar();
        assertTrue(miHeap.esVacio());
    }
    
    @Test
    void ejemploClase() {
        miHeap.encolar(6);
        miHeap.encolar(4);
        miHeap.encolar(2);
        miHeap.encolar(9);
        miHeap.encolar(3);
        miHeap.encolar(8);
        miHeap.encolar(5);
        
        assertEquals(7, miHeap.tamaño());
        
        assertEquals(9, miHeap.desencolar());
        assertEquals(8, miHeap.desencolar());
        assertEquals(6, miHeap.desencolar());
        assertEquals(5, miHeap.desencolar());
        assertEquals(4, miHeap.desencolar());
        assertEquals(3, miHeap.desencolar());
        assertEquals(2, miHeap.desencolar());
        
        assertTrue(miHeap.esVacio());
        
    }
    
    @Test
    void testModificarPrioridadConHandle() {
        HeapHandle<Integer>.Handle h1 = miHeap.encolar(10);
        HeapHandle<Integer>.Handle h2 = miHeap.encolar(50);
        HeapHandle<Integer>.Handle h3 = miHeap.encolar(30);
        
        assertEquals(h2, miHeap.maximo());
        
        miHeap.modificarPrioridad(h1,100);
        assertEquals(h1, miHeap.maximo());
        
        miHeap.modificarPrioridad(h1,5);
        assertEquals(h2, miHeap.maximo());
    }
}
