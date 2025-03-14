abstract class Equipment(val player: Player) : Player {
    override var currentHP: Int
        get() = player.currentHP
        set(value) { player.currentHP = value }

    override var maxHP: Int
        get() = player.maxHP
        set(value) { player.maxHP = value }

    override val name: String
        get() = player.name

    override fun dodgeAttack(): Boolean {
        return player.dodgeAttack()
    }

    override fun beingAttacked() {
        return player.beingAttacked()
    }
}