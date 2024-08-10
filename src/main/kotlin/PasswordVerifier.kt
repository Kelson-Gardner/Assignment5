class PasswordVerifier {
    lateinit var state: PasswordState

    fun verify(string: String): Boolean{
        state = NoSpecialNoCapitalState()

        if(string.length < 8){
            state = InvalidPasswordState()
            return false
        }
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is ValidPasswordState
    }
}