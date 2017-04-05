package enigma.core.machine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static enigma.core.RotorFactory.*;
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
        enigma = new Enigma(reflectorB(), rotorI(), rotorII(), rotorIII());
    }

    @Test
    void initialSettings_ShouldBeAAA() {
        assertEquals("AAA", enigma.getRotorsPositions());
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
    void cipherStringInInitialSetting() {
        /* tested with Enigma Emulator http://enigma.louisedade.co.uk */
        String plainText = "TEST";
        String cipherText = "OLPF";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
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
        String plainText = "TEST";
        String cipherText = "OLPF";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void cipherAnotherMessageInInitialSetting() {
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
        String plainText = "THISISALONGERENIGMAMESSAGEWILLCAUSEADOUBLESTEPSEQUENCEINTHEROTORMECHANISMTESTINGTHEMOVEMENTOFALLROTORS";
        String cipherText = "OPGNDXCGMHUTIBOKJDPYYILOLZOVGWRGMHZLCFGQYUYMVSWCBJZMQXMYWKBTFIWLPTTUOYKFYSDWWJXKOGHWNIYHVKHPEWOPTKYNPE";
        assertEquals(cipherText, enigma.cipherMessage(plainText));
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
        /*  Machine Settings for Enigma I/M3
            Reflector: 	A
            Wheel order: 	II I III
            Ring positions: XMV
            Plug pairs: 	AM FI NV PS TU WZ
            Key = ABL
         */
        enigma = new Enigma(reflectorA(), rotorII(), rotorI(), rotorIII())
                .changeRingSettings("XMV")
                .changeRotorsPositions("ABL")
                .setPlugboard("AM FI NV PS TU WZ");

        String plainText = "GCDSEAHUGWTQGRKVLFGXUCALXVYMIGMMNMFDXTGNVHVRMMEVOUYFZSLRHDRRXFJWCFHUHMUNZEFRDISIKBGPMYVXUZ";
        String cipherText = "FEINDLIQEINFANTERIEKOLONNEBEOBAQTETXANFANGSUEDAUSGANGBAERWALDEXENDEDREIKMOSTWAERTSNEUSTADT";

        testCipherAndDecipher(plainText, cipherText);
    }

    private void testCipherAndDecipher(String plainText, String cipherText) {
        String key = enigma.getRotorsPositions();
        assertEquals(plainText, enigma.cipherMessage(cipherText));

        enigma.changeRotorsPositions(key);
        assertEquals(cipherText, enigma.cipherMessage(plainText));
    }

    @Test
    void originalMessageTestOperationBarbarossa() {
        /*  Operation Barbarossa, 1941
            Machine Settings for Enigma I/M3
            Reflector: 	B
            Wheel order: 	II IV V
            Ring positions: B U L
            Plug pairs: 	AV BS CG DL FU HZ IN KM OW RX
            Sent from the Russian front on 7th July 1941.
            Part One Key = BLA
            Part Two Key = LSD
         */
        enigma = new Enigma(reflectorB(), rotorII(), rotorIV(), rotorV())
                .changeRingSettings("BUL")
                .changeRotorsPositions("BLA")
                .setPlugboard("AV BS CG DL FU HZ IN KM OW RX");

        String plainTextPartOne = "EDPUDNRGYSZRCXNUYTPOMRMBOFKTBZREZKMLXLVEFGUEYSIOZVEQMIKUBPMMYLKLTTDEISMDICAGYKU" +
                "ACTCDOMOHWXMUUIAUBSTSLRNBZSZWNRFXWFYSSXJZVIJHIDISHPRKLKAYUPADTXQSPINQMATLPIFSVKDASCTACDPBOPVHJK";
        String cipherTextPartOne = "AUFKLXABTEILUNGXVONXKURTINOWAXKURTINOWAXNORDWESTLXSEBEZXSEBEZXUAFFLIEGERSTRASZ" +
                "ERIQTUNGXDUBROWKIXDUBROWKIXOPOTSCHKAXOPOTSCHKAXUMXEINSAQTDREINULLXUHRANGETRETENXANGRIFFXINFXRGTX";
        testCipherAndDecipher(plainTextPartOne, cipherTextPartOne);

        enigma.changeRotorsPositions("LSD");

        String plainTextPartTwo = "SFBWDNJUSEGQOBHKRTAREEZMWKPPRBXOHDROEQGBBGTQVPGVKBVVGBIMHUSZYDAJQIROAXSS" +
                "SNREHYGGRPISEZBOVMQIEMMZCYSGQDGRERVBILEKXYQIRGIRQNRDNVRXCYYTNJR";
        String cipherTextPartTwo = "DREIGEHTLANGSAMABERSIQERVORWAERTSXEINSSIEBENNULLSEQSXUHRXROEMXEINSXINFR" +
                "GTXDREIXAUFFLIEGERSTRASZEMITANFANGXEINSSEQSXKMXKMXOSTWXKAMENECXK";
        testCipherAndDecipher(plainTextPartTwo, cipherTextPartTwo);
    }

    @Test
    void originalMessageTestNorrkoping() {
        /*  Norrkoping Enigma M3 intercept Page 81
            Machine Settings for Enigma I/M3
            Reflector: 	B
            Wheel order: 	VII VI V
            Ring positions: AXP
            Plug pairs: 	AV BF DR IM OS WY
            Key = AQO
         */
        enigma = new Enigma(reflectorB(), rotorVII(), rotorVI(), rotorV())
                .changeRingSettings("AXP")
                .changeRotorsPositions("AQO")
                .setPlugboard("AV BF DR IM OS WY");

        String plainText = "MSPOKQBHQLCMENJJXMYGPKKXWGXTWWXDNYZFQGEKDKSYPKLBBDPMSFUUWHYZLTVOWIOFHKOUZMLQF" +
                "CFLVQGXOPWNEGWWQJLTFWAMENHBDBRBSYLMSNWYZOIE";
        String cipherText = "VINRFINSRRRFLVONFDMMMOSTMITDUNKOAWERDONARBEITUQTXRBRECHENYHERANSCHLIESINNANM" +
                "INEZRATUMSCIIFFEINSEINSQUSIEBENVIERNLUNACHTO";
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestScharnhorst() {
        /*  Scharnhorst (Konteradmiral Erich Bey), 1943
            Machine Settings for Enigma M3
            Reflector: 	B
            Wheel order: 	III VI VIII
            Ring positions: AHM
            Plug pairs: 	AN EZ HK IJ LR MQ OT PV SW UX

            Key = UZV
         */
        enigma = new Enigma(reflectorB(), rotorIII(), rotorVI(), rotorVIII())
                .changeRingSettings("AHM")
                .changeRotorsPositions("UZV")
                .setPlugboard("AN EZ HK IJ LR MQ OT PV SW UX");

        String plainText = "YKAENZAPMSCHZBFOCUVMRMDPYCOFHADZIZMEFXTHFLOLPZLFGGBOTGOXGRETDWTJIQHLMXVJWKZUASTR";
        String cipherText = "STEUEREJTANAFJORDJANSTANDORTQUAAACCCVIERNEUNNEUNZWOFAHRTZWONULSMXXSCHARNHORSTHCO";
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestU264() {
        /*  U-264 (Kapitänleutnant Hartwig Looks), 1942
            Machine Settings for Enigma M4
            Reflector: 	 Thin B
            Wheel order: B II IV I
            Ring positions:	A A A V
            Plug pairs: 	AT BL DF GJ HM NW OP QY RZ VX
            Key: V J N A
         */
        enigma = new Enigma(reflectorThinB(), rotorBetta(), rotorII(), rotorIV(), rotorI())
                .changeRingSettings("AAAV")
                .changeRotorsPositions("VJNA")
                .setPlugboard("AT BL DF GJ HM NW OP QY RZ VX");

        String plainText = "NCZWVUSXPNYMINHZXMQXSFWXWLKJAHSHNMCOCCAKUQPMKCSMHKSEINJUSBLKIOSXCKUBHMLLXCSJUSRRDV" +
                "KOHULXWCCBGVLIYXEOAHXRHKKFVDREWEZLXOBAFGYUJQUKGRTVUKAMEURBVEKSUHHVOYHABCJWMAKLFKLMYFVNRIZRVVR" +
                "TKOFDANJMOLBGFFLEOPRGTFLVRHOWOPBEKVWMUQFMPWPARMFHAGKXIIBG";
        String cipherText = "VONVONJLOOKSJHFFTTTEINSEINSDREIZWOYYQNNSNEUNINHALTXXBEIANGRIFFUNTERWASSERGEDRUECK" +
                "TYWABOSXLETZTERGEGNERSTANDNULACHTDREINULUHRMARQUANTONJOTANEUNACHTSEYHSDREIYZWOZWONULGRADYACHT" +
                "SMYSTOSSENACHXEKNSVIERMBFAELLTYNNNNNNOOOVIERYSICHTEINSNULL";
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestDonitzMay1945() {
        /*  Message from Dönitz - 1 May 1945
            Machine Settings for Enigma M4
            Reflector: 	Thin C
            Wheel order: 	B V VI VIII
            Ring positions:  E P E L
            Plug pairs: 	AE BF CM DQ HU JN LX PR SZ VW
            Key: C D S Z
         */
        enigma = new Enigma(reflectorThinC(), rotorBetta(), rotorV(), rotorVI(), rotorVIII())
                .changeRingSettings("EPEL")
                .changeRotorsPositions("CDSZ")
                .setPlugboard("AE BF CM DQ HU JN LX PR SZ VW");

        String plainText = "LANOTCTOUARBBFPMHPHGCZXTDYGAHGUFXGEWKBLKGJWLQXXTGPJJAVTOCKZFSLPPQIHZFXOEBWIIEKFZLCLOAQJ" +
                "ULJOYHSSMBBGWHZANVOIIPYRBRTDJQDJJOQKCXWDNBBTYVXLYTAPGVEATXSONPNYNQFUDBBHHVWEPYEYDOHNLXKZDNWRHDUWUJ" +
                "UMWWVIIWZXIVIUQDRHYMNCYEFUAPNHOTKHKGDNPSAKNUAGHJZSMJBMHVTREQEDGXHLZWIFUSKDQVELNMIMITHBHDBWVHDFYHJO" +
                "QIHORTDJDBWXEMEAYXGYQXOHFDMYUXXNOJAZRSGHPLWMLRECWWUTLRTTVLBHYOORGLGOWUXNXHMHYFAACQEKTHSJW";
        String cipherText = "KRKRALLEXXFOLGENDESISTSOFORTBEKANNTZUGEBENXXICHHABEFOLGELNBEBEFEHLERHALTENXXJANSTERLED" +
                "ESBISHERIGXNREICHSMARSCHALLSJGOERINGJSETZTDERFUEHRERSIEYHVRRGRZSSADMIRALYALSSEINENNACHFOLGEREINXSC" +
                "HRIFTLSCHEVOLLMACHTUNTERWEGSXABSOFORTSOLLENSIESAEMTLICHEMASSNAHMENVERFUEGENYDIESICHAUSDERGEGENWAER" +
                "TIGENLAGEERGEBENXGEZXREICHSLEITEIKKTULPEKKJBORMANNJXXOBXDXMMMDURNHFKSTXKOMXADMXUUUBOOIEXKP";
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void navalEnigmaOriginalMessageTestU534() {
        /*  U-534 P1030659
            Machine Settings for Enigma M4
            Reflector: 	Thin B
            Wheel order: 	G IV III VIII
            Ring positions:  A A C U
            Plug pairs: 	CH EJ NV OU TY LG SZ PK DI QB
            Key: Y V O S
         */
        enigma = new Enigma(reflectorThinB(), rotorGamma(), rotorIV(), rotorIII(), rotorVIII())
                .changeRingSettings("AACU")
                .changeRotorsPositions("YVOS")
                .setPlugboard("CH EJ NV OU TY LG SZ PK DI QB");

        String plainText = "YUPOVEJTBKONNFSALTWELQAZJXTIRJLLISCSGXSHEJFYNZQDNQSUXPGFTJKWINGORYBJYADWNFCLPPNSLWUYBUQISXGQ";
        String cipherText = "FFFDDDUUUAUSBILDUNGVONVONZWOSECHSUUUFLOTTXXTTTFFFZWODREIAUSGEZQESTETMITSSSSSSGCGXTTTFFFZWOQI";
        testCipherAndDecipher(plainText, cipherText);
    }

    @Test
    void plugboardSettingsAreChecked() {
        assertThrows(IllegalArgumentException.class, ()-> enigma.setPlugboard("A"));
    }

}