import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryStateMachineTest {
    private var binaryVerifier = BinaryVerifier()
    private val firstBinaryDigit = FirstBinaryDigitState()
    private val zeroBinaryDigitState = ZeroBinaryDigitState()
    private val invalidBinaryState = InvalidBinaryState()
    private val validBinaryState = ValidBinaryState()


    /**
     * @FirstBinaryDigitState
     */
    @Test
    fun testFirstBinaryDigitStateValid(){
        firstBinaryDigit.consumeCharacter("1", binaryVerifier)
        assertEquals(binaryVerifier.state is ValidBinaryState, true)
    }
    @Test
    fun testFirstBinaryDigitStateInvalid(){
        firstBinaryDigit.consumeCharacter("0", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
        firstBinaryDigit.consumeCharacter("a", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
    }

    /**
     * @ZeroBinaryDigitState
     */
    @Test
    fun testZeroBinaryDigitStateZero(){
        binaryVerifier.state = ZeroBinaryDigitState()
        zeroBinaryDigitState.consumeCharacter("0", binaryVerifier)
        assertEquals(binaryVerifier.state is ZeroBinaryDigitState, true)
    }
    @Test
    fun testZeroBinaryDigitStateOne(){
        zeroBinaryDigitState.consumeCharacter("1", binaryVerifier)
        assertEquals(binaryVerifier.state is ValidBinaryState, true)
    }
    @Test
    fun testZeroBinaryDigitStateInvalid(){
        zeroBinaryDigitState.consumeCharacter("a", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
        zeroBinaryDigitState.consumeCharacter("4", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
    }

    /**
     * @InvalidBinaryState
     */
    @Test
    fun testInvalidBinaryState(){
        binaryVerifier.state = InvalidBinaryState()
        invalidBinaryState.consumeCharacter("1", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
        invalidBinaryState.consumeCharacter("0", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
        invalidBinaryState.consumeCharacter("a", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
    }

    /**
     * @ValidBinaryState
     */
    @Test
    fun testValidBinaryStateValid(){
        binaryVerifier.state = ValidBinaryState()
        validBinaryState.consumeCharacter("1", binaryVerifier)
        assertEquals(binaryVerifier.state is ValidBinaryState, true)
    }
    @Test
    fun testValidBinaryStateZero(){
        validBinaryState.consumeCharacter("0", binaryVerifier)
        assertEquals(binaryVerifier.state is ZeroBinaryDigitState, true)
    }
    @Test
    fun testValidBinaryStateInvalid(){
        validBinaryState.consumeCharacter("a", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
        validBinaryState.consumeCharacter("4", binaryVerifier)
        assertEquals(binaryVerifier.state is InvalidBinaryState, true)
    }

    /**
     * @BinaryVerifier
     */
    @Test
    fun testBinaryVerifierValid(){
        assertEquals(binaryVerifier.verify("1"), true)
        assertEquals(binaryVerifier.verify("11"), true)
        assertEquals(binaryVerifier.verify("101"), true)
        assertEquals(binaryVerifier.verify("1001011011001101010101011101111100000001"), true)
    }
    @Test
    fun testBinaryVerifierInvalid(){
        assertEquals(binaryVerifier.verify("0"), false)
        assertEquals(binaryVerifier.verify("01"), false)
        assertEquals(binaryVerifier.verify("10"), false)
        assertEquals(binaryVerifier.verify("100101101100110a010101011101111100000001"), false)
        assertEquals(binaryVerifier.verify(""), false)
    }
}