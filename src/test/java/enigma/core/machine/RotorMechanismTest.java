package enigma.core.machine;

import enigma.RotorFactory;
import enigma.core.devices.Rotor;

import enigma.core.util.Alphabet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the step sequence
 *
 * Examples taken from:
 * http://enigma.louisedade.co.uk/enigma.html
 *
 * @author diegodc 2017-02-03
 */
class RotorMechanismTest {

    private RotorMechanism mechanism;
    private Rotor leftRotor;
    private Rotor middleRotor;
    private Rotor rightRotor;

    @BeforeEach
    void setUp() {
        leftRotor = RotorFactory.rotorI();
        middleRotor = RotorFactory.rotorII();
        rightRotor = RotorFactory.rotorIII();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    @Test
    void normalSequence() {

        rightRotor.changePositionTo(Alphabet.U);
        assertEquals(Alphabet.U, rightRotor.getPosition());
        assertEquals(Alphabet.A, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.V, rightRotor.getPosition());
        assertEquals(Alphabet.A, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.W, rightRotor.getPosition());
        assertEquals(Alphabet.B, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.X, rightRotor.getPosition());
        assertEquals(Alphabet.B, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence() {
        rightRotor.changePositionTo(Alphabet.U);
        middleRotor.changePositionTo(Alphabet.D);

        assertEquals(Alphabet.U, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.V, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.W, rightRotor.getPosition());
        assertEquals(Alphabet.E, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.X, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.Y, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());
    }

    @Test
    void middleRotorSetAtTurnover() {

        rightRotor.changePositionTo(Alphabet.A);
        middleRotor.changePositionTo(Alphabet.E);

        assertEquals(Alphabet.A, rightRotor.getPosition());
        assertEquals(Alphabet.E, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.B, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence2() {

        leftRotor = RotorFactory.rotorIII();
        middleRotor = RotorFactory.rotorII();
        rightRotor = RotorFactory.rotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePositionTo(Alphabet.O);
        middleRotor.changePositionTo(Alphabet.D);

        assertEquals(Alphabet.O, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.P, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.Q, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.R, rightRotor.getPosition());
        assertEquals(Alphabet.E, middleRotor.getPosition());
        assertEquals(Alphabet.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.S, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.T, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.U, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.B, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence3() {

        leftRotor = RotorFactory.rotorIII();
        middleRotor = RotorFactory.rotorII();
        rightRotor = RotorFactory.rotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePositionTo(Alphabet.O);
        middleRotor.changePositionTo(Alphabet.D);
        leftRotor.changePositionTo(Alphabet.K);

        assertEquals(Alphabet.O, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.P, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.Q, rightRotor.getPosition());
        assertEquals(Alphabet.D, middleRotor.getPosition());
        assertEquals(Alphabet.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.R, rightRotor.getPosition());
        assertEquals(Alphabet.E, middleRotor.getPosition());
        assertEquals(Alphabet.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.S, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.L, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.T, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.L, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Alphabet.U, rightRotor.getPosition());
        assertEquals(Alphabet.F, middleRotor.getPosition());
        assertEquals(Alphabet.L, leftRotor.getPosition());
    }

}