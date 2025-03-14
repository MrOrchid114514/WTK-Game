object GeneralManager{
    val list: MutableList<Player> = mutableListOf()

    fun getLord(): Player? {
        return list.find { it.identity == Identity.LORD }
    }

    init {
        println("Setting up the general manager.")
    }

    fun addGeneral(general: Player){
        list.add(general)
        general.currentHP = general.maxHP
        println("General ${general.name} created.")
        println("${general}, has ${general.currentHP} health point(s).")
    }

    fun removeGeneral(general: Player){
        list.remove(general)
    }

    fun createGenerals(lords: Int, loyalists: Int, spies: Int, rebels: Int) {
        val lordFactory = LordFactory()
        repeat(lords) {
            val general = lordFactory.createRandomGeneral()
            addGeneral(general)
        }

        val nonLordFactory = NonLordFactory(Identity.LOYALIST)
        repeat(loyalists) {
            val loyalist = nonLordFactory.createRandomGeneral()
            addGeneral(loyalist)
        }
        val weiChain = nonLordFactory.createWeiChain()
        if (weiChain != null) {
            val caoCao = list.find { it.name == "CAO Cao" } as? CAOCao
            caoCao?.weiChain = weiChain
        }

        repeat(spies) {
            val spy = NonLordFactory(Identity.SPY).createRandomGeneral()
            addGeneral(spy)
        }

        repeat(rebels) {
            val rebel = NonLordFactory(Identity.REBEL).createRandomGeneral()
            addGeneral(rebel)
        }
    }

    fun getGeneral(): Int{
        return list.size
    }

    fun getGeneralsList(): List<Player> {
        return list
    }

    fun placeAcediaCard(player: Player) {
        (player as General).judgmentCommand = AcediaCommand()
        println("${player.name} being placed the Acedia card.")
    }

    fun gameStart() {
        for (general in list) {
            general.playTurn()
        }

        val caoCao = list.find { it.name == "CAO Cao" }
        caoCao?.beingAttacked()
    }
}