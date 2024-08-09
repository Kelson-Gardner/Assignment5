class EmailPart2State: EmailState {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if(char in "@ "){
            emailVerifier.state = InvalidEmailState()
        }
        if(char == "."){
            emailVerifier.state = FirstCharacterEmailPart3State()
        }
    }
}