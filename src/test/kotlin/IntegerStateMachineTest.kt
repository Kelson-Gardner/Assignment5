import kotlin.test.Test
import kotlin.test.assertEquals

class IntegerStateMachineTest {
    private var integerVerifier = IntegerVerifier()
    private val firstDigit = FirstDigitState()
    private val invalidState = InvalidIntegerState()
    private val validState = ValidIntegerState()

    /**
     * @FirstDigitState
     */
    @Test
    fun testFirstDigitStateValidState(){
        firstDigit.consumeCharacter("1", integerVerifier)
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        firstDigit.consumeCharacter("3", integerVerifier)
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        firstDigit.consumeCharacter("6", integerVerifier)
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        firstDigit.consumeCharacter("9", integerVerifier)
        assertEquals(integerVerifier.state is ValidIntegerState, true)
    }
    @Test
    fun testFirstDigitStateInvalidState(){
        firstDigit.consumeCharacter("a", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        firstDigit.consumeCharacter("0", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        firstDigit.consumeCharacter("*", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        firstDigit.consumeCharacter(";", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
    }

    /**
     * @ValidInteger
     */
    @Test
    fun testValidIntegerState(){
        integerVerifier.state = ValidIntegerState()
        validState.consumeCharacter("0", integerVerifier)
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        validState.consumeCharacter("a", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
    }

    /**
     * @InvalidState
     */
    @Test
    fun testInvalidIntegerState(){
        integerVerifier.state = InvalidIntegerState()
        invalidState.consumeCharacter("4", integerVerifier)
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
    }

    /**
     * @IntegerVerifier
     */
    @Test
    fun testIntegerVerifierValid(){
        integerVerifier.verify("1234")
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        integerVerifier.verify("1110987344749873982709387409879287387873874499039827482798734")
        assertEquals(integerVerifier.state is ValidIntegerState, true)
        integerVerifier.verify("9")
        assertEquals(integerVerifier.state is ValidIntegerState, true)
    }
    @Test
    fun testIntegerVerifierInvalid(){
        integerVerifier.verify("01234")
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        integerVerifier.verify("1110987344a749873982a709387409879b28738787c3874499039827482798734")
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        integerVerifier.verify("0")
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
        integerVerifier.verify("")
        assertEquals(integerVerifier.state is InvalidIntegerState, true)
    }
}