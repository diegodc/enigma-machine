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

    private static final Letter[] WIRING = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
    private static final Letter[] INCOMPLETE_WIRING = {B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
    private static final Letter[] DUPLICATE_LETTER_WIRING = {A,A,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};

    private Rotor rotor;

    @BeforeEach
    void setUp() {
        rotor = new Rotor(WIRING);
    }

    @Test
    void creatingRotorWithIncompleteWiringShouldThrowException() {
        assertThrows(Rotor.MalformedWiring.class, () -> new Rotor(INCOMPLETE_WIRING));
    }

    @Test
    void creatingRotorWithDuplicateLetterInWiringShouldThrowException() {
        assertThrows(Rotor.MalformedWiring.class, () -> new Rotor(DUPLICATE_LETTER_WIRING));
    }

    @Test
    void initialPositionShouldBeLetterA() {
        assertEquals(A, rotor.getPosition());
    }

    @Test
    void positionCanBeChanged() {
        rotor.setPosition(C);
        assertEquals(C, rotor.getPosition());
    }

    @Test
    void initialRingSettingShouldBeLetterA() {
        assertEquals(A, rotor.getRingSetting());
    }

    @Test
    void ringSettingCanBeChanged() {
        rotor.setRingSetting(B);
        assertEquals(B, rotor.getRingSetting());

        rotor.setRingSetting(L);
        assertEquals(L, rotor.getRingSetting());

        rotor.setRingSetting(Z);
        assertEquals(Z, rotor.getRingSetting());
    }

    @Test
    void rotorStepsToTheNextPosition() {
        rotor.step();
        assertEquals(B, rotor.getPosition());
    }

    @Test
    void rotorPositionIsResetAfterPositionZ() {
        rotor.setPosition(Z);
        assertEquals(Z, rotor.getPosition());

        rotor.step();
        assertEquals(A, rotor.getPosition());
    }

    @Test
    void rotorCyclesThoughAllPositions() {

        Letter[] allPositions = Letter.values();

        for(Letter expectedPosition : allPositions) {
            assertEquals(expectedPosition, rotor.getPosition());
            rotor.step();
        }

        assertEquals(A, rotor.getPosition());
    }

    @Test
    void cipherInwardsInDefaultPositionAndRingSetting() {

        Letter[] expectedLetters = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    @Test
    void cipherOutwardsInDefaultPositionAndRingSetting() {
        Letter[] actualLetters = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
        Letter expected = A;

        for (Letter actual : actualLetters) {
            assertEquals(expected, rotor.cipherOutwards(actual));
            expected = expected.nextLetter();
        }
    }

    @Test
    void cipherInwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.setPosition(B);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.setPosition(C);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.setPosition(D);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(E, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.setPosition(E);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.setPosition(F);
        assertEquals(B, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.setPosition(G);
        assertEquals(X, rotor.cipherInwards(A));
        assertEquals(M, rotor.cipherInwards(M));
        assertEquals(A, rotor.cipherInwards(Z));

        rotor.setPosition(H);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(I, rotor.cipherInwards(M));
        assertEquals(W, rotor.cipherInwards(Z));

        rotor.setPosition(I);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(S, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.setPosition(J);
        assertEquals(Q, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.setPosition(K);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(R, rotor.cipherInwards(M));
        assertEquals(P, rotor.cipherInwards(Z));

        rotor.setPosition(L);
        assertEquals(I, rotor.cipherInwards(A));
        assertEquals(G, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.setPosition(M);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(H, rotor.cipherInwards(Z));

        rotor.setPosition(N);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.setPosition(O);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.setPosition(P);
        assertEquals(S, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.setPosition(Q);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(R, rotor.cipherInwards(Z));

        rotor.setPosition(R);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.setPosition(S);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.setPosition(T);
        assertEquals(W, rotor.cipherInwards(A));
        assertEquals(N, rotor.cipherInwards(M));
        assertEquals(Z, rotor.cipherInwards(Z));

        rotor.setPosition(U);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.setPosition(V);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(F, rotor.cipherInwards(Z));

        rotor.setPosition(W);
        assertEquals(F, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.setPosition(X);
        assertEquals(U, rotor.cipherInwards(A));
        assertEquals(C, rotor.cipherInwards(M));
        assertEquals(E, rotor.cipherInwards(Z));

        rotor.setPosition(Y);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(T, rotor.cipherInwards(Z));

        rotor.setPosition(Z);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(U, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.setPosition(B);
        assertEquals(V, rotor.cipherOutwards(A));
        assertEquals(J, rotor.cipherOutwards(M));
        assertEquals(T, rotor.cipherOutwards(Z));

        rotor.setPosition(M);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(C, rotor.cipherOutwards(M));
        assertEquals(S, rotor.cipherOutwards(Z));

        rotor.setPosition(Z);
        assertEquals(K, rotor.cipherOutwards(A));
        assertEquals(F, rotor.cipherOutwards(M));
        assertEquals(P, rotor.cipherOutwards(Z));
    }

    @Test
    void cipherInwardsDefaultPositionRingSettingB() {

        Letter[] expectedLetters = {K,F,L,N,G,M,H,E,R,W,A,O,U,P,X,Z,I,Y,V,T,Q,B,J,C,S,D};
        Letter actual = A;

        rotor.setRingSetting(B);

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    @Test
    void cipherInwardsDifferentPositionsAndRingSettings() {
        rotor.setRingSetting(M);
        rotor.setPosition(A);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.setRingSetting(M);
        rotor.setPosition(G);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(A);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(N);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(R);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(Z);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsDefaultPositionRingSettingL() {

        Letter[] actualLetters = {S,I,F,D,A,L,T,M,C,N,U,P,V,X,Q,W,R,O,B,G,K,Y,E,Z,H,J};
        Letter expected = A;

        rotor.setRingSetting(L);

        for (Letter actual : actualLetters) {
            assertEquals(expected, rotor.cipherOutwards(actual));
            expected = expected.nextLetter();
        }
    }

    @Test
    void cipherOutwardsDifferentPositionsAndRingSettings() {
        rotor.setRingSetting(M);
        rotor.setPosition(A);
        assertEquals(Y, rotor.cipherOutwards(A));
        assertEquals(G, rotor.cipherOutwards(M));
        assertEquals(W, rotor.cipherOutwards(Z));

        rotor.setRingSetting(M);
        rotor.setPosition(G);
        assertEquals(X, rotor.cipherOutwards(A));
        assertEquals(L, rotor.cipherOutwards(M));
        assertEquals(R, rotor.cipherOutwards(Z));

        rotor.setRingSetting(M);
        rotor.setPosition(V);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(Z, rotor.cipherOutwards(M));
        assertEquals(M, rotor.cipherOutwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(F);
        assertEquals(Z, rotor.cipherOutwards(A));
        assertEquals(M, rotor.cipherOutwards(M));
        assertEquals(X, rotor.cipherOutwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(R);
        assertEquals(A, rotor.cipherOutwards(A));
        assertEquals(I, rotor.cipherOutwards(M));
        assertEquals(F, rotor.cipherOutwards(Z));

        rotor.setRingSetting(Z);
        rotor.setPosition(G);
        assertEquals(I, rotor.cipherOutwards(A));
        assertEquals(E, rotor.cipherOutwards(M));
        assertEquals(Y, rotor.cipherOutwards(Z));
    }

}