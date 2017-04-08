package enigma.core.devices;

import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PlugboardTest
 *
 * @author diegodc 2017-02-07
 */
class PlugboardTest {

    private Plugboard plugboard;

    @BeforeEach
    void setUp() {
        plugboard = new Plugboard();
    }

    @Test
    void initiallyLettersAreUnpaired() {
        assertAllLetterAreUnpaired();
    }

    private void assertAllLetterAreUnpaired() {
        for (Letter letter : Letter.values())
            assertEquals(letter, plugboard.cipher(letter));
    }

    @Test
    void lettersArePaired() {

        pairLetters();

        assertEquals(A, plugboard.cipher(W));
        assertEquals(B, plugboard.cipher(F));
        assertEquals(C, plugboard.cipher(L));
        assertEquals(D, plugboard.cipher(N));
        assertEquals(E, plugboard.cipher(O));
        assertEquals(F, plugboard.cipher(B));
        assertEquals(G, plugboard.cipher(K));
        assertEquals(H, plugboard.cipher(V));
        assertEquals(I, plugboard.cipher(J));
        assertEquals(J, plugboard.cipher(I));
        assertEquals(K, plugboard.cipher(G));
        assertEquals(L, plugboard.cipher(C));
        assertEquals(M, plugboard.cipher(Z));
        assertEquals(N, plugboard.cipher(D));
        assertEquals(O, plugboard.cipher(E));
        assertEquals(P, plugboard.cipher(X));
        assertEquals(Q, plugboard.cipher(T));
        assertEquals(R, plugboard.cipher(Y));
        assertEquals(S, plugboard.cipher(U));
        assertEquals(T, plugboard.cipher(Q));
        assertEquals(U, plugboard.cipher(S));
        assertEquals(V, plugboard.cipher(H));
        assertEquals(W, plugboard.cipher(A));
        assertEquals(X, plugboard.cipher(P));
        assertEquals(Y, plugboard.cipher(R));
        assertEquals(Z, plugboard.cipher(M));
    }

    private void pairLetters() {
        plugboard.pairLetters(A, W)
                .pairLetters(B, F)
                .pairLetters(C, L)
                .pairLetters(D, N)
                .pairLetters(E, O)
                .pairLetters(H, V)
                .pairLetters(J, I)
                .pairLetters(K, G)
                .pairLetters(T, Q)
                .pairLetters(U, S)
                .pairLetters(X, P)
                .pairLetters(Y, R)
                .pairLetters(Z, M);
    }

    @Test
    void plugboardCanBeResetToInitialState() {
        pairLetters();
        plugboard.reset();
        assertAllLetterAreUnpaired();
    }

    @Test
    void pairedLettersAreRearranged() {
        plugboard.pairLetters(A, B);
        assertEquals(A, plugboard.cipher(B));
        assertEquals(B, plugboard.cipher(A));

        plugboard.pairLetters(A, C);
        assertEquals(A, plugboard.cipher(C));
        assertEquals(C, plugboard.cipher(A));
        assertEquals(B, plugboard.cipher(B));

        plugboard.pairLetters(T, C);
        assertEquals(A, plugboard.cipher(A));
        assertEquals(C, plugboard.cipher(T));
        assertEquals(T, plugboard.cipher(C));

        plugboard.pairLetters(C, Z)
                .pairLetters(H, T);
        assertEquals(Z, plugboard.cipher(C));
        assertEquals(C, plugboard.cipher(Z));
        assertEquals(T, plugboard.cipher(H));
        assertEquals(H, plugboard.cipher(T));
        assertEquals(A, plugboard.cipher(A));
        assertEquals(B, plugboard.cipher(B));
    }

}