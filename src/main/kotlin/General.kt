import java.lang.reflect.Type

abstract class General(override val name : String, override var maxHP: Int, override val identity: Identity,
                       override val strategy: Strategy) : Player {
    override var currentHP: Int = 0
    override var numOfCards: Int = 4
    override var skipPlayPhase: Boolean = false
    var judgmentCommand: Command? = null

    override fun toString(): String {
        return "$name, a ${identity.name.lowercase()}"
    }

    override fun playTurn() {
        judgmentPhase()
        if (!skipPlayPhase) {
            drawPhase()
            playPhase()
            discardPhase()
        } else {
            println("${name} skips the Play Phase.")
            skipPlayPhase = false
        }
    }

    override fun judgmentPhase() {
        judgmentCommand?.execute(this)
        judgmentCommand = null
    }
}

interface Player {
    val name: String
    var currentHP: Int
    var maxHP: Int
    var numOfCards: Int
    var skipPlayPhase: Boolean
    val identity: Identity
    val strategy: Strategy

    fun hasAttackCard(): Boolean {
        val chance = (1..100).random()
        return chance <= 20 * numOfCards
    }

    fun hasDodgeCard(): Boolean {
        val chance = (1..100).random()
        return chance <= 15 * numOfCards
    }

    fun beingAttacked() {
        println("$name is being attacked.")
        val dodgeSuccess = hasDodgeCard()
        (strategy as? LordStrategy)?.notifyObservers(dodgeSuccess)
        if (!dodgeSuccess) {
            currentHP--
            println("$name couldn't dodge the attack and loses 1 HP. Current HP: $currentHP")
        }
    }

    fun dodgeAttack(): Boolean {
        println("$name dodged the attack!")
        return true
    }

    fun playTurn() {
        drawPhase()
        playPhase()
        discardPhase()
        judgmentPhase()
    }

    fun drawPhase() {
        val drawnCards = 2
        numOfCards += drawnCards
        println("$name draws $drawnCards cards and now has $numOfCards card(s).")
    }

    fun playPhase() {
        if (hasAttackCard()) {
            val target = strategy.whomToAttack(this, GeneralManager.list.filter { it != this })
            if (target != null) {
                println("$name spends a card to attack ${target.identity}, ${target.name}")
                numOfCards--
                target.beingAttacked()
            }
        }
    }

    fun discardPhase() {
        val discardedCards = numOfCards - currentHP
        if (discardedCards > 0){
            numOfCards -= discardedCards
            println("$name discards $discardedCards card(s), now has $numOfCards card(s).")
        }
    }

    fun judgmentPhase() {}
}

class LIUBei(name: String) : General(name, 1, Identity.LORD, LiuBeiStrategy()) {
    private var currentState: State = if (currentHP >= 2) HealthyState() else UnhealthyState()

    fun checkHealthState() {
        currentState = if (currentHP >= 2) {
            println("${name} is now healthy.")
            HealthyState()
        } else {
            println("${name} is not healthy.")
            UnhealthyState()
        }
    }

    override fun playPhase() {
        currentState.playNextCard(this)
    }

    override fun drawPhase() {
        super.drawPhase()
        checkHealthState()
    }
}

class CAOCao(name: String) : General(name, 5, Identity.LORD, LordStrategy()) {
    var weiChain: WeiGeneral? = null
    override fun beingAttacked() {
        println("$name is being attacked.")
        println("[Entourage] $name activates Lord Skill Entourage.")
        if (weiChain?.handleRequest() != true) {
            val dodgeSuccess = hasDodgeCard()
            (strategy as? LordStrategy)?.notifyObservers(dodgeSuccess)
            if (!dodgeSuccess) {
                currentHP--
                println("$name couldn't dodge the attack and loses 1 HP. Current HP: $currentHP")
            }
        }

    }
}

class SUNQuan(name: String) : General(name, 5, Identity.LORD, LordStrategy()) {}

class DiaoChan(name: String) : General(name, 3, Identity.SPY, SpyStrategy()) {
    override fun discardPhase() {
        super.discardPhase()
        numOfCards++
        println("[Beauty Outshining the Moon] $name now has $numOfCards card(s).")
    }
}

class GUANYu{
    val maximumHP = 4
}

class GUANYuAdapter(name: String) : General(name, 4, Identity.LOYALIST, LoyalistStrategy()) {
    private val guanYu = GUANYu()

    override val name: String = "GUAN Yu"
    override var currentHP: Int = guanYu.maximumHP
    override var maxHP: Int = guanYu.maximumHP

    override fun beingAttacked() {
        println("$name is being attacked.")
        if (currentHP > 1) {
            currentHP--
            println("$name can't dodge the attack, current HP is $currentHP")
        } else {
            println("$name has been defeated!")
        }
    }

    override fun dodgeAttack(): Boolean {
        println("$name dodged the attack!")
        return true
    }
}

class ZHOUYu(name: String) : General(name, 3, Identity.SPY, SpyStrategy()) {
    override fun drawPhase() {
        val drawnCards = 3
        numOfCards += drawnCards
        println("[Heroism] $name draws $drawnCards cards and now has $numOfCards card(s).")
    }
}

class SimaYi(name: String) : WeiGeneral(name, 3)
class XiahouDun(name: String) : WeiGeneral(name, 4)
class XiahouYuan(name: String) : WeiGeneral(name, 4)