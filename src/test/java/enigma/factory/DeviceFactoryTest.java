package enigma.factory;

import enigma.core.devices.NotchedRotor;
import enigma.core.devices.Reflector;
import enigma.core.devices.Rotor;
import enigma.core.util.Letter;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * DeviceFactoryTest.
 *
 * @author diegodc 2017-02-05
 */
class DeviceFactoryTest {

    @Test
    void testConfiguration_RotorI() {
        NotchedRotor rotor = DeviceFactory.I();
        rotor.setPosition(Letter.Q);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {E,K,M,F,L,G,D,Q,V,Z,N,T,O,W,Y,H,X,U,S,P,A,I,B,R,C,J};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorII() {
        NotchedRotor rotor = DeviceFactory.II();
        rotor.setPosition(Letter.E);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {A,J,D,K,S,I,R,U,X,B,L,H,W,T,M,C,Q,G,Z,N,P,Y,F,V,O,E};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorIII() {
        NotchedRotor rotor = DeviceFactory.III();
        rotor.setPosition(Letter.V);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {B,D,F,H,J,L,C,P,R,T,X,V,Z,N,Y,E,I,W,G,A,K,M,U,S,Q,O};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorIV() {
        NotchedRotor rotor = DeviceFactory.IV();
        rotor.setPosition(Letter.J);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {E,S,O,V,P,Z,J,A,Y,Q,U,I,R,H,X,L,N,F,T,G,K,D,C,M,W,B};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorV() {
        NotchedRotor rotor = DeviceFactory.V();
        rotor.setPosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {V,Z,B,R,G,I,T,Y,U,P,S,D,N,H,L,X,A,W,M,J,Q,O,F,E,C,K};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVI() {
        NotchedRotor rotor = DeviceFactory.VI();

        rotor.setPosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.setPosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {J,P,G,V,O,U,M,F,Y,Q,B,E,N,H,Z,R,D,K,A,S,X,L,I,C,T,W};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVII() {
        NotchedRotor rotor = DeviceFactory.VII();

        rotor.setPosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.setPosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {N,Z,J,H,G,R,C,X,M,Y,S,W,B,O,U,F,A,I,V,L,P,E,K,Q,D,T};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorVIII() {
        NotchedRotor rotor = DeviceFactory.VIII();

        rotor.setPosition(Letter.M);
        assertTrue(rotor.isAtTurnoverPosition());

        rotor.setPosition(Letter.Z);
        assertTrue(rotor.isAtTurnoverPosition());

        Letter[] expectedLetters = {F,K,Q,H,T,L,X,O,C,B,J,S,P,D,Z,R,A,M,E,W,N,I,U,Y,G,V};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorBeta() {
        Rotor rotor = DeviceFactory.beta();

        Letter[] expectedLetters = {L,E,Y,J,V,C,N,I,X,W,P,B,Q,M,D,R,T,A,K,Z,G,F,U,H,O,S};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_RotorGamma() {
        Rotor rotor = DeviceFactory.gamma();

        Letter[] expectedLetters = {F,S,O,K,A,N,U,E,R,H,M,B,T,I,Y,C,W,L,Q,P,Z,X,V,G,J,D};
        verifyWiring(rotor, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorA() {
        Reflector reflector = DeviceFactory.A();

        Letter[] expectedLetters = {E,J,M,Z,A,L,Y,X,V,B,W,F,C,R,Q,U,O,N,T,S,P,I,K,H,G,D};
        verifyReflectorWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorB() {
        Reflector reflector = DeviceFactory.B();

        Letter[] expectedLetters = {Y,R,U,H,Q,S,L,D,P,X,N,G,O,K,M,I,E,B,F,Z,C,W,V,J,A,T};
        verifyReflectorWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorC() {
        Reflector reflector = DeviceFactory.C();

        Letter[] expectedLetters = {F,V,P,J,I,A,O,Y,E,D,R,Z,X,W,G,C,T,K,U,Q,S,B,N,M,H,L};
        verifyReflectorWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorThinB() {
        Reflector reflector = DeviceFactory.thinB();

        Letter[] expectedLetters = {E,N,K,Q,A,U,Y,W,J,I,C,O,P,B,L,M,D,X,Z,V,F,T,H,R,G,S};
        verifyReflectorWiring(reflector, expectedLetters);
    }

    @Test
    void testConfiguration_ReflectorThinC() {
        Reflector reflector = DeviceFactory.thinC();

        Letter[] expectedLetters = {R,D,O,B,J,N,T,K,V,E,H,M,L,F,C,W,Z,A,X,G,Y,I,P,S,U,Q};
        verifyReflectorWiring(reflector, expectedLetters);
    }

    private void verifyWiring(Rotor rotor, Letter[] expectedLetters) {
        rotor.setPosition(A);
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, rotor.cipherInwards(actual));
            actual = actual.nextLetter();
        }
    }

    private void verifyReflectorWiring(Reflector reflector, Letter[] expectedLetters) {
        Letter actual = A;

        for (Letter expected : expectedLetters) {
            assertEquals(expected, reflector.reflect(actual));
            actual = actual.nextLetter();
        }
    }

}
