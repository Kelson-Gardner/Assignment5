class IntegerVerifier {
    lateinit var state: IntegerState

    fun verify(string: String): Boolean{
        //TODO: for password verifier make sure to check that the password is greater than 8 characters long
        state = FirstDigitState()
        if(string.isEmpty()){
            state = InvalidIntegerState()
            return false
        }
        string.chunked(1).forEach {
            state.consumeCharacter(it, this)
        }
        return state is ValidIntegerState
    }
}