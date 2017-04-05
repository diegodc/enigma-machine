package enigma.core.machine;

import enigma.core.util.Alphabet;
import enigma.core.devices.Rotor;

/**
 * Responsible for the rotor's stepping motion.
 *
 * @author diegodc 2017-02-03
 */
public class RotorMechanism {

    private Rotor leftRotor;
    private Rotor middleRotor;
    private Rotor rightRotor;

    private boolean middleRotorPawlEngaged;
    private boolean leftRotorPawlEngaged;

    public RotorMechanism(Rotor leftRotor, Rotor middleRotor, Rotor rightRotor) {
        this.leftRotor = leftRotor;
        this.middleRotor = middleRotor;
        this.rightRotor = rightRotor;
    }

    public Alphabet leftRotorPosition() {
        return leftRotor.getPosition();
    }

    public Alphabet middleRotorPosition() {
        return middleRotor.getPosition();
    }

    public Alphabet rightRotorPosition() {
        return rightRotor.getPosition();
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
