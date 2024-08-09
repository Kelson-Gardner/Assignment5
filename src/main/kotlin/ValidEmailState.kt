class ValidEmailState: EmailState {
    override fun consumeCharacter(char: String, emailVerifier: EmailVerifier) {
        if(char in "@. "){
            emailVerifier.state = InvalidEmailState()
        }
    }
}