interface State {
    fun playNextCard(player: LIUBei)
}

class HealthyState : State {
    override fun playNextCard(player: LIUBei) {
        val target = player.strategy.whomToAttack(player, GeneralManager.list.filter { it != player })
        if (player.hasAttackCard() && target != null) {
            println("${player.name} spends a card to attack ${target.identity}, ${target.name}")
            player.numOfCards--
            target.beingAttacked()
        } else {
            println("${player.name} doesn't have attack card.")
        }
    }
}

class UnhealthyState : State {
    override fun playNextCard(player: LIUBei) {
        if (player.numOfCards >= 2) {
            player.numOfCards -= 2
            player.currentHP++
            println("[Benevolence] ${player.name} gives away two cards and recovers 1 HP, now his HP is ${player.currentHP}.")
            player.checkHealthState()
        } else {
            println("${player.name} cannot activate Benevolence due to insufficient cards.")
        }
    }
}