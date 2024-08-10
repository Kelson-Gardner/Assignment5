class ValidPasswordState: PasswordState {
    override fun consumeCharacter(char: String, passwordVerifier: PasswordVerifier) {
        if(char in "!@#\\$%&*"){
            passwordVerifier.state = EndInSpecialCharacterState()
        }
    }
}