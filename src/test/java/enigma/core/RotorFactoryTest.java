package enigma.core;

import enigma.core.devices.Rotor;
import enigma.core.util.Alphabet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RotorFactoryTest.
 *
 * @author diegodc 2017-02-05
 */
class RotorFactoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void newRotorI() {
        Rotor rotor = RotorFactory.rotorI();
        rotor.changePositionTo(Alphabet.Q);
        assertTrue(rotor.isAtTurnoverPosition());
    }

    @Test
    void newRotorII() {
        Rotor rotor = RotorFactory.rotorII();
        rotor.changePositionTo(Alphabet.E);
        assertTrue(rotor.isAtTurnoverPosition());
    }

    @Test
    void newRotorIII() {
        Rotor rotor = RotorFactory.rotorIII();
        rotor.changePositionTo(Alphabet.V);
        assertTrue(rotor.isAtTurnoverPosition());
    }

    @Test
    void newRotorIV() {
        Rotor rotor = RotorFactory.rotorIV();
        rotor.changePositionTo(Alphabet.J);
        assertTrue(rotor.isAtTurnoverPosition());
    }

    @Test
    void newRotorV() {
        Rotor rotor = RotorFactory.rotorV();
        rotor.changePositionTo(Alphabet.Z);
        assertTrue(rotor.isAtTurnoverPosition());
    }

}
