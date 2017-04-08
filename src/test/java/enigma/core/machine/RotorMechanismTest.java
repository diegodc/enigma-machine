package enigma.core.machine;

import enigma.core.RotorFactory;
import enigma.core.devices.Rotor;
import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        rightRotor.changePositionTo(Letter.U);
        assertEquals(Letter.U, rightRotor.getPosition());
        assertEquals(Letter.A, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.V, rightRotor.getPosition());
        assertEquals(Letter.A, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.W, rightRotor.getPosition());
        assertEquals(Letter.B, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.X, rightRotor.getPosition());
        assertEquals(Letter.B, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence() {
        rightRotor.changePositionTo(Letter.U);
        middleRotor.changePositionTo(Letter.D);

        assertEquals(Letter.U, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.V, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.W, rightRotor.getPosition());
        assertEquals(Letter.E, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.X, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.Y, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());
    }

    @Test
    void middleRotorSetAtTurnover() {

        rightRotor.changePositionTo(Letter.A);
        middleRotor.changePositionTo(Letter.E);

        assertEquals(Letter.A, rightRotor.getPosition());
        assertEquals(Letter.E, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.B, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence2() {

        leftRotor = RotorFactory.rotorIII();
        middleRotor = RotorFactory.rotorII();
        rightRotor = RotorFactory.rotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePositionTo(Letter.O);
        middleRotor.changePositionTo(Letter.D);

        assertEquals(Letter.O, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.P, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.Q, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.R, rightRotor.getPosition());
        assertEquals(Letter.E, middleRotor.getPosition());
        assertEquals(Letter.A, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.S, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.T, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.U, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.B, leftRotor.getPosition());
    }

    @Test
    void doubleStepSequence3() {

        leftRotor = RotorFactory.rotorIII();
        middleRotor = RotorFactory.rotorII();
        rightRotor = RotorFactory.rotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePositionTo(Letter.O);
        middleRotor.changePositionTo(Letter.D);
        leftRotor.changePositionTo(Letter.K);

        assertEquals(Letter.O, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.P, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.Q, rightRotor.getPosition());
        assertEquals(Letter.D, middleRotor.getPosition());
        assertEquals(Letter.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.R, rightRotor.getPosition());
        assertEquals(Letter.E, middleRotor.getPosition());
        assertEquals(Letter.K, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.S, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.L, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.T, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.L, leftRotor.getPosition());

        mechanism.step();
        assertEquals(Letter.U, rightRotor.getPosition());
        assertEquals(Letter.F, middleRotor.getPosition());
        assertEquals(Letter.L, leftRotor.getPosition());
    }

}