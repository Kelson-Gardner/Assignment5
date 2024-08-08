fun main(args: Array<String>) {
    val verifier = BinaryVerifier()

    println(verifier.verify("1"))
    println(verifier.verify("11"))
    println(verifier.verify("101"))
    println(verifier.verify("111000010111010100000101010111001010101111101001010101010111110000000000000000000111101010101101"))
    println(verifier.verify("0"))
    println(verifier.verify("10"))
    println(verifier.verify("01"))
    println(verifier.verify("10101010101a00010011"))
}