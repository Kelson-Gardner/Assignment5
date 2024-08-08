class FirstDigitAfterPeriodState: FloatState {
    override fun consumeCharacter(char: String, floatVerifier: FloatVerifier) {
        if(char !in "0123456789"){
            floatVerifier.state = InvalidFloatState()
        } else {
            floatVerifier.state = ValidFloatState()
        }
    }
}