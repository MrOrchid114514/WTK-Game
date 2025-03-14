abstract class WeiGeneral(name: String, maxHP: Int) : General(name, maxHP, Identity.REBEL, RebelStrategy()) {
    var next: WeiGeneral? = null

    fun handleRequest(): Boolean {
        if ((1..2).random() == 1) {
            println("$name helps Cao Cao dodge an attack by spending a dodge card.")
            return true
        }
        return next?.handleRequest() ?: false
    }
}