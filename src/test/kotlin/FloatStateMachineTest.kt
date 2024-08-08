import kotlin.test.Test
import kotlin.test.assertEquals

class FloatStateMachineTest {
    private var floatVerifier = FloatVerifier()
    private val firstFloatDigit = FirstFloatDigitState()
    private val validFirstPartFloatState = ValidFirstPartFloatState()
    private val validSecondPartFloatState = ValidFloatState()
    private val firstDigitAfterPeriodState = FirstDigitAfterPeriodState()
    private val periodAfterZeroState = PeriodAfterZeroState()
    private val invalidFloatState = InvalidFloatState()

    /**
     * @FirstFloatDigit
     */
    @Test
    fun testFirstFloatDigitPeriod(){
        floatVerifier.state = FirstFloatDigitState()
        firstFloatDigit.consumeCharacter(".", floatVerifier)
        assertEquals(floatVerifier.state is FirstDigitAfterPeriodState, true)
    }
    @Test
    fun testFirstFloatDigitZero(){
        floatVerifier.state = FirstFloatDigitState()
        firstFloatDigit.consumeCharacter("0", floatVerifier)
        assertEquals(floatVerifier.state is PeriodAfterZeroState, true)
    }
    @Test
    fun testFirstFloatDigitNumber(){
        floatVerifier.state = FirstFloatDigitState()
        firstFloatDigit.consumeCharacter("3", floatVerifier)
        assertEquals(floatVerifier.state is ValidFirstPartFloatState, true)
    }
    @Test
    fun testFirstFloatDigitInvalidInput(){
        floatVerifier.state = FirstFloatDigitState()
        firstFloatDigit.consumeCharacter("a", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        firstFloatDigit.consumeCharacter("*", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        firstFloatDigit.consumeCharacter(";", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }

    /**
     * @ValidFirstPartFloatState
     */
    @Test
    fun testValidFirstPartFloatStateValid(){
        floatVerifier.state = ValidFirstPartFloatState()
        validFirstPartFloatState.consumeCharacter("3", floatVerifier)
        assertEquals(floatVerifier.state is ValidFirstPartFloatState, true)
    }
    @Test
    fun testValidFirstPartFloatStateInvalid(){
        floatVerifier.state = ValidFirstPartFloatState()
        validFirstPartFloatState.consumeCharacter("a", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = ValidFirstPartFloatState()
        validFirstPartFloatState.consumeCharacter("*", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = ValidFirstPartFloatState()
        validFirstPartFloatState.consumeCharacter("?", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }
    @Test
    fun testValidFirstPartFloatStatePeriod(){
        floatVerifier.state = ValidFirstPartFloatState()
        validFirstPartFloatState.consumeCharacter(".", floatVerifier)
        assertEquals(floatVerifier.state is FirstDigitAfterPeriodState, true)
    }
    /**
     * @ValidSecondPartFloatState
     */
    @Test
    fun testValidSecondPartFloatStateValid(){
        floatVerifier.state = ValidFloatState()
        validSecondPartFloatState.consumeCharacter("3", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
        validSecondPartFloatState.consumeCharacter("6", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
        validSecondPartFloatState.consumeCharacter("9", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
    }
    @Test
    fun testValidSecondPartFloatStateInvalid(){
        floatVerifier.state = ValidFloatState()
        validSecondPartFloatState.consumeCharacter(".", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = ValidFloatState()
        validSecondPartFloatState.consumeCharacter("a", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = ValidFloatState()
        validSecondPartFloatState.consumeCharacter("?", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }

    /**
     * @FirstDigitAfterPeriodState
     */
    @Test
    fun testFirstDigitAfterPeriodStateValid(){
        floatVerifier.state = FirstDigitAfterPeriodState()
        firstDigitAfterPeriodState.consumeCharacter("3", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
        floatVerifier.state = FirstDigitAfterPeriodState()
        firstDigitAfterPeriodState.consumeCharacter("6", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
        floatVerifier.state = FirstDigitAfterPeriodState()
        firstDigitAfterPeriodState.consumeCharacter("9", floatVerifier)
        assertEquals(floatVerifier.state is ValidFloatState, true)
    }
    @Test
    fun testFirstDigitAfterPeriodStateInvalid(){
        floatVerifier.state = FirstDigitAfterPeriodState()
        firstDigitAfterPeriodState.consumeCharacter(".", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = FirstDigitAfterPeriodState()
        firstDigitAfterPeriodState.consumeCharacter("a", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }

    /**
     * @PeriodAfterZero
     */
    @Test
    fun testPeriodAfterZeroValid(){
        floatVerifier.state = PeriodAfterZeroState()
        periodAfterZeroState.consumeCharacter(".", floatVerifier)
        assertEquals(floatVerifier.state is FirstDigitAfterPeriodState, true)
    }
    @Test
    fun testPeriodAfterZeroInvalid(){
        floatVerifier.state = PeriodAfterZeroState()
        periodAfterZeroState.consumeCharacter("a", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = PeriodAfterZeroState()
        periodAfterZeroState.consumeCharacter("3", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.state = PeriodAfterZeroState()
        periodAfterZeroState.consumeCharacter("", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }

    /**
     * @InvalidFloatState
     */
    @Test
    fun testInvalidFloatState(){
        floatVerifier.state = InvalidFloatState()
        invalidFloatState.consumeCharacter("", floatVerifier)
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }

    /**
     * @FloatVerifier
     */
    @Test
    fun testFloatVerifierValid(){
        floatVerifier.verify("1234.1234")
        assertEquals(floatVerifier.state is ValidFloatState, true)
        floatVerifier.verify(".1234")
        assertEquals(floatVerifier.state is ValidFloatState, true)
        floatVerifier.verify("0.1234")
        assertEquals(floatVerifier.state is ValidFloatState, true)
    }
    @Test
    fun testFloatVerifierInvalidDigit(){
        floatVerifier.verify("1a234.1234")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.verify(".1a234")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.verify("0.123a4")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }
    @Test
    fun testFloatVerifierInvalidDigitAfterZero(){
        floatVerifier.verify("02.1234")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.verify("0a.1234")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
        floatVerifier.verify("02343.123a4")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }
    @Test
    fun testFloatVerifierInvalidNoPeriod(){
        floatVerifier.verify("1234")
        assertEquals(floatVerifier.state is ValidFirstPartFloatState, true)
        floatVerifier.verify("1")
        assertEquals(floatVerifier.state is ValidFirstPartFloatState, true)
        floatVerifier.verify("9872348798275987098703984798729384")
        assertEquals(floatVerifier.state is ValidFirstPartFloatState, true)
    }
    @Test
    fun testFloatVerifierInvalidDigitNothingAfterPeriod(){
        assertEquals(floatVerifier.verify("123."), false)
        assertEquals(floatVerifier.verify("1."), false)
    }
    @Test
    fun testFloatVerifierInvalidEmptyInput(){
        floatVerifier.verify("")
        assertEquals(floatVerifier.state is InvalidFloatState, true)
    }
}
