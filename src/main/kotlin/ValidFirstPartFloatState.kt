class ValidFirstPartFloatState: FloatState {
    override fun consumeCharacter(char: String, floatVerifier: FloatVerifier) {
        if(char == ".") {
            floatVerifier.state = FirstDigitAfterPeriodState()
        } else if(char !in "0123456789") {
            floatVerifier.state = InvalidFloatState()
        }
    }
}