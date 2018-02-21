package enigma.core.devices;

import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PlugboardTest
 *
 * @author diegodc 2017-02-07.
 */
class PlugboardTest {

    private Plugboard plugboard;

    @BeforeEach
    void setUp() {
        plugboard = new Plugboard();
    }

    @Test
    void initiallyLettersAreUnsteckered() {
        assertAllLetterAreUnsteckered();
    }

    private void assertAllLetterAreUnsteckered() {
        for (Letter letter : Letter.values())
            assertEquals(letter, plugboard.swap(letter));
    }

    @Test
    void lettersAreCorrectlySteckered() {
        connectLetters();

        Letter[] connectedLetters = {W,F,L,N,O,B,K,V,J,I,G,C,Z,D,E,X,T,Y,U,Q,S,H,A,P,R,M};

        Letter actual = A;

        for (Letter expected : connectedLetters) {
            assertEquals(expected, plugboard.swap(actual));
            assertEquals(actual, plugboard.swap(expected));
            actual = actual.nextLetter();
        }
    }

    private void connectLetters() {
        plugboard.connect(A, W).connect(B, F).connect(C, L)
                .connect(D, N).connect(E, O).connect(H, V)
                .connect(J, I).connect(K, G).connect(T, Q)
                .connect(U, S).connect(X, P).connect(Y, R)
                .connect(Z, M);
    }

    @Test
    void plugboardCanBeResetToInitialState() {
        connectLetters();
        plugboard.disconnectAllPairs();
        assertAllLetterAreUnsteckered();
    }

    @Test
    void itIsPossibleToReconnectLetters() {
        plugboard.connect(A, B);
        assertEquals(A, plugboard.swap(B));
        assertEquals(B, plugboard.swap(A));

        plugboard.connect(A, C);
        assertEquals(A, plugboard.swap(C));
        assertEquals(C, plugboard.swap(A));
        assertEquals(B, plugboard.swap(B));

        plugboard.connect(T, C);
        assertEquals(A, plugboard.swap(A));
        assertEquals(C, plugboard.swap(T));
        assertEquals(T, plugboard.swap(C));

        plugboard.connect(C, Z).connect(H, T);

        assertEquals(Z, plugboard.swap(C));
        assertEquals(C, plugboard.swap(Z));
        assertEquals(T, plugboard.swap(H));
        assertEquals(H, plugboard.swap(T));
        assertEquals(A, plugboard.swap(A));
        assertEquals(B, plugboard.swap(B));
    }

}