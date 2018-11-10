package enigma.core.devices;

import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * RotorTest
 *
 * @author diegodc 2017-02-02
 */
class RotorTest {

    private static final Letter[] WIRING = {E, K, M, F, L, G, D, Q, V, Z, N, T, O, W, Y, H, X, U, S, P, A, I, B, R, C, J};
    private static final Letter[] INCOMPLETE_WIRING = {B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
    private static final Letter[] DUPLICATE_LETTER_WIRING = {A, A, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};

    private Rotor rotor;

    @BeforeEach
    void setUp() {
        rotor = new Rotor(WIRING);
    }

    @Test
    void creatingRotorWithIncompleteWiringShouldThrowException() {
        assertThrows(MalformedWiring.class, () -> new Rotor(INCOMPLETE_WIRING));
    }

    @Test
    void creatingRotorWithDuplicateLetterInWiringShouldThrowException() {
        assertThrows(MalformedWiring.class, () -> new Rotor(DUPLICATE_LETTER_WIRING));
    }

    @Test
    void initialPositionShouldBeLetterA() {
        assertEquals(A, rotor.currentPosition());
    }

    @Test
    void positionCanBeChanged() {
        rotor.changePosition(C);
        assertEquals(C, rotor.currentPosition());
    }

    @Test
    void initialRingSettingShouldBeLetterA() {
        assertEquals(A, rotor.ringSetting());
    }

    @Test
    void ringSettingCanBeChanged() {
        rotor.changeRingSetting(B);
        assertEquals(B, rotor.ringSetting());

        rotor.changeRingSetting(L);
        assertEquals(L, rotor.ringSetting());

        rotor.changeRingSetting(Z);
        assertEquals(Z, rotor.ringSetting());
    }

    @Test
    void rotorStepsToTheNextPosition() {
        rotor.step();
        assertEquals(B, rotor.currentPosition());
    }

    @Test
    void rotorPositionIsResetAfterPositionZ() {
        rotor.changePosition(Z);
        assertEquals(Z, rotor.currentPosition());

        rotor.step();
        assertEquals(A, rotor.currentPosition());
    }

    @Test
    void rotorCyclesThoughAllPositions() {

        Letter[] allPositions = Letter.values();

        for (Letter expectedPosition : allPositions) {
            assertEquals(expectedPosition, rotor.currentPosition());
            rotor.step();
        }

        assertEquals(A, rotor.currentPosition());
    }

    @Test
    void cipherInwardsInDefaultPositionAndRingSetting() {

        Letter[] expectedLetters = {E, K, M, F, L, G, D, Q, V, Z, N, T, O, W, Y, H, X, U, S, P, A, I, B, R, C, J};
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    @Test
    void cipherOutwardsInDefaultPositionAndRingSetting() {
        Letter[] actualLetters = {E, K, M, F, L, G, D, Q, V, Z, N, T, O, W, Y, H, X, U, S, P, A, I, B, R, C, J};
        Letter expected = A;

        for (Letter actual : actualLetters) {
            assertEquals(expected, rotor.cipherOutwards(actual));
            expected = expected.nextLetter();
        }
    }

    @Test
    void cipherInwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.changePosition(B);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.changePosition(C);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePosition(D);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(E, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.changePosition(E);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.changePosition(F);
        assertEquals(B, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.changePosition(G);
        assertEquals(X, rotor.cipherInwards(A));
        assertEquals(M, rotor.cipherInwards(M));
        assertEquals(A, rotor.cipherInwards(Z));

        rotor.changePosition(H);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(I, rotor.cipherInwards(M));
        assertEquals(W, rotor.cipherInwards(Z));

        rotor.changePosition(I);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(S, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePosition(J);
        assertEquals(Q, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.changePosition(K);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(R, rotor.cipherInwards(M));
        assertEquals(P, rotor.cipherInwards(Z));

        rotor.changePosition(L);
        assertEquals(I, rotor.cipherInwards(A));
        assertEquals(G, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.changePosition(M);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(H, rotor.cipherInwards(Z));

        rotor.changePosition(N);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.changePosition(O);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePosition(P);
        assertEquals(S, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.changePosition(Q);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(R, rotor.cipherInwards(Z));

        rotor.changePosition(R);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.changePosition(S);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.changePosition(T);
        assertEquals(W, rotor.cipherInwards(A));
        assertEquals(N, rotor.cipherInwards(M));
        assertEquals(Z, rotor.cipherInwards(Z));

        rotor.changePosition(U);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.changePosition(V);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(F, rotor.cipherInwards(Z));

        rotor.changePosition(W);
        assertEquals(F, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.changePosition(X);
        assertEquals(U, rotor.cipherInwards(A));
        assertEquals(C, rotor.cipherInwards(M));
        assertEquals(E, rotor.cipherInwards(Z));

        rotor.changePosition(Y);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(T, rotor.cipherInwards(Z));

        rotor.changePosition(Z);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(U, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.changePosition(B);
        assertEquals(V, rotor.cipherOutwards(A));
        assertEquals(J, rotor.cipherOutwards(M));
        assertEquals(T, rotor.cipherOutwards(Z));

        rotor.changePosition(M);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(C, rotor.cipherOutwards(M));
        assertEquals(S, rotor.cipherOutwards(Z));

        rotor.changePosition(Z);
        assertEquals(K, rotor.cipherOutwards(A));
        assertEquals(F, rotor.cipherOutwards(M));
        assertEquals(P, rotor.cipherOutwards(Z));
    }

    @Test
    void cipherInwardsDefaultPositionRingSettingB() {

        Letter[] expectedLetters = {K, F, L, N, G, M, H, E, R, W, A, O, U, P, X, Z, I, Y, V, T, Q, B, J, C, S, D};
        Letter actual = A;

        rotor.changeRingSetting(B);

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    @Test
    void cipherInwardsDifferentPositionsAndRingSettings() {
        rotor.changeRingSetting(M);
        rotor.changePosition(A);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changeRingSetting(M);
        rotor.changePosition(G);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(A);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(N);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(R);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(Z);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsDefaultPositionRingSettingL() {

        Letter[] actualLetters = {S, I, F, D, A, L, T, M, C, N, U, P, V, X, Q, W, R, O, B, G, K, Y, E, Z, H, J};
        Letter expected = A;

        rotor.changeRingSetting(L);

        for (Letter actual : actualLetters) {
            assertEquals(expected, rotor.cipherOutwards(actual));
            expected = expected.nextLetter();
        }
    }

    @Test
    void cipherOutwardsDifferentPositionsAndRingSettings() {
        rotor.changeRingSetting(M);
        rotor.changePosition(A);
        assertEquals(Y, rotor.cipherOutwards(A));
        assertEquals(G, rotor.cipherOutwards(M));
        assertEquals(W, rotor.cipherOutwards(Z));

        rotor.changeRingSetting(M);
        rotor.changePosition(G);
        assertEquals(X, rotor.cipherOutwards(A));
        assertEquals(L, rotor.cipherOutwards(M));
        assertEquals(R, rotor.cipherOutwards(Z));

        rotor.changeRingSetting(M);
        rotor.changePosition(V);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(Z, rotor.cipherOutwards(M));
        assertEquals(M, rotor.cipherOutwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(F);
        assertEquals(Z, rotor.cipherOutwards(A));
        assertEquals(M, rotor.cipherOutwards(M));
        assertEquals(X, rotor.cipherOutwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(R);
        assertEquals(A, rotor.cipherOutwards(A));
        assertEquals(I, rotor.cipherOutwards(M));
        assertEquals(F, rotor.cipherOutwards(Z));

        rotor.changeRingSetting(Z);
        rotor.changePosition(G);
        assertEquals(I, rotor.cipherOutwards(A));
        assertEquals(E, rotor.cipherOutwards(M));
        assertEquals(Y, rotor.cipherOutwards(Z));
    }

}