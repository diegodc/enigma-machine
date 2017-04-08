package enigma.core.util;

import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * LetterTest
 *
 * @author diegodc 2017-02-05
 */
class LetterTest {

    @Test
    void shouldReturnTheNextLetterDownTheAlphabet() {

        Letter[] letters = {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
        Letter[] expectedLetters = {B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,A};

        for (int i = 0; i < 26; i++) {
            Letter letter = letters[i];
            Letter expectedLetter = expectedLetters[i];
            assertEquals(expectedLetter, letter.nextLetter());
        }
    }

    @Test
    void shouldShiftLetterDownOrUpTheAlphabet() {
        assertEquals(A, A.shiftBy(0));
        assertEquals(B, A.shiftBy(1));
        assertEquals(Z, A.shiftBy(-1));

        assertEquals(A, A.shiftBy(26));
        assertEquals(A, A.shiftBy(-26));

        assertEquals(A, Z.shiftBy(1));
        assertEquals(Y, Z.shiftBy(-1));

        assertEquals(G, D.shiftBy(3));
        assertEquals(U, N.shiftBy(7));
        assertEquals(T, N.shiftBy(-20));

        assertEquals(G, D.shiftBy(29));
        assertEquals(G, D.shiftBy(55));

        assertEquals(Z, D.shiftBy(-30));
        assertEquals(Z, D.shiftBy(-56));
    }

    @Test
    void testAllShiftPositionsDownTheAlphabet() {
        Letter expectedLetter = A;

        for (int i = 0; i < 26; i++) {
            assertEquals(expectedLetter, A.shiftBy(i));
            expectedLetter = expectedLetter.nextLetter();
        }
    }

    @Test
    void testAllShiftPositionsUpTheAlphabet() {
        Letter expectedLetter = Z;

        for (int i = -26; i < 1; i++) {
            assertEquals(expectedLetter, Z.shiftBy(i));
            expectedLetter = expectedLetter.nextLetter();
        }
    }

    @Test
    void shouldGetLetterFromChar() {
        assertEquals(A, Letter.fromChar('A'));
        assertEquals(A, Letter.fromChar('a'));
    }

    @Test
    void invalidCharShouldThrowException() {
        assertThrows(IllegalArgumentException.class,() -> Letter.fromChar('4'));
        assertThrows(IllegalArgumentException.class,() -> Letter.fromChar('.'));
        assertThrows(IllegalArgumentException.class,() -> Letter.fromChar('-'));
    }

    @Test
    void shouldConvertLetterToChar() {
        assertEquals('A', A.asChar());
        assertEquals('J', J.asChar());
        assertEquals('P', P.asChar());
        assertEquals('Z', Z.asChar());
    }

    @Test
    void testConversionFromCharToLetterWithAllChars() {

        Letter[] letters = Letter.values();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < letters.length; i++) {
            assertEquals(letters[i], Letter.fromChar(chars.charAt(i)));
            assertEquals(letters[i], Letter.fromChar(lowerCaseChars.charAt(i)));
        }
    }

    @Test
    void testConversionFromLetterToCharWithAllLetters() {
        String expectedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Letter letter = A;
        for (int i = 0; i < 26; i++) {
            assertEquals(expectedChars.charAt(i), letter.asChar());
            letter = letter.nextLetter();
        }
    }

}