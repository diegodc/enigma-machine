package enigma.core.machine;

import enigma.core.devices.NotchedRotor;
import enigma.core.util.Letter;
import enigma.factory.DeviceFactory;
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
    private NotchedRotor leftRotor;
    private NotchedRotor middleRotor;
    private NotchedRotor rightRotor;

    @BeforeEach
    void setUp() {
        leftRotor = DeviceFactory.I();
        middleRotor = DeviceFactory.II();
        rightRotor = DeviceFactory.III();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    @Test
    void normalSequence() {

        rightRotor.setPosition(Letter.U);
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
        rightRotor.setPosition(Letter.U);
        middleRotor.setPosition(Letter.D);

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

        rightRotor.setPosition(Letter.A);
        middleRotor.setPosition(Letter.E);

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

        leftRotor = DeviceFactory.III();
        middleRotor = DeviceFactory.II();
        rightRotor = DeviceFactory.I();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.setPosition(Letter.O);
        middleRotor.setPosition(Letter.D);

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

        leftRotor = DeviceFactory.III();
        middleRotor = DeviceFactory.II();
        rightRotor = DeviceFactory.I();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.setPosition(Letter.O);
        middleRotor.setPosition(Letter.D);
        leftRotor.setPosition(Letter.K);

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