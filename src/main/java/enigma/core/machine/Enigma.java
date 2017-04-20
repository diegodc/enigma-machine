package enigma.core.machine;

import enigma.core.devices.NotchedRotor;
import enigma.core.devices.Plugboard;
import enigma.core.devices.Reflector;
import enigma.core.devices.Rotor;
import enigma.core.util.Letter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the top level class of the enigma core system.
 *
 * @author diegodc 2017-02-17.
 */
public class Enigma {

    private int numberOfRotors;
    private final RotorMechanism mechanism;
    private final List<Rotor> rotors = new ArrayList<>();
    private final Reflector reflector;
    private final Plugboard plugboard = new Plugboard();

    public Enigma(Reflector wideReflector,
                  NotchedRotor leftRotor,
                  NotchedRotor middleRotor,
                  NotchedRotor rightRotor) {

        reflector = wideReflector;

        numberOfRotors = 3;

        rotors.add(leftRotor);
        rotors.add(middleRotor);
        rotors.add(rightRotor);

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    public Enigma(Reflector thinReflector,
                  Rotor thin,
                  NotchedRotor leftRotor,
                  NotchedRotor middleRotor,
                  NotchedRotor rightRotor) {

        reflector = thinReflector;

        numberOfRotors = 4;

        rotors.add(thin);
        rotors.add(leftRotor);
        rotors.add(middleRotor);
        rotors.add(rightRotor);

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    public String cipherMessage(String plainText) {
        StringBuilder cipherText = new StringBuilder(plainText.length());

        for (char letter : plainText.toCharArray()) {
            mechanism.step();
            cipherText.append(cipher(letter));
        }

        return cipherText.toString();
    }

    private Letter cipher(char plainLetter) {
        Letter letter = Letter.fromChar(plainLetter);

        letter = plugboard.swap(letter);
        letter = cipherInwards(letter);
        letter = reflector.reflect(letter);
        letter = cipherOutwards(letter);
        letter = plugboard.swap(letter);

        return letter;
    }

    private Letter cipherInwards(Letter letter) {
        for (int i = numberOfRotors -1; i >= 0; i--)
            letter = rotors.get(i).cipherInwards(letter);
        return letter;
    }

    private Letter cipherOutwards(Letter letter) {
        for (Rotor rotor : rotors)
            letter = rotor.cipherOutwards(letter);
        return letter;
    }

    public String getRotorsPositions() {
        StringBuilder positions = new StringBuilder();
        for (Rotor rotor : rotors)
            positions.append(rotor.currentPosition().asChar());
        return positions.toString();
    }

    public String getRingSettings() {
        StringBuilder settings = new StringBuilder();
        for (Rotor rotor : rotors)
            settings.append(rotor.ringSetting().asChar());
        return settings.toString();
    }

    public Enigma changeRotorsPositions(String positions) {
        validateRotorSettings(positions);
        int i = 0;
        for (Rotor rotor : rotors) {
            rotor.changePosition(Letter.fromChar(positions.charAt(i)));
            i++;
        }
        return this;
    }

    private void validateRotorSettings(String settings) {
        if (!settings.matches("[A-Z]{" + numberOfRotors + "}"))
            throw new InvalidSetting("Invalid rotor setting: " + settings);
    }

    public Enigma changeRingSettings(String ringsSettings) {
        validateRotorSettings(ringsSettings);
        int i = 0;
        for (Rotor rotor : rotors) {
            rotor.changeRingSetting(Letter.fromChar(ringsSettings.charAt(i)));
            i++;
        }
        return this;
    }

    public Enigma setPlugboard(String settings) {
        plugboard.disconnectAllPairs();
        validatePlugboardSettings(settings);

        setPlugboardPairs(parseLetterPairs(settings));

        return this;
    }

    public void resetSettings() {
        changeRotorsPositions("AAA");
        changeRingSettings("AAA");
        plugboard.disconnectAllPairs();
    }

    private void validatePlugboardSettings(String settings) {
        if (!settings.matches("[A-Z]{2}(\\s[A-Z]{2})*"))
            throw new InvalidSetting("Invalid plugboard setting: " + settings);
    }

    private String[] parseLetterPairs(String settings) {
        return settings.split(" ");
    }

    private void setPlugboardPairs(String[] pairs) {
        for (String pair : pairs) {
            setPair(pair);
        }
    }

    private void setPair(String pair) {
        Letter letter1 = Letter.fromChar(pair.charAt(0));
        Letter letter2 = Letter.fromChar(pair.charAt(1));

        plugboard.connect(letter1, letter2);
    }

    public class InvalidSetting extends RuntimeException {
        public InvalidSetting(String message) {
            super(message);
        }
    }

}
