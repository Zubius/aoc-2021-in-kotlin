fun main() {
    val forward = "forward"
    val up = "up"
    val down = "down"

    fun part1(input: List<String>): Int {
        var movement = 0
        var depth = 0
        var value : Int

        for (direction in input) {
            val data = direction.split(" ")
            value = data[1].toInt()
            when(data[0]) {
                forward -> movement += value
                up -> depth -= value
                down -> depth += value
            }
        }

        return movement * depth
    }

    fun part2(input: List<String>): Int {
        var movement = 0
        var depth = 0
        var aim = 0
        var value: Int

        for (direction in input) {
            val data = direction.split(" ")
            value = data[1].toInt()
            when(data[0]) {
                forward -> {
                    movement += value
                    depth += aim * value
                }
                up -> aim -= value
                down -> aim += value
            }
        }

        return movement * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02/Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02/Day02")
    println(part1(input))
    println(part2(input))
}
