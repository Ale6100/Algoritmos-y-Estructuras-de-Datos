package aed.tp2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlantillaTest {

    private AirMiles am;

    @BeforeEach
    void setUp() {
        am = new AirMiles();
    }

    @Test
    void test() {
        am.registrarUsuario(10);
        assertEquals(0.0, am.consultarSaldo(10));
        assertEquals("Bronce", am.consultarCategoria(10));
        
        am.registrarUsuarioConReferidor(20,10);
        assertEquals(500.0, am.consultarSaldo(10));
        assertEquals(500.0, am.consultarSaldo(20));
        
        am.iniciarPromocionConTope("felipealejandroarieljulio", 2.0, 600.0);
        
        am.acumularMillas(10, 250, "felipealejandroarieljulio");
        assertEquals(1000.0, am.consultarSaldo(10));
        
        am.acumularMillas(20, 50, "felipealejandroarieljulio");
        assertEquals(600.0, am.consultarSaldo(20));
        
        am.acumularMillas(20, 100, "felipealejandroarieljulio");
        assertEquals(700.0, am.consultarSaldo(20));
        
        assertEquals(10, am.elMasAcumulador());
        
        int[] top2 = am.rankingMillas(2);
        assertEquals(2, top2.length);
        assertEquals(10, top2[0]);
        assertEquals(20, top2[1]);
        
        am.canjearMillas(10, 500);
        assertEquals(500.0, am.consultarSaldo(10));
        assertEquals(10, am.elMasCanjeador());
        
        am.transferirMillas(10,20,300);
        assertEquals(200.0, am.consultarSaldo(10));
        assertEquals(1000.0, am.consultarSaldo(20));
        
        am.transferirMillas(20,10,100);
        
        int[][] pares = am.paresTransferidoresExclusivos();
        assertEquals(1, pares.length);
        assertEquals(20, pares[0][0]);
        assertEquals(10, pares[0][1]);
    }
}
