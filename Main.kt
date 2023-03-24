package machine

fun <S> S.reply() = println(this).run { readln() }

enum class Coffee(val water: Int, val milk: Int, val coffee: Int, val cups: Int, val cost: Int) {
    Espresso(250, 0, 16, 1, 4),
    Latte(350, 75, 20, 1, 7),
    Cappuccino(200, 100, 12, 1, 6)
}

class CoffeeMachine(private var water: Int, private var milk: Int, private var coffee: Int, private var cups: Int, private var money: Int) {

    private fun buy(cofe: Coffee) {
        if (water < cofe.water) println("Sorry, not enough water!")
        if (milk < cofe.milk) println("Sorry, not enough milk!")
        if (coffee < cofe.coffee) println("Sorry, not enough coffee!")
        if (cups < cofe.cups) println("Sorry, not enough water!")
        if (water > cofe.water && milk > cofe.milk && coffee > cofe.coffee && cups > cofe.cups) {
            println("I have enough resources, making you a coffee!\n")
            water -= cofe.water
            milk -= cofe.milk
            coffee -= cofe.coffee
            cups -= cofe.cups
            money += cofe.cost
        }
    }

    fun buy() {
        when ("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:".reply()) {
            "1" -> buy(Coffee.Espresso)
            "2" -> buy(Coffee.Latte)
            "3" -> buy(Coffee.Cappuccino)
            "back" -> return
            else -> throw IllegalArgumentException("Invalid input")
        }
    }

    fun fill() {
        println("\nWrite how many ml of water you want to add:")
        val wat = readln().toInt()
        println("Write how many ml of milk you want to add:")
        val mil = readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        val cof = readln().toInt()
        println("WWrite how many disposable cups you want to add:\n")
        val cup = readln().toInt()
        water += wat
        milk += mil
        coffee += cof
        cups += cup
    }

    fun take() {
        println("\nI gave you \$${money}\n")
        money = 0
    }

    fun remaining() {
        println("\n" + """
        The coffee machine has:
        $water ml of water
        $milk ml of milk
        $coffee g of coffee beans
        $cups disposable cups
        $money of money""".trimIndent() + "\n"
        )
    }

}

fun main() {

    val coffeeMachine = CoffeeMachine(400, 540, 120, 9,550)

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (readln()) {
            "buy" -> coffeeMachine.buy()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "remaining" -> coffeeMachine.remaining()
            "exit" -> return
            else -> throw IllegalArgumentException("Invalid input")
        }
    }
}
