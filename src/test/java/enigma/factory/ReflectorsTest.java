package enigma.factory;

import enigma.core.devices.Reflector;
import enigma.core.util.Letter;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ReflectorsTest
 *
 * @author diegodc 2017-04-19.
 */
class ReflectorsTest {

    @Test
    void testConfiguration_ReflectorA() {
        Reflector reflector = Reflectors.A.get();

        Letter[] expectedLetters = {E,J,M,Z,A,L,Y,X,V,B,W,F,C,R,Q,U,O,N,T,S,P,I,K,H,G,D};

        verifyWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorB() {
        Reflector reflector = Reflectors.B.get();

        Letter[] expectedLetters = {Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T};

        verifyWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorC() {
        Reflector reflector = Reflectors.C.get();

        Letter[] expectedLetters = {F,V,P,J,I,A,O,Y,E,D,R,Z,X,W,G,C,T,K,U,Q,S,B,N,M,H,L};

        verifyWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorThinB() {
        Reflector reflector = Reflectors.ThinB.get();

        Letter[] expectedLetters = {E,N,K,Q,A,U,Y,W,J,I,C,O,P,B,L,M,D,X,Z,V,F,T,H,R,G,S};

        verifyWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorThinC() {
        Reflector reflector = Reflectors.ThinC.get();

        Letter[] expectedLetters = {R,D,O,B,J,N,T,K,V,E,H,M,L,F,C,W,Z,A,X,G,Y,I,P,S,U,Q};

        verifyWiring(reflector, expectedLetters);
    }

    private void verifyWiring(Reflector reflector, Letter[] expectedLetters) {
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, reflector.reflect(actual));
            actual = actual.nextLetter();
        }
    }

}