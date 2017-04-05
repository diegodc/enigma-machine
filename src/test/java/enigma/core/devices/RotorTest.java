package enigma.core.devices;

import enigma.RotorFactory;
import enigma.core.util.Alphabet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Alphabet.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RotorTest
 *
 * @author diegodc 2017-02-02
 */
class RotorTest {

    private Rotor rotor;

    @BeforeEach
    void setUp() {
        rotor = RotorFactory.rotorI();
    }

    @Test
    void initialPositionShouldBeLetterA() {
        assertEquals(A, rotor.getPosition());
    }

    @Test
    void positionCanBeChanged() {
        rotor.changePositionTo(C);
        assertEquals(C, rotor.getPosition());
    }

    @Test
    void initialRingSettingShouldBeLetterA() {
        assertEquals(A, rotor.getRingSetting());
    }

    @Test
    void ringSettingCanBeChanged() {
        rotor.ringSetting(B);
        assertEquals(B, rotor.getRingSetting());

        rotor.ringSetting(L);
        assertEquals(L, rotor.getRingSetting());

        rotor.ringSetting(Z);
        assertEquals(Z, rotor.getRingSetting());
    }

    @Test
    void rotorStepsToTheNextPosition() {
        rotor.step();
        assertEquals(B, rotor.getPosition());
    }

    @Test
    void rotorPositionIsResetToLetterA() {
        rotor.changePositionTo(Z);
        assertEquals(Z, rotor.getPosition());

        rotor.step();
        assertEquals(A, rotor.getPosition());
    }

    @Test
    void rotorCyclesThoughAllPositions() {

        Alphabet[] allPositions = Alphabet.values();

        for(Alphabet expectedPosition : allPositions) {
            assertEquals(expectedPosition, rotor.getPosition());
            rotor.step();
        }

        assertEquals(A, rotor.getPosition());
    }

    @Test
    void rotorKnowsIfItIsInTurnoverPosition() {
        rotor.changePositionTo(P);
        assertFalse(rotor.isAtTurnoverPosition());

        rotor.step();
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.step();
        assertFalse(rotor.isAtTurnoverPosition());
    }

