package enigma.factory;

import enigma.core.devices.Rotor;
import enigma.core.util.Letter;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * M4RotorsTest
 *
 * @author diegodc 2017-04-19.
 */
class M4RotorsTest {

    @Test
    void testConfiguration_RotorBeta() {
        Rotor rotor = M4Rotors.Beta.get();

        Letter[] expectedLetters = {L, E, Y, J, V, C, N, I, X, W, P, B, Q, M, D, R, T, A, K, Z, G, F, U, H, O, S};

        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorGamma() {
        Rotor rotor = M4Rotors.Gamma.get();

        Letter[] expectedLetters = {F, S, O, K, A, N, U, E, R, H, M, B, T, I, Y, C, W, L, Q, P, Z, X, V, G, J, D};

        verifyWiring(rotor, expectedLetters);
    }


    private void verifyWiring(Rotor rotor, Letter[] expectedLetters) {
        rotor.changePosition(A);
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

}