package enigma.core.devices;

import enigma.core.util.Letter;

import java.util.Arrays;

/**
 * This rotor has one or more turnover notches. Each notch is at a certain position.
 * When the rotor reaches turnover position, the rotor mechanism steps the rotor.
 *
 * @author diegodc 2017-04-07.
 */
public class NotchedRotor extends Rotor {

    private Letter[] turnoverPositions;

    /**
     * Creates a Notched Rotor with the given wiring and turnover positions.
     *
     * @param wiring            the wiring of the rotor
     * @param turnoverPositions an array with the turnover positions
     */
    public NotchedRotor(Letter[] wiring, Letter[] turnoverPositions) {
        super(wiring);
        this.turnoverPositions = turnoverPositions;
    }

    /**
     * Returns true if the rotor is currently at turnover position, false otherwise.
     *
     * @return true if the rotor is at turnover position
     */
    public boolean isAtTurnoverPosition() {
        return Arrays.stream(turnoverPositions)
                .anyMatch(letter -> letter == currentPosition());
    }

}
