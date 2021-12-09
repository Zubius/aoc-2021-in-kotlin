import kotlin.math.abs

fun main() {
    val dayId = "Day07"

    fun part1(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }
        var fuelSpent = Int.MAX_VALUE
        for (position in 0..crabPositions.maxOrNull()!!) {
            val currentFuel = crabPositions.sumOf { current -> abs(position - current) }
            if (currentFuel < fuelSpent) fuelSpent = currentFuel
        }

        return fuelSpent
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }
        var fuelSpent = Int.MAX_VALUE
        for (position in 0..crabPositions.maxOrNull()!!) {

            val lambda = { value: Int ->
                val distance = abs(position - value)
                var fuel = 0
                for (step in 0..distance) {
                    fuel += step
                }
                fuel
            }
            val currentFuel = crabPositions.sumOf(lambda)
            if (currentFuel < fuelSpent) fuelSpent = currentFuel
        }

        return fuelSpent
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
