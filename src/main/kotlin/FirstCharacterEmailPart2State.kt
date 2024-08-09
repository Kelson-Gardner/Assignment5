class FirstCharacterEmailPart2State: EmailState {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if(char in "@ ."){
            emailVerifier.state = InvalidEmailState()
        } else {
            emailVerifier.state = EmailPart2State()
        }
    }
}