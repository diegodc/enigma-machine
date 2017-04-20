package enigma.core.machine;

import enigma.factory.M3Rotors;
import enigma.factory.M4Rotors;
import enigma.factory.Reflectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * EnigmaTest
 *
 * @author diegodc 2017-02-17
 */
class EnigmaTest {

    private Enigma enigma;

    @BeforeEach
    void setUp() {
        enigma = new Enigma(Reflectors.B.get(),
                            M3Rotors.I.get(),
                            M3Rotors.II.get(),
                            M3Rotors.III.get());
    }

    @Test
    void initialSettings_ShouldBeAAA() {
        assertEquals("AAA", enigma.getRotorsPositions());
        assertEquals("AAA", enigma.getRingSettings());
    }

    @Test
    void changeRotorsPositions() {
        enigma.changeRotorsPositions("POS");
        assertEquals("POS", enigma.getRotorsPositions());

        enigma.changeRotorsPositions("ZZZ");
        assertEquals("ZZZ", enigma.getRotorsPositions());

        enigma.changeRotorsPositions("TDD");
        assertEquals("TDD", enigma.getRotorsPositions());

        enigma.changeRotorsPositions("AAA");
        assertEquals("AAA", enigma.getRotorsPositions());
    }

    @Test
    void changeRingSettings() {
        enigma.changeRingSettings("RGN");
        assertEquals("RGN", enigma.getRingSettings());

        enigma.changeRingSettings("ZZZ");
        assertEquals("ZZZ", enigma.getRingSettings());

        enigma.changeRingSettings("ROT");
        assertEquals("ROT", enigma.getRingSettings());

        enigma.changeRingSettings("AAA");
        assertEquals("AAA", enigma.getRingSettings());
    }

