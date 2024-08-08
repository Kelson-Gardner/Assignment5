class ZeroBinaryDigitState: BinaryState {
    override fun consumeCharacter(char: String, binaryVerifier: BinaryVerifier) {
        if(char !in "01"){
            binaryVerifier.state = InvalidBinaryState()
        } else if(char == "1"){
            binaryVerifier.state = ValidBinaryState()
        }
    }
}