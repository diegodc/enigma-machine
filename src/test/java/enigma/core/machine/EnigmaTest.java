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

    private Enigma m3Enigma;
    private Enigma m4Enigma;

    @BeforeEach
    void setUp() {
        m3Enigma = new Enigma(Reflectors.B.get(),
                            M3Rotors.I.get(),
                            M3Rotors.II.get(),
                            M3Rotors.III.get());

        m4Enigma = new Enigma((Reflectors.ThinB.get()),
                            M4Rotors.Beta.get(),
                            M3Rotors.VI.get(),
                            M3Rotors.VII.get(),
                            M3Rotors.VIII.get());
    }

    @Test
    void initialSettings() {
        assertEquals("AAA", m3Enigma.getRotorsPositions());
        assertEquals("AAA", m3Enigma.getRingSettings());

        assertEquals("AAAA", m4Enigma.getRotorsPositions());
        assertEquals("AAAA", m4Enigma.getRingSettings());
    }

    @Test
    void changeRotorsPositions() {
        m3Enigma.changeRotorsPositions("POS");
        m4Enigma.changeRotorsPositions("PRES");
        assertEquals("POS", m3Enigma.getRotorsPositions());
        assertEquals("PRES", m4Enigma.getRotorsPositions());

        m3Enigma.changeRotorsPositions("ZZZ");
        m4Enigma.changeRotorsPositions("ZZZZ");
        assertEquals("ZZZ", m3Enigma.getRotorsPositions());
        assertEquals("ZZZZ", m4Enigma.getRotorsPositions());

        m3Enigma.changeRotorsPositions("TDD");
        m4Enigma.changeRotorsPositions("TEDD");
        assertEquals("TDD", m3Enigma.getRotorsPositions());
        assertEquals("TEDD", m4Enigma.getRotorsPositions());

        m3Enigma.changeRotorsPositions("AAA");
        m4Enigma.changeRotorsPositions("AAAA");
        assertEquals("AAA", m3Enigma.getRotorsPositions());
        assertEquals("AAAA", m4Enigma.getRotorsPositions());
    }

    @Test
    void changeRingSettings() {
        m3Enigma.changeRingSettings("RGN");
        m4Enigma.changeRingSettings("RGAN");
        assertEquals("RGN", m3Enigma.getRingSettings());
        assertEquals("RGAN", m4Enigma.getRingSettings());

        m3Enigma.changeRingSettings("ZZZ");
        m4Enigma.changeRingSettings("ZZZZ");
        assertEquals("ZZZ", m3Enigma.getRingSettings());
        assertEquals("ZZZZ", m4Enigma.getRingSettings());

        m3Enigma.changeRingSettings("ROT");
        m4Enigma.changeRingSettings("ROTR");
        assertEquals("ROT", m3Enigma.getRingSettings());
        assertEquals("ROTR", m4Enigma.getRingSettings());

        m3Enigma.changeRingSettings("AAA");
        m4Enigma.changeRingSettings("AAAA");
        assertEquals("AAA", m3Enigma.getRingSettings());
        assertEquals("AAAA", m4Enigma.getRingSettings());
    }

    @Test
    void cipherSameLetterMessageInInitialSetting() {
        /* example from https://en.wikipedia.org/wiki/Enigma_rotor_details */
        String plainText = "AAAAA";
        String cipherText = "BDZGO";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSetting() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISATEXTMESSAGE";
        String cipherText = "OPGNDXCRWRMNLNECJZ";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSettingEnigmaM4() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISATEXTMESSAGE";
        String cipherText = "QYYHTHMBRQRJGGIEAG";
        assertEquals(cipherText, m4Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInInitialSettingWithRightRotorTurnover() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "TESTMESSAGEWITHRIGHTROTORSTEP";
        String cipherText = "OLPFHNVFLYNQRABFYUJSPDEAOFGKT";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
        assertEquals("ABD", m3Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageInInitialSettingWithRightRotorTurnoverM4Enigma() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "TESTMESSAGEWITHRIGHTROTORSTEP";
        String cipherText = "QWCMRUZNDNCEJPKZWENKGVDMHKYAF";
        assertEquals(cipherText, m4Enigma.cipherMessage(plainText));
        assertEquals("AACD", m4Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageInInitialSettingWithDoubleStepSequence() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISALONGERENIGMAMESSAGETHATWILLCAUSEADOUBLESTEPSEQUENCEINTHE" +
                "ROTORMECHANISMTESTINGTHEMOVEMENTOFALLROTORS";
        String cipherText = "OPGNDXCGMHUTIBOKJDPYYILOLZGACJDXOWULFJNNIFVZGTWRAFMHCZYYETUXJK" +
                "PLFEJLDULKGJLWIMMNODWEEGYHNWPXFOUXXEVHPVFENX";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
        assertEquals("BFC", m3Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageInInitialSettingWithDoubleStepSequenceM4Enigma() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "THISISALONGERENIGMAMESSAGETHATWILLCAUSEADOUBLESTEPSEQUENCEINTHE" +
                "ROTORMECHANISMTESTINGTHEMOVEMENTOFALLROTORS";
        String cipherText = "EYLRZTZBTSFDGLCXCUWGKZLFJNYZWEYERKJZSULWHGDORMZKTURCEVUTJYWTWK" +
                "IZKNQDKJGUYXQVWIDIYUSYCXGYIMORXTCUOYSVOPEVPO";

        m4Enigma.changeRotorsPositions("AAFG");
        assertEquals(cipherText, m4Enigma.cipherMessage(plainText));
        assertEquals("ABOI", m4Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageWithKeyABC() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        m3Enigma.changeRotorsPositions("ABC");

        String plainText = "AEFAEJXXBNXYJTY";
        String cipherText = "CONGRATULATIONS";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithKeySetForRotorTurnover() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        m3Enigma.changeRotorsPositions("ABR");

        String plainText = "MABEKGZXSG";
        String cipherText = "TURNMIDDLE";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
        assertEquals("ACB", m3Enigma.getRotorsPositions());

        /* example from https://en.wikipedia.org/wiki/Enigma_rotor_details */
        m3Enigma.changeRotorsPositions("AAU");

        String plainText2 = "AAA";
        String cipherText2 = "MUQ";
        assertEquals(cipherText2, m3Enigma.cipherMessage(plainText2));
        assertEquals("ABX", m3Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageWithKeySetForDoubleStep() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        m3Enigma.changeRotorsPositions("ADS");

        String plainText = "RZFOGFYHPL";
        String cipherText = "TURNSTHREE";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
        assertEquals("BFC", m3Enigma.getRotorsPositions());
    }

    @Test
    void cipherMessageWithRotorsInRingSettingB() {
        /* example from https://en.wikipedia.org/wiki/Enigma_rotor_details */
        m3Enigma.changeRingSettings("BBB");

        String plainText = "AAAAA";
        String cipherText = "EWTYX";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageWithCustomKeyAndRingSetting() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        m3Enigma.changeRingSettings("JNU")
                .changeRotorsPositions("XYZ");

        String plainText = "QKTPEBZIUK";
        String cipherText = "GOODRESULT";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void setPlugboardWithM3Enigma() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        assertEquals("OZHFXR", m3Enigma.cipherMessage("TTTTTT"));

        m3Enigma.changeRotorsPositions("AAA")
                .setPlugboard("TP");
        assertEquals("OZHFXR", m3Enigma.cipherMessage("PPPPPP"));

        m3Enigma.changeRotorsPositions("AAA")
                .setPlugboard("TP OE ZN HI FG XM RA");
        assertEquals("ENIGMA", m3Enigma.cipherMessage("PPPPPP"));
    }

    @Test
    void setPlugboardWithM4Enigma() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        assertEquals("PVTKML", m4Enigma.cipherMessage("RRRRRR"));

        m4Enigma.changeRotorsPositions("AAAA")
                .setPlugboard("RW");
        assertEquals("PVTKML", m4Enigma.cipherMessage("WWWWWW"));

        m4Enigma.changeRotorsPositions("AAAA")
                .setPlugboard("RW EP NV IT GK LA");
        assertEquals("ENIGMA", m4Enigma.cipherMessage("WWWWWW"));
    }

    @Test
    void cipherMessageInCustomSettingsM3Enigma() {
        /* example from http://wiki.franklinheath.co.uk/index.php/Enigma/Paper_Enigma */
        m3Enigma.changeRingSettings("JNU")
                .changeRotorsPositions("VQQ")
                .setPlugboard("AP BR CM FZ GJ IL NT OV QS WX");

        String plainText = "HABHVHLYDFNADZY";
        String cipherText = "THATSITWELLDONE";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void cipherMessageInCustomSettingsM4Enigma() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        m4Enigma.changeRingSettings("JNUZ")
                .changeRotorsPositions("RVQQ")
                .setPlugboard("AP BR CM FZ GJ IL NT OV QS WX");

        String plainText = "LAYZVXGKUQXADGY";
        String cipherText = "THATSITWELLDONE";
        assertEquals(cipherText, m4Enigma.cipherMessage(plainText));
    }

    @Test
    void settingsCanBeReset() {
        m3Enigma.changeRingSettings("JNU")
                .changeRotorsPositions("VQQ")
                .setPlugboard("AP BR CM FZ GJ IL NT OV QS WX");

        String plainText = "HABHVHLYDFNADZY";
        String cipherText = "THATSITWELLDONE";
        assertEquals(cipherText, m3Enigma.cipherMessage(plainText));

        m3Enigma.resetSettings();
        assertEquals("AAA", m3Enigma.getRotorsPositions());
        assertEquals("AAA", m3Enigma.getRingSettings());

        String newCipherText = "IDLKKJUERQESWGC";
        assertEquals(newCipherText, m3Enigma.cipherMessage(plainText));
    }

    @Test
    void rotorSettingsAreCheckedForM3Enigma() {
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRingSettings("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRingSettings("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRingSettings("AA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRingSettings("AAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRingSettings("123"));

        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRotorsPositions("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRotorsPositions("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRotorsPositions("AA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRotorsPositions("AAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.changeRotorsPositions("123"));
    }

    @Test
    void rotorSettingsAreCheckedForM4Enigma() {
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRingSettings("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRingSettings("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRingSettings("AAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRingSettings("AAAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRingSettings("1234"));

        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRotorsPositions("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRotorsPositions("1"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRotorsPositions("AAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRotorsPositions("AAAAA"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.changeRotorsPositions("1234"));
    }

    @Test
    void plugboardSettingsAreChecked() {
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("ABC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("AB C"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("A BC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("A B"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("AF BR A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m3Enigma.setPlugboard("12 34"));

        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("ABC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("AB C"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("A BC"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("A B"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("AF BR A"));
        assertThrows(Enigma.InvalidSetting.class, ()-> m4Enigma.setPlugboard("12 34"));
    }

}