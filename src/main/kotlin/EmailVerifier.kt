class EmailVerifier {
    lateinit var state: EmailState

    fun verify(string: String): Boolean{
        state = FirstCharacterEmailPart1State()

        if(string.isEmpty()){
            state = InvalidEmailState()
            return false
        }
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is ValidEmailState
    }
}