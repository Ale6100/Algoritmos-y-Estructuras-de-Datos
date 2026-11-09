package aed.tp2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aed.tp2.recursos.Trie;

public class TrieTest {
    private Trie<Integer> miTrie;

    @BeforeEach
    void setUp() {
        this.miTrie = new Trie<Integer>();
    }

    @Test
    void insertar_elemento() {
        this.miTrie.insertar("a", 1);

        assertTrue(this.miTrie.pertenece("a"));
        assertEquals(1, this.miTrie.obtener("a"));
    }

    @Test
    void insertar_elemento_string() {
        Trie<String> trieNumber = new Trie<String>();

        trieNumber.insertar("a", "b");

        assertTrue(trieNumber.pertenece("a"));
        assertEquals("b", trieNumber.obtener("a"));
    }

    @Test
    void insertar_varios_elementos() {
        this.miTrie.insertar("a", 1);
        this.miTrie.insertar("av", 2);
        this.miTrie.insertar("ave", 3);

        assertTrue(this.miTrie.pertenece("a"));
        assertEquals(1, this.miTrie.obtener("a"));
        assertTrue(this.miTrie.pertenece("av"));
        assertEquals(2, this.miTrie.obtener("av"));
        assertTrue(this.miTrie.pertenece("ave"));
        assertEquals(3, this.miTrie.obtener("ave"));
        assertFalse(this.miTrie.pertenece("avem"));
    }

    @Test
    void insertar_y_eliminar_elemento() {
        this.miTrie.insertar("a", 1);
        this.miTrie.eliminar("a");

        assertFalse(this.miTrie.pertenece("a"));
    }

    @Test
    void insertar_y_eliminar_varios_elemento() {
        this.miTrie.insertar("a", 1);
        this.miTrie.insertar("av", 2);
        this.miTrie.insertar("ave", 3);
        this.miTrie.insertar("aves", 4);

        this.miTrie.eliminar("a");
        assertFalse(this.miTrie.pertenece("a"));

        assertTrue(this.miTrie.pertenece("av"));
        assertEquals(2, this.miTrie.obtener("av"));
        assertTrue(this.miTrie.pertenece("ave"));
        assertEquals(3, this.miTrie.obtener("ave"));
        assertTrue(this.miTrie.pertenece("aves"));
        assertEquals(4, this.miTrie.obtener("aves"));

        this.miTrie.eliminar("ave");
        assertFalse(this.miTrie.pertenece("ave"));

        assertTrue(this.miTrie.pertenece("av"));
        assertEquals(2, this.miTrie.obtener("av"));
        assertTrue(this.miTrie.pertenece("aves"));
        assertEquals(4, this.miTrie.obtener("aves"));
    }
}
