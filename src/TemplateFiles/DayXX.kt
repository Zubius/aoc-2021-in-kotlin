fun main() {
    val dayId = "DayXX"

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 1)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
