import kotlin.random.Random

class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        initializeDeck()
    }

    private fun initializeDeck() {
        cards.add(CardFactory.createCard("Lightning", Suit.SPADE, "A"))
        cards.add(CardFactory.createCard("Duel", Suit.SPADE, "A"))
        cards.add(CardFactory.createCard("Eight Trigrams Formation", Suit.SPADE, "2"))
        cards.add(CardFactory.createCard("YinYangSwords", Suit.SPADE, "2"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.SPADE, "3"))
        cards.add(CardFactory.createCard("Stealing Sheep", Suit.SPADE, "3"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.SPADE, "4"))
        cards.add(CardFactory.createCard("Stealing Sheep", Suit.SPADE, "4"))
        cards.add(CardFactory.createCard("Green Dragon Blade", Suit.SPADE, "5"))
        cards.add(CardFactory.createCard("Shadowrunner", Suit.SPADE, "5"))
        cards.add(CardFactory.createCard("Acedia", Suit.SPADE, "6"))
        cards.add(CardFactory.createCard("Blue Steel Blade", Suit.SPADE, "6"))
        cards.add(CardFactory.createCard("Barbarian Invasion", Suit.SPADE, "7"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "7"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "8"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "8"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.SPADE, "10"))
        cards.add(CardFactory.createCard("Negation", Suit.SPADE, "J"))
        cards.add(CardFactory.createCard("Stealing Sheep", Suit.SPADE, "J"))
        cards.add(CardFactory.createCard("Serpent Spear", Suit.SPADE, "Q"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.SPADE, "Q"))
        cards.add(CardFactory.createCard("Ferghana Horse", Suit.SPADE, "K"))
        cards.add(CardFactory.createCard("Barbarian Invasion", Suit.SPADE, "K"))
        cards.add(CardFactory.createCard("Brotherhood", Suit.HEART, "A"))
        cards.add(CardFactory.createCard("Raining Arrows", Suit.HEART, "A"))
        cards.add(CardFactory.createCard("Dodge", Suit.HEART, "2"))
        cards.add(CardFactory.createCard("Dodge", Suit.HEART, "2"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "3"))
        cards.add(CardFactory.createCard("Bumper Harvest", Suit.HEART, "3"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "4"))
        cards.add(CardFactory.createCard("Bumper Harvest", Suit.HEART, "4"))
        cards.add(CardFactory.createCard("Kirin Bow", Suit.HEART, "5"))
        cards.add(CardFactory.createCard("Red Hare", Suit.HEART, "5"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "6"))
        cards.add(CardFactory.createCard("Acedia", Suit.HEART, "6"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "7"))
        cards.add(CardFactory.createCard("Something out of nothing", Suit.HEART, "7"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "8"))
        cards.add(CardFactory.createCard("Something out of nothing", Suit.HEART, "8"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "9"))
        cards.add(CardFactory.createCard("Something out of nothing", Suit.HEART, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.HEART, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.HEART, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.HEART, "J"))
        cards.add(CardFactory.createCard("Something out of nothing", Suit.HEART, "J"))
        cards.add(CardFactory.createCard("Peach", Suit.HEART, "Q"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.HEART, "Q"))
        cards.add(CardFactory.createCard("Flying Lightning", Suit.HEART, "K"))
        cards.add(CardFactory.createCard("Dodge", Suit.HEART, "K"))
        cards.add(CardFactory.createCard("Zhuge Crossbow", Suit.CLUB, "A"))
        cards.add(CardFactory.createCard("Duel", Suit.CLUB, "A"))
        cards.add(CardFactory.createCard("Eight Trigrams Formation", Suit.CLUB, "2"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "2"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.CLUB, "3"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "3"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.CLUB, "4"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "4"))
        cards.add(CardFactory.createCard("Hex Mark", Suit.CLUB, "5"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "5"))
        cards.add(CardFactory.createCard("Acedia", Suit.CLUB, "6"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "6"))
        cards.add(CardFactory.createCard("Barbarian Invasion", Suit.CLUB, "7"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "7"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "8"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "8"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "J"))
        cards.add(CardFactory.createCard("Attack", Suit.CLUB, "J"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.CLUB, "Q"))
        cards.add(CardFactory.createCard("Negation", Suit.CLUB, "Q"))
        cards.add(CardFactory.createCard("Burning Bridges", Suit.CLUB, "K"))
        cards.add(CardFactory.createCard("Negation", Suit.CLUB, "K"))
        cards.add(CardFactory.createCard("Zhuge Crossbow", Suit.DIAMOND, "A"))
        cards.add(CardFactory.createCard("Duel", Suit.DIAMOND, "A"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "2"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "2"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "3"))
        cards.add(CardFactory.createCard("Stealing Sheep", Suit.DIAMOND, "3"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "4"))
        cards.add(CardFactory.createCard("Stealing Sheep", Suit.DIAMOND, "4"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "5"))
        cards.add(CardFactory.createCard("Rock Cleaving Axe", Suit.DIAMOND, "5"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "6"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "6"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "7"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "7"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "8"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "8"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "9"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "9"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "10"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "10"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "J"))
        cards.add(CardFactory.createCard("Dodge", Suit.DIAMOND, "J"))
        cards.add(CardFactory.createCard("Peach", Suit.DIAMOND, "Q"))
        cards.add(CardFactory.createCard("Sky Piercing Halberd", Suit.DIAMOND, "Q"))
        cards.add(CardFactory.createCard("Attack", Suit.DIAMOND, "K"))
        cards.add(CardFactory.createCard("Violet Stallion", Suit.DIAMOND, "K"))
    }

    fun shuffle() {
        cards.shuffle()
        println("Deck has been shuffled.")
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("No cards left in the deck!")
        }
        return cards.removeAt(0)
    }

    fun drawRandomCard(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("No cards left in the deck!")
        }
        val randomIndex = Random.nextInt(cards.size)
        return cards.removeAt(randomIndex)
    }

    fun drawCards(num: Int): List<Card> {
        if (num > cards.size) {
            throw IllegalStateException("Not enough cards in the deck!")
        }
        return List(num) { drawCard() }
    }

    fun remainingCards(): Int {
        return cards.size
    }
}