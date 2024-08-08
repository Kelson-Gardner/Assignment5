class PeriodAfterZeroState: FloatState {
    override fun consumeCharacter(char: String, floatVerifier: FloatVerifier) {
        if(char == "."){
            floatVerifier.state = FirstDigitAfterPeriodState()
        } else {
            floatVerifier.state = InvalidFloatState()
        }
    }
}