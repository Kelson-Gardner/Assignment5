class FirstBinaryDigitState: BinaryState {
    override fun consumeCharacter(char: String, binaryVerifier: BinaryVerifier) {
        if(char == "1"){
            binaryVerifier.state = ValidBinaryState()
        } else {
            binaryVerifier.state = InvalidBinaryState()
        }
    }
}