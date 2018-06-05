package enigma.core.devices;

import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * NotchedRotorTest
 *
 * @author diegodc 2017-04-07.
 */
class NotchedRotorTest {

    private static final Letter[] WIRING = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
    private static final Letter[] INCOMPLETE_WIRING = {B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
    private static final Letter[] DUPLICATE_LETTER_WIRING = {A,A,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
    private static final Letter[] SINGLE_NOTCH = {Q};
    private static final Letter[] DOUBLE_NOTCH = {M,Z};

    private NotchedRotor rotor;

    @BeforeEach
    void setUp() {
        rotor = new NotchedRotor(WIRING, SINGLE_NOTCH);
    }

    @Test
    void singleNotchRotorKnowsIfItIsAtTurnoverPosition() {
        NotchedRotor rotor = new NotchedRotor(WIRING, SINGLE_NOTCH);

        rotor.changePosition(P);
        assertFalse(rotor.isAtTurnoverPosition());

        rotor.step();
        assertTrue(rotor.isAtTurnoverPosition());
        rotor.step();
        assertFalse(rotor.isAtTurnoverPosition());
    }

    @Test
    void doubleNotchRotorKnowsIfItIsAtTurnoverPositions() {
        NotchedRotor rotor = new NotchedRotor(WIRING, DOUBLE_NOTCH);

        rotor.changePosition(L);
        assertFalse(rotor.isAtTurnoverPosition());
        rotor.step();
        assertTrue(rotor.isAtTurnoverPosition());
        rotor.step();
        assertFalse(rotor.isAtTurnoverPosition());

        rotor.changePosition(Y);
        assertFalse(rotor.isAtTurnoverPosition());
        rotor.step();
        assertTrue(rotor.isAtTurnoverPosition());
        rotor.step();
        assertFalse(rotor.isAtTurnoverPosition());
    }

    @Test
    void testAllPositionsWithSingleNotchRotor() {
        NotchedRotor rotor = new NotchedRotor(WIRING, SINGLE_NOTCH);

        for (int i = 0; i < 26; i++) {
            if (rotor.currentPosition() == Q)
                assertTrue(rotor.isAtTurnoverPosition());
            else
                assertFalse(rotor.isAtTurnoverPosition());
            rotor.step();
        }
    }

    @Test
    void testAllPositionsWithDoubleNotchRotor() {
        NotchedRotor rotor = new NotchedRotor(WIRING, DOUBLE_NOTCH);

        for (int i = 0; i < 26; i++) {
            if (rotor.currentPosition() == M || rotor.currentPosition() == Z)
                assertTrue(rotor.isAtTurnoverPosition());
            else
                assertFalse(rotor.isAtTurnoverPosition());
            rotor.step();
        }
    }

    @Test
    void creatingRotorWithIncompleteWiringShouldThrowException() {
        assertThrows(MalformedWiring.class, () -> new NotchedRotor(INCOMPLETE_WIRING, SINGLE_NOTCH));
    }

    @Test
    void creatingRotorWithDuplicateLetterInWiringShouldThrowException() {
        assertThrows(MalformedWiring.class, () -> new NotchedRotor(DUPLICATE_LETTER_WIRING, SINGLE_NOTCH));
    }

    @Test
    void verifyItBehavesAsRotor() {
        assertEquals(A, rotor.currentPosition());
        rotor.changePosition(C);
        assertEquals(C, rotor.currentPosition());

        assertEquals(A, rotor.ringSetting());
        rotor.changeRingSetting(B);
        assertEquals(B, rotor.ringSetting());

        rotor.changePosition(A);
        rotor.step();
        assertEquals(B, rotor.currentPosition());

        rotor.changePosition(Z);
        assertEquals(Z, rotor.currentPosition());

        rotor.step();
        assertEquals(A, rotor.currentPosition());
    }

    @Test
    void rotorCyclesThoughAllPositions() {

        Letter[] allPositions = Letter.values();

        for(Letter expectedPosition : allPositions) {
            assertEquals(expectedPosition, rotor.currentPosition());
            rotor.step();
        }

        assertEquals(A, rotor.currentPosition());
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
    void cipherInwardsDefaultPositionRingSettingB() {

        Letter[] expectedLetters = {K,F,L,N,G,M,H,E,R,W,A,O,U,P,X,Z,I,Y,V,T,Q,B,J,C,S,D};
        Letter actual = A;

        rotor.changeRingSetting(B);

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    @Test
    void cipherOutwardsDefaultPositionRingSettingL() {

        Letter[] actualLetters = {S,I,F,D,A,L,T,M,C,N,U,P,V,X,Q,W,R,O,B,G,K,Y,E,Z,H,J};
        Letter expected = A;

        rotor.changeRingSetting(L);

        for (Letter actual : actualLetters) {
            assertEquals(expected, rotor.cipherOutwards(actual));
            expected = expected.nextLetter();
        }
    }

}
