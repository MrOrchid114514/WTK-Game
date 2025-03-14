import kotlin.random.Random

abstract class GeneralFactory {
    abstract fun createRandomGeneral(): General
}

class LordFactory : GeneralFactory() {
    private val lords = listOf("LIU Bei", "CAO Cao", "SUN Quan")

    override fun createRandomGeneral(): General {
        val randomLordName = lords.random()
        return when (randomLordName) {
            "LIU Bei" -> LIUBei(randomLordName).apply { currentHP = 1 }
            "CAO Cao" -> CAOCao(randomLordName)
            "SUN Quan" -> SUNQuan(randomLordName)
            else -> throw IllegalArgumentException("Unknown lord name: $randomLordName")
        }
    }
}

class NonLordFactory(private val identity: Identity) : GeneralFactory(){
    private val nonLords = mutableSetOf("DIAO Chan", "GUAN Yu", "ZHOU Yu", "SIMA Yi", "XIAHOU Dun", "XIAHOU Yuan")
    private val nonLords_LOYAL = mutableSetOf("GUAN Yu")
    private val nonLords_SPY = mutableSetOf("DIAO Chan", "ZHOU Yu")
    private val nonLords_REBEL = mutableSetOf("SIMA Yi", "XIAHOU Dun", "XIAHOU Yuan")

    override fun createRandomGeneral(): General {
        if (nonLords.isEmpty()) throw IllegalStateException("No more non-lords available.")
        val Name_LOYAL = nonLords_LOYAL.random()
        nonLords_LOYAL.remove(Name_LOYAL)
        val Name_SPY = nonLords_SPY.random()
        nonLords_SPY.remove(Name_SPY)
        val Name_REBEL = nonLords_REBEL.random()
        nonLords_REBEL.remove(Name_REBEL)
        return when (identity) {
            Identity.LOYALIST -> when (Name_LOYAL) {
                "GUAN Yu" -> GUANYuAdapter(Name_LOYAL)
                else -> throw IllegalArgumentException("Invalid identity: $identity")
            }
            Identity.SPY -> {
                val spy = when (Name_SPY) {
                    "DIAO Chan" -> DiaoChan(Name_SPY)
                    "ZHOU Yu" -> ZHOUYu(Name_SPY)
                    else -> {throw IllegalArgumentException("Invalid identity: $identity")}
                }
                val lord = GeneralManager.getLord()
                if (lord != null) {
                    (lord.strategy as? LordStrategy)?.registerObserver(spy.strategy as Observer)
                    println("${spy.name} is observing lord.")
                }
                spy
            }
            Identity.REBEL -> when (Name_REBEL) {
                "SIMA Yi" -> SimaYi(Name_REBEL)
                "XIAHOU Dun" -> XiahouDun(Name_REBEL)
                "XIAHOU Yuan" -> XiahouYuan(Name_REBEL)
                else -> throw IllegalArgumentException("Invalid identity: $identity")
            }
            else -> throw IllegalArgumentException("Invalid identity: $identity")
        }
    }

    fun createWeiChain(): WeiGeneral? {
        val weiGenerals = nonLords.filter { it in setOf("SIMA Yi", "XIAHOU Dun", "XIAHOU Yuan") }.toMutableSet()
        var chain: WeiGeneral? = null
        for (weiGeneralName in weiGenerals) {
            nonLords.remove(weiGeneralName)
            val general = when (weiGeneralName) {
                "SIMA Yi" -> SimaYi(weiGeneralName)
                "XIAHOU Dun" -> XiahouDun(weiGeneralName)
                "XIAHOU Yuan" -> XiahouYuan(weiGeneralName)
                else -> throw IllegalStateException("Unexpected non-Wei general in Wei chain.")
            }
            general.next = chain
            chain = general
            println("${general.name} added to the Wei chain.")
        }

        return chain
    }
}