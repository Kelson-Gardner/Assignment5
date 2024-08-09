import kotlin.test.Test
import kotlin.test.assertEquals

class EmailStateMachineTest {
    private var emailVerifier = EmailVerifier()
    private var firstCharacterEmailPart1State = FirstCharacterEmailPart1State()
    private var firstCharacterEmailPart2State = FirstCharacterEmailPart2State()
    private var firstCharacterEmailPart3State = FirstCharacterEmailPart3State()
    private var emailPart1State = EmailPart1State()
    private var emailPart2State = EmailPart2State()
    private var invalidEmailState = InvalidEmailState()
    private var validEmailState = ValidEmailState()

    /**
     * @FirstCharacterEmailPart1State
     */
    @Test
    fun testFirstCharacterEmailPart1StateValid(){
        emailVerifier.state = FirstCharacterEmailPart1State()
        firstCharacterEmailPart1State.consumeCharacter("a", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart1State, true)
        firstCharacterEmailPart1State.consumeCharacter("!", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart1State, true)
        firstCharacterEmailPart1State.consumeCharacter("0", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart1State, true)
    }
    @Test
    fun testFirstCharacterEmailPart1StateInvalid(){
        emailVerifier.state = FirstCharacterEmailPart1State()
        firstCharacterEmailPart1State.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        firstCharacterEmailPart1State.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @FirstCharacterEmailPart2State
     */
    @Test
    fun testFirstCharacterEmailPart2StateValid(){
        emailVerifier.state = FirstCharacterEmailPart2State()
        firstCharacterEmailPart2State.consumeCharacter("a", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart2State, true)
        firstCharacterEmailPart2State.consumeCharacter("!", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart2State, true)
        firstCharacterEmailPart2State.consumeCharacter("0", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart2State, true)
    }
    @Test
    fun testFirstCharacterEmailPart2StateInvalid(){
        emailVerifier.state = FirstCharacterEmailPart2State()
        firstCharacterEmailPart2State.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        firstCharacterEmailPart2State.consumeCharacter(".", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        firstCharacterEmailPart2State.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @FirstCharacterEmailPart3State
     */
    @Test
    fun testFirstCharacterEmailPart3StateValid(){
        emailVerifier.state = FirstCharacterEmailPart3State()
        firstCharacterEmailPart3State.consumeCharacter("a", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
        firstCharacterEmailPart3State.consumeCharacter("!", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
        firstCharacterEmailPart3State.consumeCharacter("0", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
    }
    @Test
    fun testFirstCharacterEmailPart3StateInvalid(){
        emailVerifier.state = FirstCharacterEmailPart3State()
        firstCharacterEmailPart3State.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        firstCharacterEmailPart3State.consumeCharacter(".", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        firstCharacterEmailPart3State.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @EmailPart1State
     */
    @Test
    fun testEmailPart1StateValid(){
        emailVerifier.state = EmailPart1State()
        emailPart1State.consumeCharacter("1", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart1State, true)
        emailPart1State.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is FirstCharacterEmailPart2State, true)
    }
    @Test
    fun testEmailPart1StateInvalid(){
        emailVerifier.state = EmailPart1State()
        emailPart1State.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @EmailPart2State
     */
    @Test
    fun testEmailPart2StateValid(){
        emailVerifier.state = EmailPart2State()
        emailPart2State.consumeCharacter("1", emailVerifier)
        assertEquals(emailVerifier.state is EmailPart2State, true)
        emailPart2State.consumeCharacter(".", emailVerifier)
        assertEquals(emailVerifier.state is FirstCharacterEmailPart3State, true)
    }
    @Test
    fun testEmailPart2StateInvalid(){
        emailVerifier.state = EmailPart2State()
        emailPart2State.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        emailPart2State.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @InvalidEmailState
     */
    @Test
    fun testInvalidEmail(){
        emailVerifier.state = InvalidEmailState()
        invalidEmailState.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        invalidEmailState.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }

    /**
     * @ValidEmailState
     */
    @Test
    fun testValidEmailValid(){
        emailVerifier.state = ValidEmailState()
        validEmailState.consumeCharacter("e", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
        validEmailState.consumeCharacter("A", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
        validEmailState.consumeCharacter("e;", emailVerifier)
        assertEquals(emailVerifier.state is ValidEmailState, true)
    }
    @Test
    fun testValidEmailInvalid(){
        emailVerifier.state = ValidEmailState()
        validEmailState.consumeCharacter(" ", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        validEmailState.consumeCharacter("@", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
        validEmailState.consumeCharacter(".", emailVerifier)
        assertEquals(emailVerifier.state is InvalidEmailState, true)
    }
    /**
     * @EmailVerifier
     */
    @Test
    fun testEmailVerifierValid(){
        assertEquals(emailVerifier.verify("a@b.c"), true)
        assertEquals(emailVerifier.verify("kelson.gardner@gamil.com"), true)
        assertEquals(emailVerifier.verify("0987120987r09qwfyuas@:::KLSLJKDFJ{}}.1234%$##$%"), true)
    }
    @Test
    fun testEmailVerifierInvalidSpace(){
        assertEquals(emailVerifier.verify("a @b.c"), false)
        assertEquals(emailVerifier.verify("kelson.gardner@ga mil.com"), false)
        assertEquals(emailVerifier.verify("0987120987r09qwfyuas@:::KLSLJKDFJ{}}.1234%$## $%"), false)
    }
    @Test
    fun testEmailVerifierInvalidPeriod(){
        assertEquals(emailVerifier.verify("a@.b.c"), false)
        assertEquals(emailVerifier.verify("kelson.gardner@gamil.co.m"), false)
        assertEquals(emailVerifier.verify("0987120987r09qwfyuas@:::KLSLJKDFJ{}}.1234%$##.$%"), false)
    }
    @Test
    fun testEmailVerifierInvalidAt(){
        assertEquals(emailVerifier.verify("a@@b.c"), false)
        assertEquals(emailVerifier.verify("kelson.gardner@gamil.co@m"), false)
        assertEquals(emailVerifier.verify("0@987120987r09qwfyuas@:::KLSLJKDFJ{}}.1234%$##$%"), false)
    }
    @Test
    fun testEmailVerifierInvalidMissingPart(){
        assertEquals(emailVerifier.verify("@b.c"), false)
        assertEquals(emailVerifier.verify("kelson.gardner@gamil."), false)
        assertEquals(emailVerifier.verify("0@987120987r09qwfyuas@.1234%$##$%"), false)
    }
}