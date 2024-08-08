class FloatVerifier {
    lateinit var state: FloatState

    fun verify(string: String): Boolean{
        state = FirstFloatDigitState()

        if(string.isEmpty()){
            state = InvalidFloatState()
            return false
        }
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is ValidSecondPartFloatState
    }
}