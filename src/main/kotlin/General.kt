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
    fun hasPeachCard(): Boolean {
        val chance = (1..100).random()
        return chance <= 20 * numOfCards
    }

    fun hasWineCard(): Boolean {
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
        if(currentHP <= 0){
            if(hasPeachCard()){
                println("$name use Peach!")
                currentHP++
                numOfCards--
            }
            if(hasWineCard()){
                println("$name use Wine!")
                currentHP++
                numOfCards--
            }
        }
    }
    fun beingWineAttacked() {
        println("$name is being attacked.")
        val dodgeSuccess = hasDodgeCard()
        (strategy as? LordStrategy)?.notifyObservers(dodgeSuccess)
        if (!dodgeSuccess) {
            currentHP = currentHP -2
            println("$name couldn't dodge the attack and loses 1 HP. Current HP: $currentHP")
        }
        if(currentHP <= 0){
            if(usePeach()){
                println("$name use Peach!")
            }
            if(useWine()){
                println("$name use Wine!")
                currentHP++
            }
        }
    }
    fun dodgeAttack(): Boolean  {
        println("$name dodged the attack!")
        return true
    }

    fun usePeach():Boolean{
        if(currentHP < maxHP){
            if(hasPeachCard()){
                currentHP++
                numOfCards--
                return true
            }
        }
        return false
    }

    fun useWine(): Boolean{
        if(hasWineCard()){
            numOfCards--
            return true
        }
        return false
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
        if (!hasAttackCard()) {
            if (useWine()) {
                val target = strategy.whomToAttack(this, GeneralManager.list.filter { it != this })
                if (target != null) {
                    println("$name spends a card to attack ${target.identity}, ${target.name}")
                    numOfCards--
                    target.beingAttacked()
                }
            } else {
                val target = strategy.whomToAttack(this, GeneralManager.list.filter { it != this })
                if (target != null) {
                    println("$name spends a card to attack ${target.identity}, ${target.name}")
                    numOfCards--
                    target.beingWineAttacked()
                }
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

class SUNQuan(name: String) : General(name, 5, Identity.LORD, LordStrategy()) {}

class SIMaYi(name: String) : WeiGeneral(name, 3)

class XIAHouDun(name: String) : WeiGeneral(name, 4)

class ZHANGLiao(name: String) : WeiGeneral(name, 4) {
    override fun drawPhase() {
        // 放弃正常摸牌，发动突袭
        println("$name activates [Raid]")

        val validTargets = GeneralManager.list
            .filter { it != this && it.numOfCards > 0 }
            .shuffled()
            .take(2)  // 随机选择最多2名有手牌的角色

        validTargets.forEach { target ->
            target.numOfCards--
            numOfCards++
            println("[Raid] Stole 1 card from ${target.name}") // 统一技能提示格式
        }

        if (validTargets.isEmpty()) {
            println("[Raid] No valid targets found") // 处理空目标情况
        }
    }

    override fun toString(): String {
        return "$name, a ${identity.name.lowercase()} (Raid)" // 显示技能标识
    }
}

class XUChu(name: String) : WeiGeneral(name, 4) {
    override fun playPhase() {
        super.playPhase()
        if (hasAttackCard()) {
            val target = strategy.whomToAttack(this, GeneralManager.list.filter { it != this })
            if (target != null) {
                println("$name spends a card to attack ${target.identity}, ${target.name}")
                numOfCards--
                target.beingAttacked()
            }
        }
    }
}

class ZHENJi(name: String) : General(name, 3, Identity.REBEL, RebelStrategy()) {
    // 洛神技能参数（黑牌成功率50%，最大判定次数5次）
    private val divineGraceSuccessRate = 50
    private val maxDivineGraceAttempts = 5

    // 倾国技能参数（闪避概率加成倍数）
    private val captivatingBeautyMultiplier = 2

    override fun judgmentPhase() {
        println("$name activates [Divine Grace]") // 技能名称使用英文
        var obtainedCards = 0
        repeat(maxDivineGraceAttempts) {
            val mockCard = CardFactory.createCard("Peach", Suit.values().random(), "Q")
            print("Divine Grace judgment: ${mockCard.suit.name.lowercase()} ") // 判定过程英文描述

            if (mockCard.suit == Suit.SPADE || mockCard.suit == Suit.CLUB) {
                obtainedCards++
                numOfCards++
                println("→ Success (Total gained: $obtainedCards)") // 结果提示保持英文
            } else {
                println("→ Terminated")
                return@repeat
            }
        }
        println("$name obtains $obtainedCards cards through Divine Grace")
    }

    override fun hasDodgeCard(): Boolean {
        // 倾国技能核心逻辑：每张手牌视为闪的概率翻倍
        val enhancedChance = (15 * captivatingBeautyMultiplier * numOfCards).coerceAtMost(95)
        return (1..100).random() <= enhancedChance
    }

    override fun beingAttacked() {
        println("$name activates [Captivating Beauty] for defense") // 技能名称英文
        super.beingAttacked()
    }

    override fun toString(): String {
        return "$name, a ${identity.name.lowercase()} (Divine Grace/Captivating Beauty)" // 技能组合显示
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

class ZHANGFei(name: String) : General(name, 4, Identity.REBEL, RebelStrategy()) {
    override fun playPhase() {
        // 咆哮：出牌阶段可以无限出杀（只要手牌足够）
        var attackCount = 0
        while (numOfCards > 0) { // 只要还有手牌就能继续攻击
            val target = strategy.whomToAttack(this, GeneralManager.list.filter { it != this })
            if (target == null) break

            println("[Roar] $name uses a card to attack ${target.name}")
            numOfCards-- // 每次攻击消耗一张手牌
            target.beingAttacked()
            attackCount++
        }
    }

    override fun hasAttackCard(): Boolean {
        // 覆盖父类逻辑，强制认为只要有手牌就能出杀
        return numOfCards > 0
    }
}

class ZHAOYun(name: String) : General(name, 4, Identity.LOYALIST, LoyalistStrategy()) {

    // 龙胆技能实现：杀闪互转
    override fun hasAttackCard(): Boolean {
        // 攻击时可使用杀或闪，概率提升至35%每张牌（标准武将的1.75倍）
        val chance = (1..100).random()
        return chance <= 35 * numOfCards
    }

    override fun hasDodgeCard(): Boolean {
        // 闪避时可使用闪或杀，概率提升至30%每张牌（标准武将的2倍）
        val chance = (1..100).random()
        return chance <= 30 * numOfCards
    }

    override fun toString(): String {
        return "$name, a ${identity.name.lowercase()} (Longdan Master)"
    }

    // 重写受伤逻辑以体现防御优势
    override fun beingAttacked() {
        println("$name activates [Longdan] to defend.")
        super.beingAttacked()
    }
}

class GANNing(name: String) : General(name, 4, Identity.REBEL, RebelStrategy()) {
    // 奇袭技能参数
    private val surpriseAttackThreshold = 45 // 每张黑牌发动概率45%
    private val maxSurpriseAttempts = 3      // 最大发动次数限制

    override fun playPhase() {
        var attackCount = 0
        // 优先发动奇袭技能
        repeat(maxSurpriseAttempts) {
            if (canSurpriseAttack()) {
                executeSurpriseAttack()
                attackCount++
            }
        }
        // 剩余行动力执行普通攻击
        if (attackCount == 0) super.playPhase()
    }

    private fun canSurpriseAttack(): Boolean {
        // 需要同时满足：有手牌、概率触发、存在可攻击目标
        return numOfCards > 0 &&
                (1..100).random() <= surpriseAttackThreshold &&
                GeneralManager.list.any { it != this && it.numOfCards > 0 }
    }

    private fun executeSurpriseAttack() {
        val target = strategy.whomToAttack(
            this,
            GeneralManager.list.filter { it != this && it.numOfCards > 0 }
        )
        target?.apply {
            println("[$name] uses [Surprise Attack] on ${target.name}")
            numOfCards-- // 弃置黑牌
            target.numOfCards = (target.numOfCards - 1).coerceAtLeast(0) // 目标弃牌
            println("${target.name} discards 1 card (Remaining: ${target.numOfCards})")
        }
    }

    override fun toString(): String {
        return "$name, a ${identity.name.lowercase()} (Surprise Attack)"
    }
}

//class ZHOUYu(name: String) : WeiGeneral(name, 4) {
//    override fun judgmentPhase() {
//        println("$name activates [Captivating Beauty]")
//        val captivatingBeautySuccess = (1..100).random() <= captivatingBeautyMultiplier
//        if (captivatingBeautySuccess) {
//            println("$name gains 1 card.")
//            numOfCards++
//        } else {
//            println("$name loses 1 card.")
//            numOfCards--
//        }
//    }
//}
class ZHOUYu(name: String) : General(name, 3, Identity.SPY, SpyStrategy()) {
    override fun drawPhase() {
        val drawnCards = 3
        numOfCards += drawnCards
        println("[Heroism] $name draws $drawnCards cards and now has $numOfCards card(s).")
    }
}

class DiaoChan(name: String) : General(name, 3, Identity.SPY, SpyStrategy()) {
    override fun discardPhase() {
        super.discardPhase()
        numOfCards++
        println("[Beauty Outshining the Moon] $name now has $numOfCards card(s).")
    }
}

class XiahouYuan(name: String) : WeiGeneral(name, 4)

