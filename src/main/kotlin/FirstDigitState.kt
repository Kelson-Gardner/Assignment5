class FirstDigitState: IntegerState {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if(char in "123456789"){
            integerVerifier.state = ValidIntegerState()
        } else {
            integerVerifier.state = InvalidIntegerState()
        }
    }
}