    @Test
    void cipherLetterInInitialSetting() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "A";
        String expectedCipherText = "B";
        assertEquals(expectedCipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherSameLetterMessageInInitialSetting() {
        /* example from https://en.wikipedia.org/wiki/Enigma_rotor_details */
        String plainText = "AAAAA";
        String cipherText = "BDZGO";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSetting() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISATEXTMESSAGE";
        String cipherText = "OPGNDXCRWRMNLNECJZ";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSettingWithRightRotorTurnover() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "TESTMESSAGEWITHRIGHTROTORSTEP";
        String cipherText = "OLPFHNVFLYNQRABFYUJSPDEAOFGKT";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSettingWithDoubleStepSequence() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISALONGERENIGMAMESSAGETHATWILLCAUSEADOUBLESTEPSEQUENCEINTHE" +
                "ROTORMECHANISMTESTINGTHEMOVEMENTOFALLROTORS";
        String cipherText = "OPGNDXCGMHUTIBOKJDPYYILOLZGACJDXOWULFJNNIFVZGTWRAFMHCZYYETUXJK" +
                "PLFEJLDULKGJLWIMMNODWEEGYHNWPXFOUXXEVHPVFENX";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithKeyABC() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        enigma.changeRotorsPositions("ABC");

        String plainText = "AEFAEJXXBNXYJTY";
        String cipherText = "CONGRATULATIONS";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithKeySetForRotorTurnover() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        enigma.changeRotorsPositions("ABR");

        String plainText = "MABEKGZXSG";
        String cipherText = "TURNMIDDLE";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithKeySetForDoubleStep() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        enigma.changeRotorsPositions("ADS");

        String plainText = "RZFOGFYHPL";
        String cipherText = "TURNSTHREE";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherStringWithRotorsInRingSettingB() {
        /* example from https://en.wikipedia.org/wiki/Enigma_rotor_details */
        enigma.changeRingSettings("BBB");

        String plainText = "AAAAA";
        String cipherText = "EWTYX";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithKeyAndRingSetting() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        enigma.changeRingSettings("JNU")
                .changeRotorsPositions("XYZ");

        String plainText = "QKTPEBZIUK";
        String cipherText = "GOODRESULT";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void setPlugboard() {
        assertEquals("OZHFXR", enigma.cipherMessage("TTTTTT"));

        enigma.changeRotorsPositions("AAA")
                .setPlugboard("TP");
        assertEquals("OZHFXR", enigma.cipherMessage("PPPPPP"));

        enigma.changeRotorsPositions("AAA")
                .setPlugboard("TP OE ZN HI FG XM RA");
        assertEquals("ENIGMA", enigma.cipherMessage("PPPPPP"));
    }

    @Test
    void cipherMessageInCustomSettings() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        enigma.changeRingSettings("JNU")
                .changeRotorsPositions("VQQ")
                .setPlugboard("AP BR CM FZ GJ IL NT OV QS WX");

        String plainText = "HABHVHLYDFNADZY";
        String cipherText = "THATSITWELLDONE";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void settingsCanBeReset() {
        enigma.changeRingSettings("JNU")
                .changeRotorsPositions("VQQ")
                .setPlugboard("AP BR CM FZ GJ IL NT OV QS WX");

        String plainText = "HABHVHLYDFNADZY";
        String cipherText = "THATSITWELLDONE";
        assertEquals(cipherText, enigma.cipherMessage(plainText));

        enigma.resetSettings();
        String newCipherText = "IDLKKJUERQESWGC";
        assertEquals(newCipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void originalMessageTestFromEnigmaManual() {
        /*  Enigma user manual example
            Machine Settings for Enigma I/M3
            Reflector:      A
            Wheel order:    II I III
            Ring positions: XMV
            Plug pairs:     AM FI NV PS TU WZ
            Key:            ABL
         */
        enigma = new Enigma(Reflectors.A.get(),
                            M3Rotors.II.get(),
                            M3Rotors.I.get(),
                            M3Rotors.III.get())
                .changeRingSettings("XMV")
                .changeRotorsPositions("ABL")
                .setPlugboard("AM FI NV PS TU WZ");

        String plainText = OriginalMessages.ENIGMA_MANUAL_PLAINTEXT;
        String cipherText = OriginalMessages.ENIGMA_MANUAL_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void originalMessageTestOperationBarbarossa() {
        /*  Operation Barbarossa, 1941
            Sent from the Russian front on 7th July 1941.
            Machine Settings for Enigma I/M3
            Reflector:      B
            Wheel order:    II IV V
            Ring positions: B U L
            Plug pairs:     AV BS CG DL FU HZ IN KM OW RX

            Part One Key: BLA
            Part Two Key: LSD
         */
        enigma = new Enigma(Reflectors.B.get(),
                            M3Rotors.II.get(),
                            M3Rotors.IV.get(),
                            M3Rotors.V.get())
                .changeRingSettings("BUL")
                .changeRotorsPositions("BLA")
                .setPlugboard("AV BS CG DL FU HZ IN KM OW RX");

        String plainTextPartOne = OriginalMessages.OPERATION_BARBAROSSA_PART1_PLAINTEXT;
        String cipherTextPartOne = OriginalMessages.OPERATION_BARBAROSSA_PART1_CIPHERTEXT;
        testCipherAndDecipher(plainTextPartOne, cipherTextPartOne);

        enigma.changeRotorsPositions("LSD");

        String plainTextPartTwo = OriginalMessages.OPERATION_BARBAROSSA_PART2_PLAINTEXT;
        String cipherTextPartTwo = OriginalMessages.OPERATION_BARBAROSSA_PART2_CIPHERTEXT;
        testCipherAndDecipher(plainTextPartTwo, cipherTextPartTwo);
    }

    @Test
    void originalMessageTestNorrkoping() {
        /*  Norrkoping Enigma M3 intercept Page 81
            Machine Settings for Enigma I/M3
            Reflector:      B
            Wheel order:    VII VI V
            Ring positions: AXP
            Plug pairs:     AV BF DR IM OS WY
            Key:            AQO
         */
        enigma = new Enigma(Reflectors.B.get(),
                            M3Rotors.VII.get(),
                            M3Rotors.VI.get(),
                            M3Rotors.V.get())
                .changeRingSettings("AXP")
                .changeRotorsPositions("AQO")
                .setPlugboard("AV BF DR IM OS WY");

        String plainText = OriginalMessages.NORRKOPING_PLAINTEXT;
        String cipherText = OriginalMessages.NORRKOPING_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestScharnhorst() {
        /*  Scharnhorst (Konteradmiral Erich Bey), 1943
            Machine Settings for Enigma M3
            Reflector:      B
            Wheel order:    III VI VIII
            Ring positions: AHM
            Plug pairs:     AN EZ HK IJ LR MQ OT PV SW UX
            Key:            UZV
         */
        enigma = new Enigma(Reflectors.B.get(),
                            M3Rotors.III.get(),
                            M3Rotors.VI.get(),
                            M3Rotors.VIII.get())
                .changeRingSettings("AHM")
                .changeRotorsPositions("UZV")
                .setPlugboard("AN EZ HK IJ LR MQ OT PV SW UX");

        String plainText = OriginalMessages.SCHARNHORST_PLAINTEXT;
        String cipherText = OriginalMessages.SCHARNHORST_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestU264() {
        /*  U-264 (Kapitänleutnant Hartwig Looks), 1942
            Machine Settings for Enigma M4
            Reflector:      Thin B
            Wheel order:    B II IV I
            Ring positions: A A A V
            Plug pairs:     AT BL DF GJ HM NW OP QY RZ VX
            Key:            V J N A
         */
        enigma = new Enigma(Reflectors.ThinB.get(),
                            M4Rotors.Beta.get(),
                            M3Rotors.II.get(),
                            M3Rotors.IV.get(),
                            M3Rotors.I.get())
                .changeRingSettings("AAAV")
                .changeRotorsPositions("VJNA")
                .setPlugboard("AT BL DF GJ HM NW OP QY RZ VX");

        String plainText = OriginalMessages.U264_MESSAGE_PLAINTEXT;
        String cipherText = OriginalMessages.U264_MESSAGE_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestDonitzMay1945() {
        /*  Message from Dönitz - 1 May 1945
            Machine Settings for Enigma M4
            Reflector:      Thin C
            Wheel order:    B V VI VIII
            Ring positions: E P E L
            Plug pairs:     AE BF CM DQ HU JN LX PR SZ VW
            Key:            C D S Z
         */
        enigma = new Enigma(Reflectors.ThinC.get(),
                            M4Rotors.Beta.get(),
                            M3Rotors.V.get(),
                            M3Rotors.VI.get(),
                            M3Rotors.VIII.get())
                .changeRingSettings("EPEL")
                .changeRotorsPositions("CDSZ")
                .setPlugboard("AE BF CM DQ HU JN LX PR SZ VW");

        String plainText = OriginalMessages.DONITZ_MESSAGE_PLAINTEXT;
        String cipherText = OriginalMessages.DONITZ_MESSAGE_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestU534() {
        /*  U-534 P1030659
            Machine Settings for Enigma M4
            Reflector:      Thin B
            Wheel order:    G IV III VIII
            Ring positions: A A C U
            Plug pairs:     CH EJ NV OU TY LG SZ PK DI QB
            Key:            Y V O S
         */
        enigma = new Enigma(Reflectors.ThinB.get(),
                            M4Rotors.Gamma.get(),
                            M3Rotors.IV.get(),
                            M3Rotors.III.get(),
                            M3Rotors.VIII.get())
                .changeRingSettings("AACU")
                .changeRotorsPositions("YVOS")
                .setPlugboard("CH EJ NV OU TY LG SZ PK DI QB");

        String plainText = OriginalMessages.U534_MESSAGE_PLAINTEXT;
        String cipherText = OriginalMessages.U534_MESSAGE_CIPHERTEXT;
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void rotorSettingsAreChecked() {
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRingSettings("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRingSettings("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRingSettings("AA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRingSettings("AAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRingSettings("123"));

        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRotorsPositions("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRotorsPositions("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRotorsPositions("AA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRotorsPositions("AAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.changeRotorsPositions("123"));
    }

    @Test
    void plugboardSettingsAreChecked() {
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("ABC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("AB C"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("A BC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("A B"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("AF BR A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> enigma.setPlugboard("12 34"));
    }

    private void testCipherAndDecipher(String plainText, String cipherText) {
        String key = enigma.getRotorsPositions();
        assertEquals(plainText, enigma.cipherMessage(cipherText));

        enigma.changeRotorsPositions(key);
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

}