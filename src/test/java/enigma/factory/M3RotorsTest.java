package enigma.factory;

import enigma.core.devices.NotchedRotor;
import enigma.core.util.Letter;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * M3RotorsTest
 *
 * @author diegodc 2017-04-19.
 */
class M3RotorsTest {

    @Test
    void testConfiguration_RotorI() {
        NotchedRotor rotor = M3Rotors.I.get();

        rotor.changePosition(Letter.Q);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorII() {
        NotchedRotor rotor = M3Rotors.II.get();

        rotor.changePosition(Letter.E);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {A,J,D,K,S,I,R,U,X,B,L,H,W,T,M,C,Q,G,Z,N,P,Y,F,V,O,E};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorIII() {
        NotchedRotor rotor = M3Rotors.III.get();

        rotor.changePosition(Letter.V);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {B,D,F,H,J,L,C,P,R,T,X,V,Z,N,Y,E,I,W,G,A,K,M,U,S,Q,O};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorIV() {
        NotchedRotor rotor = M3Rotors.IV.get();

        rotor.changePosition(Letter.J);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {E,S,O,V,P,Z,J,A,Y,Q,U,I,R,H,X,L,N,F,T,G,K,D,C,M,W,B};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorV() {
        NotchedRotor rotor = M3Rotors.V.get();

        rotor.changePosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {V,Z,B,R,G,I,T,Y,U,P,S,D,N,H,L,X,A,W,M,J,Q,O,F,E,C,K};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVI() {
        NotchedRotor rotor = M3Rotors.VI.get();

        rotor.changePosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.changePosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {J,P,G,V,O,U,M,F,Y,Q,B,E,N,H,Z,R,D,K,A,S,X,L,I,C,T,W};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVII() {
        NotchedRotor rotor = M3Rotors.VII.get();

        rotor.changePosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.changePosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {N,Z,J,H,G,R,C,X,M,Y,S,W,B,O,U,F,A,I,V,L,P,E,K,Q,D,T};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVIII() {
        NotchedRotor rotor = M3Rotors.VIII.get();

        rotor.changePosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.changePosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {F,K,Q,H,T,L,X,O,C,B,J,S,P,D,Z,R,A,M,E,W,N,I,U,Y,G,V};
        verifyWiring(rotor, expectedLetters);
    }

    private void verifyWiring(NotchedRotor rotor, Letter[] expectedLetters) {
        rotor.changePosition(A);
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

}