enum class Suit {
    SPADE, HEART, CLUB, DIAMOND
}

interface Card {
    val name: String
    val suit: Suit
    val rank: String
    fun use()
}

data class Lightning(override val name: String = "Lightning", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Duel(override val name: String = "Duel", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class EightTrigramsFormation(override val name: String = "Eight Trigrams Formation", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class YinYangSwords(override val name: String = "Yin Yang Swords", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class BurningBridges(override val name: String = "Burning Bridges", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class StealingSheep(override val name: String = "Stealing Sheep", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class GreenDragonBlade(override val name: String = "Green Dragon Blade", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Shadowrunner(override val name: String = "Shadowrunner", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Acedia(override val name: String = "Acedia", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class BlueSteelBlade(override val name: String = "Blue Steel Blade", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class BarbarianInvasion(override val name: String = "Barbarian Invasion", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Negation(override val name: String = "Negation", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class SerpentSpear(override val name: String = "Serpent Spear", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class FerghanaHorse(override val name: String = "Ferghana horse", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Brotherhood(override val name: String = "Brotherhood", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class RainingArrows(override val name: String = "Raining Arrows", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Dodge(override val name: String = "Dodge", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Attack(override val name: String = "Attack", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class Peach(override val name: String = "Peach", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class BumperHarvest(override val name: String = "Bumper Harvest", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class KirinBow(override val name: String = "Kirin Bow", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class RedHare(override val name: String = "Red Hare", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class SomethingOutOfNothing(override val name: String = "Something out of nothing", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class FlyingLightning(override val name: String = "Flying Lightning", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class ZhugeCrossbow(override val name: String = "Zhuge Crossbow", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class HexMark(override val name: String = "Hex Mark", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class RockCleavingAxe(override val name: String = "Rock Cleaving Axe", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class SkyPiercingHalberd(override val name: String = "Sky Piercing Halberd", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

data class VioletStallion(override val name: String = "Violet Stallion", override val suit: Suit, override val rank: String) : Card {
    override fun use() {
        println("$name ($suit $rank)")
    }
}

object CardFactory {
    fun createCard(type: String, suit: Suit, rank: String): Card {
        return when (type) {
            "Lightning" -> Lightning(suit = suit, rank = rank)
            "Zhuge Crossbow" -> ZhugeCrossbow(suit = suit, rank = rank)
            "Eight Trigrams Formation" -> EightTrigramsFormation(suit = suit, rank = rank)
            "Burning Bridges" -> BurningBridges(suit = suit, rank = rank)
            "Green Dragon Blade" -> GreenDragonBlade(suit = suit, rank = rank)
            "Acedia" -> Acedia(suit = suit, rank = rank)
            "Barbarian Invasion" -> BarbarianInvasion(suit = suit, rank = rank)
            "Negation" -> Negation(suit = suit, rank = rank)
            "Serpent Spear" -> SerpentSpear(suit = suit, rank = rank)
            "Ferghana Horse" -> FerghanaHorse(suit = suit, rank = rank)
            "Brotherhood" -> Brotherhood(suit = suit, rank = rank)
            "Dodge" -> Dodge(suit = suit, rank = rank)
            "Attack" -> Attack(suit = suit, rank = rank)
            "Peach" -> Peach(suit = suit, rank = rank)
            "Kirin Bow" -> KirinBow(suit = suit, rank = rank)
            "Red Hare" -> RedHare(suit = suit, rank = rank)
            "Rock Cleaving Axe" -> RockCleavingAxe(suit = suit, rank = rank)
            "Sky Piercing Halberd" -> SkyPiercingHalberd(suit = suit, rank = rank)
            "Violet Stallion" -> VioletStallion(suit = suit, rank = rank)
            "Duel" -> Duel(suit = suit, rank = rank)
            "YinYangSwords" -> YinYangSwords(suit = suit, rank = rank)
            "Stealing Sheep" -> StealingSheep(suit = suit, rank = rank)
            "Shadowrunner" -> Shadowrunner(suit = suit, rank = rank)
            "Raining Arrows" -> RainingArrows(suit = suit, rank = rank)
            "Bumper Harvest" -> BumperHarvest(suit = suit, rank = rank)
            "Something out of nothing" -> SomethingOutOfNothing(suit = suit, rank = rank)
            "Flying Lightning" -> FlyingLightning(suit = suit, rank = rank)
            "Blue Steel Blade" -> BlueSteelBlade(suit = suit, rank = rank)
            "Hex Mark" -> HexMark(suit = suit, rank = rank)
            else -> throw IllegalArgumentException("Unknown card type: $type")
        }
    }
}