fun main(args: Array<String>) {
    val verifier = EmailVerifier()

    println(verifier.verify("a@b.c"))
    println(verifier.verify("joseph.ditton@usu.edu"))
    println(verifier.verify("{}*\$.&\$*(@*\$%&.*&*"))
    println(verifier.verify("@b.c"))
    println(verifier.verify("a@b@c.com"))
    println(verifier.verify("a.b@b.b.c"))
    println(verifier.verify("joseph ditton@usu.edu"))
    println(verifier.verify("josephditton@us u.edu"))
    println(verifier.verify("josephditton@usu.ed u"))
}