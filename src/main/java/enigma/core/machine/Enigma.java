package enigma.core.machine;

import enigma.core.devices.Plugboard;
import enigma.core.devices.Reflector;
import enigma.core.devices.Rotor;
import enigma.core.util.Letter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the top level class of the enigma system.
 *
 * @author diegodc 2017-02-17
 */
public class Enigma {

    private final Plugboard plugboard = new Plugboard();
    private final Reflector reflector;
    private final RotorMechanism mechanism;

    private int numberOfRotors;
    private final List<Rotor> rotors = new ArrayList<>();

    private StringBuilder cipherText;

    public Enigma(Reflector reflector, Rotor leftRotor, Rotor middleRotor, Rotor rightRotor) {
        this.reflector = reflector;
        numberOfRotors = 3;
        rotors.add(leftRotor);
        rotors.add(middleRotor);
        rotors.add(rightRotor);

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    public Enigma(Reflector thinReflector, Rotor thin, Rotor leftRotor, Rotor middleRotor, Rotor rightRotor) {
        this.reflector = thinReflector;
        numberOfRotors = 4;
        rotors.add(thin);
        rotors.add(leftRotor);
        rotors.add(middleRotor);
        rotors.add(rightRotor);

        mechanism = new RotorMechanism(leftRotor, middleRotor, rightRotor);
    }

    public String cipherMessage(String plainText) {

        cipherText = new StringBuilder(plainText.length());

        for (char letter : plainText.toCharArray()) {
            mechanism.step();
            cipher(letter);
        }

        return cipherText.toString();
    }

    private void cipher(char plainLetter) {
        Letter letter = Letter.fromChar(plainLetter);

        letter = plugboard.cipher(letter);

        for (int i = numberOfRotors -1; i >= 0; i--)
            letter = rotors.get(i).cipherInwards(letter);

        letter = reflector.reflect(letter);

        for (Rotor rotor : rotors)
            letter = rotor.cipherOutwards(letter);

        letter = plugboard.cipher(letter);

        cipherText.append(letter);
    }

    public String getRotorsPositions() {
        String positions = "";
        for (Rotor rotor : rotors)
            positions += rotor.getPosition().toString();
        return positions;
    }

    public String getRingSettings() {
        String ringSettings = "";
        for (Rotor rotor : rotors)
            ringSettings += rotor.getRingSetting().toString();
        return ringSettings;
    }

    public Enigma changeRotorsPositions(String positions) {
        validateRotorSettings(positions);
        int i = 0;
        for (Rotor rotor : rotors) {
            rotor.changePositionTo(Letter.fromChar(positions.charAt(i)));
            i++;
        }
        return this;
    }

    private void validateRotorSettings(String settings) {
        if (!settings.matches("[A-Z]{" + numberOfRotors + "}"))
            throw new IllegalArgumentException();
    }

    public Enigma changeRingSettings(String ringsSettings) {
        validateRotorSettings(ringsSettings);
        int i = 0;
        for (Rotor rotor : rotors) {
            rotor.ringSetting(Letter.fromChar(ringsSettings.charAt(i)));
            i++;
        }
        return this;
    }

    public Enigma setPlugboard(String settings) {
        plugboard.reset();
        validatePlugboardSettings(settings);
        String[] pairs = settings.split(" ");
        for (String pair : pairs) {
            setPair(pair);
        }
        return this;
    }

    private void validatePlugboardSettings(String settings) {
        if (!settings.matches("[A-Z]{2}(\\s[A-Z]{2})*"))
            throw new IllegalArgumentException();
    }

    private void setPair(String pair) {
        char letter = pair.charAt(0);
        char pairedLetter = pair.charAt(1);
        plugboard.pairLetters(Letter.fromChar(letter), Letter.fromChar(pairedLetter));
    }

    public void resetSettings() {
        changeRotorsPositions("AAA");
        changeRingSettings("AAA");
        plugboard.reset();
    }

}
