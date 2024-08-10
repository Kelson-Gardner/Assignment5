class EndInSpecialCharacterState: PasswordState {
    override fun consumeCharacter(char: String, passwordVerifier: PasswordVerifier) {
        if(char !in "!@#\\\$%&*"){
            passwordVerifier.state = ValidPasswordState()
        }
    }
}