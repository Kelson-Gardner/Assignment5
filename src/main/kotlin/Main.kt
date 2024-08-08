fun main(args: Array<String>) {
    val verifier = FloatVerifier()

    println(verifier.verify("123.123"))
    println(verifier.verify(".123"))
    println(verifier.verify("0.123"))
    println(verifier.verify("1234"))
    println(verifier.verify("1a3.123"))
    println(verifier.verify("02.123"))
    println(verifier.verify("123.1a3"))
    println(verifier.verify("123.12.3"))
    println(verifier.verify("123."))
    println(verifier.verify("1."))
}