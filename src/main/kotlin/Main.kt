fun main(args: Array<String>) {
    val verifier = PasswordVerifier()

    println(verifier.verify("aaaaH!aa"))
    println(verifier.verify("1234567*9J"))
    println(verifier.verify("asdpoihj;loikjasdf;ijp;lij2309jasd;lfkm20ij@aH"))
    println(verifier.verify("a"))
    println(verifier.verify("aaaaaaa!"))
    println(verifier.verify("aaaHaaaaa"))
    println(verifier.verify("Abbbbbbb!"))
    println(verifier.verify("aaaaa!aaaaaaaaa"))
}