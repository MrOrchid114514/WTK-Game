fun main(args: Array<String>) {
    GeneralManager.createGenerals(
        lords = 1,
        loyalists = 1,
        spies = 1,
        rebels = 1
    )
    println("Total number of players: ${GeneralManager.list.size}")

    GeneralManager.list.lastOrNull()?.let {
        println("${it.name} being placed the Acedia card.")
    }

    repeat(2) {
        println("\nRound ${it + 1}:")
        GeneralManager.list.forEach { it.playTurn() }
    }
}