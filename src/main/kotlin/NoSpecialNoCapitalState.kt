class NoSpecialNoCapitalState: PasswordState {
    override fun consumeCharacter(char: String, passwordVerifier: PasswordVerifier) {
        if(char in "!@#\\$%&*"){
            passwordVerifier.state = NoCapitalLetterState()
        }
        if(char in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"){
            passwordVerifier.state = NoSpecialCharacterState()
        }
    }
}