class FirstCharacterEmailPart1State : EmailState {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if(char in " @"){
            emailVerifier.state = InvalidEmailState()
        } else {
            emailVerifier.state = EmailPart1State()
        }
    }
}