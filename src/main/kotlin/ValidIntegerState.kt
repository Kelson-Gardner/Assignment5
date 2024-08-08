class ValidIntegerState: IntegerState {
    override fun consumeCharacter(char: String, integerVerifier: IntegerVerifier) {
        if(char !in "0123456789"){
            integerVerifier.state = InvalidIntegerState()
        }
    }
}