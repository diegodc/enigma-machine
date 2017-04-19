package enigma.core.machine;

import enigma.core.devices.NotchedRotor;
import enigma.core.devices.Rotor;

/**
 * Responsible for the rotor's stepping motion.
 *
 * @author diegodc 2017-02-03.
 */
class RotorMechanism {

    private Rotor leftRotor;
    private NotchedRotor middleRotor;
    private NotchedRotor rightRotor;

    private boolean shouldAdvanceMiddleRotor;
    private boolean shouldAdvanceLeftRotor;

    RotorMechanism(Rotor leftRotor, NotchedRotor middleRotor, NotchedRotor rightRotor) {
        this.leftRotor = leftRotor;
        this.middleRotor = middleRotor;
        this.rightRotor = rightRotor;
    }

    void step() {
        update();
        stepRotors();
    }

    private void update() {
        shouldAdvanceMiddleRotor = rightRotor.isAtTurnoverPosition();
        shouldAdvanceLeftRotor = middleRotor.isAtTurnoverPosition();
    }

    private void stepRotors() {
        rightRotor.step();

        if (shouldAdvanceMiddleRotor)
            middleRotor.step();

        if (shouldAdvanceLeftRotor) {
            middleRotor.step();
            leftRotor.step();
        }
    }

}
