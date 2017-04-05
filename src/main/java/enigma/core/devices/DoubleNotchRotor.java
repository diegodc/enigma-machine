package enigma.core.devices;

import enigma.core.util.Alphabet;

/**
 * Doble notched rotor of the Enigma machine.
 *
 * @author diegodc 2017-02-19
 */
public class DoubleNotchRotor extends Rotor {

    private Alphabet secondTurnoverPosition;

    public DoubleNotchRotor(Alphabet[] wiring, Alphabet[] turnoverPositions) {
        super(wiring, turnoverPositions[0]);
        secondTurnoverPosition = turnoverPositions[1];
    }

    @Override
    public boolean isAtTurnoverPosition() {
        return super.isAtTurnoverPosition() || super.getPosition() == secondTurnoverPosition;
    }
}
