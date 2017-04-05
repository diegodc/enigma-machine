package enigma.core.devices;

import enigma.RotorFactory;
import enigma.core.util.Alphabet;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Alphabet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ReflectorTest
 *
 * @author diegodc 2017-02-06
 */
class ReflectorTest {

    private Reflector reflector;

    @Test
    void reflect() {
        Alphabet[] wiring = {Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T};
        reflector = new Reflector(wiring);

        assertPair(A, Y);
        assertPair(B, R);
        assertPair(C, U);
        assertPair(D, H);
        assertPair(E, Q);
        assertPair(F, S);
        assertPair(G, L);
        assertPair(H, D);
        assertPair(I, P);
        assertPair(J, X);
        assertPair(K, N);
        assertPair(L, G);
        assertPair(M, O);
    }

    @Test
    void testWiringReflectorB() {
        reflector = RotorFactory.reflectorB();

        assertPair(A, Y);
        assertPair(B, R);
        assertPair(C, U);
        assertPair(D, H);
        assertPair(E, Q);
        assertPair(F, S);
        assertPair(G, L);
        assertPair(H, D);
        assertPair(I, P);
        assertPair(J, X);
        assertPair(K, N);
        assertPair(L, G);
        assertPair(M, O);
    }

    @Test
    void testWiringReflectorC() {
        reflector = RotorFactory.reflectorC();

        assertPair(A, F);
        assertPair(B, V);
        assertPair(C, P);
        assertPair(D, J);
        assertPair(E, I);
        assertPair(F, A);
        assertPair(G, O);
        assertPair(H, Y);
        assertPair(I, E);
        assertPair(J, D);
        assertPair(K, R);
        assertPair(L, Z);
        assertPair(M, X);
    }

    private void assertPair(Alphabet letter, Alphabet pairedLetter) {
        assertEquals(letter, reflector.reflect(pairedLetter));
        assertEquals(pairedLetter, reflector.reflect(letter));
    }

}