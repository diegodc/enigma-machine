package enigma.core.machine;

import enigma.core.devices.NotchedRotor;

/**
 * Responsible for the rotor's stepping motion.
 *
 * @author diegodc 2017-02-03.
 */
public class RotorMechanism {

    private NotchedRotor leftRotor;
    private NotchedRotor middleRotor;
    private NotchedRotor rightRotor;

    private boolean middleRotorPawlEngaged;
    private boolean leftRotorPawlEngaged;

    public RotorMechanism(NotchedRotor leftRotor, NotchedRotor middleRotor, NotchedRotor rightRotor) {
        this.leftRotor = leftRotor;
        this.middleRotor = middleRotor;
        this.rightRotor = rightRotor;
    }

    public void step() {
        updatePawlPosition();
        stepRotors();
    }

    private void updatePawlPosition() {
        middleRotorPawlEngaged = rightRotor.isAtTurnoverPosition();
        leftRotorPawlEngaged = middleRotor.isAtTurnoverPosition();
    }

    private void stepRotors() {
        rightRotor.step();

        if (middleRotorPawlEngaged)
            middleRotor.step();

        if (leftRotorPawlEngaged) {
            middleRotor.step();
            leftRotor.step();
        }
    }

}
