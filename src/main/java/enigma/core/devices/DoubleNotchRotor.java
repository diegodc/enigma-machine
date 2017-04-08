package enigma.core.devices;

import enigma.core.util.Letter;

/**
 * Doble notched rotor of the Enigma machine.
 *
 * @author diegodc 2017-02-19
 */
public class DoubleNotchRotor extends Rotor {

    private Letter secondTurnoverPosition;

    public DoubleNotchRotor(Letter[] wiring, Letter[] turnoverPositions) {
        super(wiring, turnoverPositions[0]);
        secondTurnoverPosition = turnoverPositions[1];
    }

    @Override
    public boolean isAtTurnoverPosition() {
        return super.isAtTurnoverPosition() || super.getPosition() == secondTurnoverPosition;
    }
}
