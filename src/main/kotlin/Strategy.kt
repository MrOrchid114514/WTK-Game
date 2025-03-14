interface Strategy {
    fun whomToAttack(attacker: Player, players: List<Player>): Player?
}

open class LordStrategy : Strategy, Subject {
    private val observers = mutableListOf<Observer>()

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers(dodgeSuccess: Boolean) {
        observers.forEach { it.update(dodgeSuccess) }
    }
    override fun whomToAttack(attacker: Player, players: List<Player>): Player? {
        return players.filter { it.identity == Identity.REBEL }.randomOrNull()
    }
}

class LoyalistStrategy : Strategy {
    override fun whomToAttack(attacker: Player, players: List<Player>): Player? {
        return players.filter { it.identity == Identity.REBEL }.randomOrNull()
    }
}

class SpyStrategy : Strategy, Observer {
    private var riskLevel: Double = 50.0

    override fun update(dodgeSuccess: Boolean) {
        riskLevel = if (dodgeSuccess) riskLevel * 0.5 else riskLevel * 1.5
        println("Spy's Risk Level: ${riskLevel.toInt()}")
    }

    override fun whomToAttack(attacker: Player, players: List<Player>): Player? {
        println("${attacker.name} is observing lord.")
        return players.filter { it.identity == Identity.REBEL }.randomOrNull()
    }
}

class RebelStrategy : Strategy {
    override fun whomToAttack(attacker: Player, players: List<Player>): Player? {
        return players.firstOrNull { it.identity == Identity.LORD }
    }
}

class LiuBeiStrategy : LordStrategy() {
    override fun whomToAttack(attacker: Player, players: List<Player>): Player? {
        return players.filter { it.identity == Identity.REBEL }.randomOrNull()
    }
}

enum class Identity {
    LORD,
    LOYALIST,
    SPY,
    REBEL
}