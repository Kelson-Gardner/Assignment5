class EmailPart1State: EmailState {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if(char == " "){
            emailVerifier.state = InvalidEmailState()
        }
        if(char == "@"){
            emailVerifier.state = FirstCharacterEmailPart2State()
        }
    }
}