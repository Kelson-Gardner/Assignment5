class FirstFloatDigitState: FloatState {
    override fun consumeCharacter(char: String, floatVerifier: FloatVerifier) {
        if(char in "123456789"){
            floatVerifier.state = ValidFirstPartFloatState()
        } else if (char == "."){
            floatVerifier.state = FirstDigitAfterPeriodState()
        } else if(char == "0"){
            floatVerifier.state = PeriodAfterZeroState()
        } else {
            floatVerifier.state = InvalidFloatState()
        }
    }
}