package enigma.core.util;

import org.junit.jupiter.api.Test;

import static enigma.core.util.Alphabet.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AlphabetTest
 *
 * @author diegodc 2017-02-05
 */
class AlphabetTest {

    @Test
    void nextLetter() {
        assertEquals(B, A.nextLetter());
        assertEquals(C, B.nextLetter());
        assertEquals(D, C.nextLetter());
        assertEquals(E, D.nextLetter());
        assertEquals(F, E.nextLetter());
        assertEquals(G, F.nextLetter());
        assertEquals(H, G.nextLetter());
        assertEquals(I, H.nextLetter());
        assertEquals(J, I.nextLetter());
        assertEquals(K, J.nextLetter());
        assertEquals(L, K.nextLetter());
        assertEquals(M, L.nextLetter());
        assertEquals(N, M.nextLetter());
        assertEquals(O, N.nextLetter());
        assertEquals(P, O.nextLetter());
        assertEquals(Q, P.nextLetter());
        assertEquals(R, Q.nextLetter());
        assertEquals(S, R.nextLetter());
        assertEquals(T, S.nextLetter());
        assertEquals(U, T.nextLetter());
        assertEquals(V, U.nextLetter());
        assertEquals(W, V.nextLetter());
        assertEquals(X, W.nextLetter());
        assertEquals(Y, X.nextLetter());
        assertEquals(Z, Y.nextLetter());
        assertEquals(A, Z.nextLetter());
    }

    @Test
    void offset() {
        assertEquals(A, A.offsetBy(0));
        assertEquals(B, A.offsetBy(1));
        assertEquals(Z, A.offsetBy(-1));

        assertEquals(A, A.offsetBy(26));
        assertEquals(A, A.offsetBy(-26));

        assertEquals(A, Z.offsetBy(1));
        assertEquals(Y, Z.offsetBy(-1));

        assertEquals(G, D.offsetBy(3));
        assertEquals(U, N.offsetBy(7));
        assertEquals(T, N.offsetBy(-20));

        assertEquals(C, A.offsetBy(2));
        assertEquals(D, A.offsetBy(3));
        assertEquals(E, A.offsetBy(4));
        assertEquals(F, A.offsetBy(5));
        assertEquals(G, A.offsetBy(6));
        assertEquals(H, A.offsetBy(7));
        assertEquals(I, A.offsetBy(8));
        assertEquals(J, A.offsetBy(9));
        assertEquals(K, A.offsetBy(10));
        assertEquals(L, A.offsetBy(11));
        assertEquals(M, A.offsetBy(12));
        assertEquals(N, A.offsetBy(13));
        assertEquals(O, A.offsetBy(14));
        assertEquals(P, A.offsetBy(15));
        assertEquals(Q, A.offsetBy(16));
        assertEquals(R, A.offsetBy(17));
        assertEquals(S, A.offsetBy(18));
        assertEquals(T, A.offsetBy(19));
        assertEquals(U, A.offsetBy(20));
        assertEquals(V, A.offsetBy(21));
        assertEquals(W, A.offsetBy(22));
        assertEquals(X, A.offsetBy(23));
        assertEquals(Y, A.offsetBy(24));
        assertEquals(Z, A.offsetBy(25));

        assertEquals(Y, A.offsetBy(-2));
        assertEquals(X, A.offsetBy(-3));
        assertEquals(W, A.offsetBy(-4));
        assertEquals(V, A.offsetBy(-5));
        assertEquals(U, A.offsetBy(-6));
        assertEquals(T, A.offsetBy(-7));
        assertEquals(S, A.offsetBy(-8));
        assertEquals(R, A.offsetBy(-9));
        assertEquals(Q, A.offsetBy(-10));
        assertEquals(P, A.offsetBy(-11));
        assertEquals(O, A.offsetBy(-12));
        assertEquals(N, A.offsetBy(-13));
        assertEquals(M, A.offsetBy(-14));
        assertEquals(L, A.offsetBy(-15));
        assertEquals(K, A.offsetBy(-16));
        assertEquals(J, A.offsetBy(-17));
        assertEquals(I, A.offsetBy(-18));
        assertEquals(H, A.offsetBy(-19));
        assertEquals(G, A.offsetBy(-20));
        assertEquals(F, A.offsetBy(-21));
        assertEquals(E, A.offsetBy(-22));
        assertEquals(D, A.offsetBy(-23));
        assertEquals(C, A.offsetBy(-24));
        assertEquals(B, A.offsetBy(-25));
    }

    @Test
    void getAlphabetFromChar() {
        assertEquals(A, Alphabet.fromChar('A'));
        assertEquals(A, Alphabet.fromChar('a'));
    }

    @Test
    void getAlphabetFromCharAllCases() {

        Alphabet[] expecteds = Alphabet.values();

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < expecteds.length; i++) {
            assertEquals(expecteds[i], Alphabet.fromChar(chars.charAt(i)));
            assertEquals(expecteds[i], Alphabet.fromChar(lowerCaseChars.charAt(i)));
        }
    }

    @Test
    void getAlphabetAsChar() {
        assertEquals('A', A.asChar());
        assertEquals('J', J.asChar());
        assertEquals('P', P.asChar());
        assertEquals('Z', Z.asChar());
    }

    @Test
    void getAlphabetAsCharAllCases() {
        String expectedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Alphabet letter = A;
        for (int i = 0; i < 26; i++) {
            assertEquals(expectedChars.charAt(i), letter.asChar());
            letter = letter.nextLetter();
        }
    }

}