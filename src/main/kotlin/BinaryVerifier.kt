class BinaryVerifier {
    lateinit var state: BinaryState

    fun verify(string: String): Boolean{
        state = FirstBinaryDigitState()

        if(string.isEmpty()){
            state = InvalidBinaryState()
            return false
        }
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is ValidBinaryState
    }
}