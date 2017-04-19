package enigma.core.devices;

import enigma.core.util.Letter;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ReflectorTest
 *
 * @author diegodc 2017-02-06.
 */
class ReflectorTest {

    @Test
    void reflectsLettersCorrectly() {
        Letter[] wiring = {E,J,M,Z,A,L,Y,X,V,B,W,F,C,R,Q,U,O,N,T,S,P,I,K,H,G,D};
        Reflector reflector = new Reflector(wiring);

        Letter expectedLetter = A;
        for (Letter letter : wiring) {
            assertEquals(expectedLetter, reflector.reflect(letter));
            expectedLetter = expectedLetter.nextLetter();
        }
    }

    @Test
    void creatingReflectorWithIncompleteWiring_ShouldThrowException() {
        Letter[] wiring = {B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
        assertThrows(MalformedWiring.class, ()-> new Reflector(wiring));
    }

    @Test
    void creatingReflectorWithRepeatedLetterInWiring_ShouldThrowException() {
        Letter[] wiring = {A,A,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
        assertThrows(MalformedWiring.class, ()-> new Reflector(wiring));
    }

}