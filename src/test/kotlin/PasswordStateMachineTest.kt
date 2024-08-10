import kotlin.test.Test
import kotlin.test.assertEquals

class PasswordStateMachineTest {
    private var passwordVerifier = PasswordVerifier()
    private var noSpecialNoCapitalState = NoSpecialNoCapitalState()
    private var noCapitalLetterState = NoCapitalLetterState()
    private var noSpecialCharacterState = NoSpecialCharacterState()
    private var endInSpecialCharacterState = EndInSpecialCharacterState()
    private var invalidPasswordState = InvalidPasswordState()
    private var validPasswordState = ValidPasswordState()

    /**
     * @NoSpecialNoCapitalState
     */
    @Test
    fun testNoSpecialNoCapitalStateCapital(){
        passwordVerifier.state = NoSpecialNoCapitalState()
        noSpecialNoCapitalState.consumeCharacter("A", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
        noSpecialNoCapitalState.consumeCharacter("Z", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
        noSpecialNoCapitalState.consumeCharacter("R", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
    }
    @Test
    fun testNoSpecialNoCapitalStateSpecial(){
        passwordVerifier.state = NoSpecialNoCapitalState()
        noSpecialNoCapitalState.consumeCharacter("*", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noSpecialNoCapitalState.consumeCharacter("%", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noSpecialNoCapitalState.consumeCharacter("!", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
    }
    @Test
    fun testNoSpecialNoCapitalState(){
        passwordVerifier.state = NoSpecialNoCapitalState()
        noSpecialNoCapitalState.consumeCharacter("a", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialNoCapitalState, true)
        noSpecialNoCapitalState.consumeCharacter("d", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialNoCapitalState, true)
        noSpecialNoCapitalState.consumeCharacter("+", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialNoCapitalState, true)
    }

    /**
     * @NoCapitalLetterState
     */
    @Test
    fun testNoCapitalLetterStateSpecial(){
        passwordVerifier.state = NoCapitalLetterState()
        noCapitalLetterState.consumeCharacter("&", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noCapitalLetterState.consumeCharacter("#", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noCapitalLetterState.consumeCharacter("$", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
    }
    @Test
    fun testNoCapitalLetterState(){
        passwordVerifier.state = NoCapitalLetterState()
        noCapitalLetterState.consumeCharacter("i", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noCapitalLetterState.consumeCharacter("d", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
        noCapitalLetterState.consumeCharacter(";", passwordVerifier)
        assertEquals(passwordVerifier.state is NoCapitalLetterState, true)
    }

    /**
     * @NoSpecialCharacterState
     */
    @Test
    fun testNoSpecialCharacterStateSpecial(){
        passwordVerifier.state = NoSpecialCharacterState()
        noSpecialCharacterState.consumeCharacter("!", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        noSpecialCharacterState.consumeCharacter("*", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        noSpecialCharacterState.consumeCharacter("\\", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
    }
    @Test
    fun testNoSpecialCharacterState(){
        passwordVerifier.state = NoSpecialCharacterState()
        noSpecialCharacterState.consumeCharacter("J", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
        noSpecialCharacterState.consumeCharacter("b", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
        noSpecialCharacterState.consumeCharacter("E", passwordVerifier)
        assertEquals(passwordVerifier.state is NoSpecialCharacterState, true)
    }

    /**
     * @EndInSpecialCharacterState
     */
    @Test
    fun testEndInSpecialCharacterStateValid(){
        passwordVerifier.state = EndInSpecialCharacterState()
        endInSpecialCharacterState.consumeCharacter("J", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
        endInSpecialCharacterState.consumeCharacter("b", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
        endInSpecialCharacterState.consumeCharacter("E", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
    }
    @Test
    fun testEndInSpecialCharacterState(){
        passwordVerifier.state = EndInSpecialCharacterState()
        endInSpecialCharacterState.consumeCharacter("&", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        endInSpecialCharacterState.consumeCharacter("!", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        endInSpecialCharacterState.consumeCharacter("#", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
    }

    /**
     * @InvalidPasswordState
     */
    @Test
    fun testInvalidPasswordState(){
        passwordVerifier.state = InvalidPasswordState()
        invalidPasswordState.consumeCharacter("d", passwordVerifier)
        assertEquals(passwordVerifier.state is InvalidPasswordState, true)
        invalidPasswordState.consumeCharacter("A", passwordVerifier)
        assertEquals(passwordVerifier.state is InvalidPasswordState, true)
        invalidPasswordState.consumeCharacter("#", passwordVerifier)
        assertEquals(passwordVerifier.state is InvalidPasswordState, true)
    }

    /**
     * @ValidPasswordState
     */
    @Test
    fun testValidPasswordStateSpecial(){
        passwordVerifier.state = ValidPasswordState()
        validPasswordState.consumeCharacter("$", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        validPasswordState.consumeCharacter("%", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
        validPasswordState.consumeCharacter("#", passwordVerifier)
        assertEquals(passwordVerifier.state is EndInSpecialCharacterState, true)
    }
    @Test
    fun testValidPasswordState(){
        passwordVerifier.state = ValidPasswordState()
        validPasswordState.consumeCharacter("A", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
        validPasswordState.consumeCharacter("a", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
        validPasswordState.consumeCharacter("e", passwordVerifier)
        assertEquals(passwordVerifier.state is ValidPasswordState, true)
    }

    /**
     * @PasswordVerifier
     */
    @Test
    fun testPasswordVerifierValid(){
        assertEquals(passwordVerifier.verify("aaaaH!aa"), true)
        assertEquals(passwordVerifier.verify("1234567*9J"), true)
        assertEquals(passwordVerifier.verify("asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@aH"), true)
    }
    @Test
    fun testPasswordVerifierInvalidEndsInSpecialCharacter(){
        assertEquals(passwordVerifier.verify("aaaaH!aa!"), false)
        assertEquals(passwordVerifier.verify("1234567*9J$"), false)
        assertEquals(passwordVerifier.verify("asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@aH#"), false)
    }
    @Test
    fun testPasswordVerifierInvalidNoSpecialCharacter(){
        assertEquals(passwordVerifier.verify("aaaaHaaa"), false)
        assertEquals(passwordVerifier.verify("12345679JKJDI"), false)
        assertEquals(passwordVerifier.verify("asdpoihjloikjasdfijplij2309jasdlfkm20ijaH"), false)
    }
    @Test
    fun testPasswordVerifierInvalidNoCapitalLetter(){
        assertEquals(passwordVerifier.verify("aaaak!aaa"), false)
        assertEquals(passwordVerifier.verify("1234567*9jdj"), false)
        assertEquals(passwordVerifier.verify("asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@ah"), false)
    }
    @Test
    fun testPasswordVerifierInvalidTooShort(){
        assertEquals(passwordVerifier.verify("aaAa!"), false)
        assertEquals(passwordVerifier.verify("a"), false)
        assertEquals(passwordVerifier.verify("ido#asJ"), false)
    }
}