    @Test
    void cipherInwardsInDefaultPositionAndRingSetting() {
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(K, rotor.cipherInwards(B));
        assertEquals(M, rotor.cipherInwards(C));
        assertEquals(F, rotor.cipherInwards(D));
        assertEquals(L, rotor.cipherInwards(E));
        assertEquals(G, rotor.cipherInwards(F));
        assertEquals(D, rotor.cipherInwards(G));
        assertEquals(Q, rotor.cipherInwards(H));
        assertEquals(V, rotor.cipherInwards(I));
        assertEquals(Z, rotor.cipherInwards(J));
        assertEquals(N, rotor.cipherInwards(K));
        assertEquals(T, rotor.cipherInwards(L));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(W, rotor.cipherInwards(N));
        assertEquals(Y, rotor.cipherInwards(O));
        assertEquals(H, rotor.cipherInwards(P));
        assertEquals(X, rotor.cipherInwards(Q));
        assertEquals(U, rotor.cipherInwards(R));
        assertEquals(S, rotor.cipherInwards(S));
        assertEquals(P, rotor.cipherInwards(T));
        assertEquals(A, rotor.cipherInwards(U));
        assertEquals(I, rotor.cipherInwards(V));
        assertEquals(B, rotor.cipherInwards(W));
        assertEquals(R, rotor.cipherInwards(X));
        assertEquals(C, rotor.cipherInwards(Y));
        assertEquals(J, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsInDefaultPositionAndRingSetting() {
        assertEquals(A, rotor.cipherOutwards(E));
        assertEquals(B, rotor.cipherOutwards(K));
        assertEquals(C, rotor.cipherOutwards(M));
        assertEquals(D, rotor.cipherOutwards(F));
        assertEquals(E, rotor.cipherOutwards(L));
        assertEquals(F, rotor.cipherOutwards(G));
        assertEquals(G, rotor.cipherOutwards(D));
        assertEquals(H, rotor.cipherOutwards(Q));
        assertEquals(I, rotor.cipherOutwards(V));
        assertEquals(J, rotor.cipherOutwards(Z));
        assertEquals(K, rotor.cipherOutwards(N));
        assertEquals(L, rotor.cipherOutwards(T));
        assertEquals(M, rotor.cipherOutwards(O));
        assertEquals(N, rotor.cipherOutwards(W));
        assertEquals(O, rotor.cipherOutwards(Y));
        assertEquals(P, rotor.cipherOutwards(H));
        assertEquals(Q, rotor.cipherOutwards(X));
        assertEquals(R, rotor.cipherOutwards(U));
        assertEquals(S, rotor.cipherOutwards(S));
        assertEquals(T, rotor.cipherOutwards(P));
        assertEquals(U, rotor.cipherOutwards(A));
        assertEquals(V, rotor.cipherOutwards(I));
        assertEquals(W, rotor.cipherOutwards(B));
        assertEquals(X, rotor.cipherOutwards(R));
        assertEquals(Y, rotor.cipherOutwards(C));
        assertEquals(Z, rotor.cipherOutwards(J));
    }

    @Test
    void cipherInwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.changePositionTo(B);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.changePositionTo(C);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePositionTo(D);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(E, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.changePositionTo(E);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.changePositionTo(F);
        assertEquals(B, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.changePositionTo(G);
        assertEquals(X, rotor.cipherInwards(A));
        assertEquals(M, rotor.cipherInwards(M));
        assertEquals(A, rotor.cipherInwards(Z));

        rotor.changePositionTo(H);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(I, rotor.cipherInwards(M));
        assertEquals(W, rotor.cipherInwards(Z));

        rotor.changePositionTo(I);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(S, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePositionTo(J);
        assertEquals(Q, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.changePositionTo(K);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(R, rotor.cipherInwards(M));
        assertEquals(P, rotor.cipherInwards(Z));

        rotor.changePositionTo(L);
        assertEquals(I, rotor.cipherInwards(A));
        assertEquals(G, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.changePositionTo(M);
        assertEquals(C, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(H, rotor.cipherInwards(Z));

        rotor.changePositionTo(N);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(B, rotor.cipherInwards(Z));

        rotor.changePositionTo(O);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.changePositionTo(P);
        assertEquals(S, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));

        rotor.changePositionTo(Q);
        assertEquals(H, rotor.cipherInwards(A));
        assertEquals(W, rotor.cipherInwards(M));
        assertEquals(R, rotor.cipherInwards(Z));

        rotor.changePositionTo(R);
        assertEquals(D, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(G, rotor.cipherInwards(Z));

        rotor.changePositionTo(S);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.changePositionTo(T);
        assertEquals(W, rotor.cipherInwards(A));
        assertEquals(N, rotor.cipherInwards(M));
        assertEquals(Z, rotor.cipherInwards(Z));

        rotor.changePositionTo(U);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.changePositionTo(V);
        assertEquals(N, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(F, rotor.cipherInwards(Z));

        rotor.changePositionTo(W);
        assertEquals(F, rotor.cipherInwards(A));
        assertEquals(Z, rotor.cipherInwards(M));
        assertEquals(M, rotor.cipherInwards(Z));

        rotor.changePositionTo(X);
        assertEquals(U, rotor.cipherInwards(A));
        assertEquals(C, rotor.cipherInwards(M));
        assertEquals(E, rotor.cipherInwards(Z));

        rotor.changePositionTo(Y);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(P, rotor.cipherInwards(M));
        assertEquals(T, rotor.cipherInwards(Z));

        rotor.changePositionTo(Z);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(U, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsInDifferentPositionsWithDefaultRingSetting() {
        rotor.changePositionTo(B);
        assertEquals(V, rotor.cipherOutwards(A));
        assertEquals(J, rotor.cipherOutwards(M));
        assertEquals(T, rotor.cipherOutwards(Z));

        rotor.changePositionTo(M);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(C, rotor.cipherOutwards(M));
        assertEquals(S, rotor.cipherOutwards(Z));

        rotor.changePositionTo(Z);
        assertEquals(K, rotor.cipherOutwards(A));
        assertEquals(F, rotor.cipherOutwards(M));
        assertEquals(P, rotor.cipherOutwards(Z));
    }

    @Test
    void cipherInwardsDefaultPositionRingSettingB() {
        rotor.ringSetting(B);

        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(F, rotor.cipherInwards(B));
        assertEquals(L, rotor.cipherInwards(C));
        assertEquals(N, rotor.cipherInwards(D));
        assertEquals(G, rotor.cipherInwards(E));
        assertEquals(M, rotor.cipherInwards(F));
        assertEquals(H, rotor.cipherInwards(G));
        assertEquals(E, rotor.cipherInwards(H));
        assertEquals(R, rotor.cipherInwards(I));
        assertEquals(W, rotor.cipherInwards(J));
        assertEquals(A, rotor.cipherInwards(K));
        assertEquals(O, rotor.cipherInwards(L));
        assertEquals(U, rotor.cipherInwards(M));
        assertEquals(P, rotor.cipherInwards(N));
        assertEquals(X, rotor.cipherInwards(O));
        assertEquals(Z, rotor.cipherInwards(P));
        assertEquals(I, rotor.cipherInwards(Q));
        assertEquals(Y, rotor.cipherInwards(R));
        assertEquals(V, rotor.cipherInwards(S));
        assertEquals(T, rotor.cipherInwards(T));
        assertEquals(Q, rotor.cipherInwards(U));
        assertEquals(B, rotor.cipherInwards(V));
        assertEquals(J, rotor.cipherInwards(W));
        assertEquals(C, rotor.cipherInwards(X));
        assertEquals(S, rotor.cipherInwards(Y));
        assertEquals(D, rotor.cipherInwards(Z));
    }

    @Test
    void cipherInwardsDifferentPositionsAndRingSettings() {
        rotor.ringSetting(M);
        rotor.changePositionTo(A);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.ringSetting(M);
        rotor.changePositionTo(G);
        assertEquals(G, rotor.cipherInwards(A));
        assertEquals(J, rotor.cipherInwards(M));
        assertEquals(V, rotor.cipherInwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(A);
        assertEquals(J, rotor.cipherInwards(A));
        assertEquals(V, rotor.cipherInwards(M));
        assertEquals(D, rotor.cipherInwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(N);
        assertEquals(K, rotor.cipherInwards(A));
        assertEquals(Q, rotor.cipherInwards(M));
        assertEquals(I, rotor.cipherInwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(R);
        assertEquals(A, rotor.cipherInwards(A));
        assertEquals(T, rotor.cipherInwards(M));
        assertEquals(C, rotor.cipherInwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(Z);
        assertEquals(E, rotor.cipherInwards(A));
        assertEquals(O, rotor.cipherInwards(M));
        assertEquals(J, rotor.cipherInwards(Z));
    }

    @Test
    void cipherOutwardsDefaultPositionRingSettingL() {
        rotor.ringSetting(L);

        assertEquals(A, rotor.cipherOutwards(S));
        assertEquals(B, rotor.cipherOutwards(I));
        assertEquals(C, rotor.cipherOutwards(F));
        assertEquals(D, rotor.cipherOutwards(D));
        assertEquals(E, rotor.cipherOutwards(A));
        assertEquals(F, rotor.cipherOutwards(L));
        assertEquals(G, rotor.cipherOutwards(T));
        assertEquals(H, rotor.cipherOutwards(M));
        assertEquals(I, rotor.cipherOutwards(C));
        assertEquals(J, rotor.cipherOutwards(N));
        assertEquals(K, rotor.cipherOutwards(U));
        assertEquals(L, rotor.cipherOutwards(P));
        assertEquals(M, rotor.cipherOutwards(V));
        assertEquals(N, rotor.cipherOutwards(X));
        assertEquals(O, rotor.cipherOutwards(Q));
        assertEquals(P, rotor.cipherOutwards(W));
        assertEquals(Q, rotor.cipherOutwards(R));
        assertEquals(R, rotor.cipherOutwards(O));
        assertEquals(S, rotor.cipherOutwards(B));
        assertEquals(T, rotor.cipherOutwards(G));
        assertEquals(U, rotor.cipherOutwards(K));
        assertEquals(V, rotor.cipherOutwards(Y));
        assertEquals(W, rotor.cipherOutwards(E));
        assertEquals(X, rotor.cipherOutwards(Z));
        assertEquals(Y, rotor.cipherOutwards(H));
        assertEquals(Z, rotor.cipherOutwards(J));
    }

    @Test
    void cipherOutwardsDifferentPositionsAndRingSettings() {
        rotor.ringSetting(M);
        rotor.changePositionTo(A);
        assertEquals(Y, rotor.cipherOutwards(A));
        assertEquals(G, rotor.cipherOutwards(M));
        assertEquals(W, rotor.cipherOutwards(Z));

        rotor.ringSetting(M);
        rotor.changePositionTo(G);
        assertEquals(X, rotor.cipherOutwards(A));
        assertEquals(L, rotor.cipherOutwards(M));
        assertEquals(R, rotor.cipherOutwards(Z));

        rotor.ringSetting(M);
        rotor.changePositionTo(V);
        assertEquals(Q, rotor.cipherOutwards(A));
        assertEquals(Z, rotor.cipherOutwards(M));
        assertEquals(M, rotor.cipherOutwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(F);
        assertEquals(Z, rotor.cipherOutwards(A));
        assertEquals(M, rotor.cipherOutwards(M));
        assertEquals(X, rotor.cipherOutwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(R);
        assertEquals(A, rotor.cipherOutwards(A));
        assertEquals(I, rotor.cipherOutwards(M));
        assertEquals(F, rotor.cipherOutwards(Z));

        rotor.ringSetting(Z);
        rotor.changePositionTo(G);
        assertEquals(I, rotor.cipherOutwards(A));
        assertEquals(E, rotor.cipherOutwards(M));
        assertEquals(Y, rotor.cipherOutwards(Z));
    }

}