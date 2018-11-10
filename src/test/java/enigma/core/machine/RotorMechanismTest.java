package enigma.core.machine;

import enigma.core.devices.NotchedRotor;
import enigma.core.util.Letter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.util.Letter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the step sequence
 * <p>
 * Examples taken from:
 * http://enigma.louisedade.co.uk/enigma.html
 *
 * @author diegodc 2017-02-03
 */
class RotorMechanismTest {

    private static final Letter[] ROTOR_I = {E, K, M, F, L, G, D, Q, V, Z, N, T, O, W, Y, H, X, U, S, P, A, I, B, R, C, J};
    private static final Letter[] ROTOR_II = {A, J, D, K, S, I, R, U, X, B, L, H, W, T, M, C, Q, G, Z, N, P, Y, F, V, O, E};
    private static final Letter[] ROTOR_III = {B, D, F, H, J, L, C, P, R, T, X, V, Z, N, Y, E, I, W, G, A, K, M, U, S, Q, O};
    private static final Letter[] ROTOR_I_NOTCH = {Q};
    private static final Letter[] ROTOR_II_NOTCH = {E};
    private static final Letter[] ROTOR_III_NOTCH = {V};

    private RotorMechanism mechanism;
    private NotchedRotor leftRotor;
    private NotchedRotor middleRotor;
    private NotchedRotor rightRotor;

    @BeforeEach
    void setUp() {
        leftRotor = buildRotorI();
        middleRotor = buildRotorII();
        rightRotor = buildRotorIII();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    @Test
    void normalSequence() {

        rightRotor.changePosition(Letter.U);
        assertEquals(Letter.U, rightRotor.currentPosition());
        assertEquals(Letter.A, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.V, rightRotor.currentPosition());
        assertEquals(Letter.A, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.W, rightRotor.currentPosition());
        assertEquals(Letter.B, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.X, rightRotor.currentPosition());
        assertEquals(Letter.B, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());
    }

    @Test
    void doubleStepSequence() {
        rightRotor.changePosition(Letter.U);
        middleRotor.changePosition(Letter.D);

        assertEquals(Letter.U, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.V, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.W, rightRotor.currentPosition());
        assertEquals(Letter.E, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.X, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.Y, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());
    }

    @Test
    void middleRotorSetAtTurnover() {

        rightRotor.changePosition(Letter.A);
        middleRotor.changePosition(Letter.E);

        assertEquals(Letter.A, rightRotor.currentPosition());
        assertEquals(Letter.E, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.B, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());
    }

    @Test
    void doubleStepSequence2() {

        leftRotor = buildRotorIII();
        middleRotor = buildRotorII();
        rightRotor = buildRotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePosition(Letter.O);
        middleRotor.changePosition(Letter.D);

        assertEquals(Letter.O, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.P, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.Q, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.R, rightRotor.currentPosition());
        assertEquals(Letter.E, middleRotor.currentPosition());
        assertEquals(Letter.A, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.S, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.T, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.U, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.B, leftRotor.currentPosition());
    }

    @Test
    void doubleStepSequence3() {

        leftRotor = buildRotorIII();
        middleRotor = buildRotorII();
        rightRotor = buildRotorI();

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);

        rightRotor.changePosition(Letter.O);
        middleRotor.changePosition(Letter.D);
        leftRotor.changePosition(Letter.K);

        assertEquals(Letter.O, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.K, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.P, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.K, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.Q, rightRotor.currentPosition());
        assertEquals(Letter.D, middleRotor.currentPosition());
        assertEquals(Letter.K, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.R, rightRotor.currentPosition());
        assertEquals(Letter.E, middleRotor.currentPosition());
        assertEquals(Letter.K, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.S, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.L, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.T, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.L, leftRotor.currentPosition());

        mechanism.step();
        assertEquals(Letter.U, rightRotor.currentPosition());
        assertEquals(Letter.F, middleRotor.currentPosition());
        assertEquals(Letter.L, leftRotor.currentPosition());
    }

    private NotchedRotor buildRotorI() {
        return new NotchedRotor(ROTOR_I, ROTOR_I_NOTCH);
    }

    private NotchedRotor buildRotorII() {
        return new NotchedRotor(ROTOR_II, ROTOR_II_NOTCH);
    }

    private NotchedRotor buildRotorIII() {
        return new NotchedRotor(ROTOR_III, ROTOR_III_NOTCH);
    }

}