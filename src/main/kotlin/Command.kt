interface Command {
    fun execute(player: Player)
}

class AcediaCommand : Command {
    override fun execute(player: Player) {
        println("${player.name} judging the Acedia card.")
        val chance = (1..4).random()
        if (chance == 1) {
            println("${player.name} dodged the Acedia card!")
        } else {
            println("${player.name} can't dodge the Acedia card. Skipping one round of Play Phase.")
            player.skipPlayPhase = true
        }
    }
}