fun main() {
    val dayId = "Day06"
    var days : Int
    val reproduceTimer = 6
    val newTimer = 8

    fun part1(input: List<String>): Int {
        days= 80
        var initialSchool = input[0].split(",").map { it.toInt() }
        for (a in 0 until days) {
            val newSchool = ArrayList<Int>()

            for (fishTimer in initialSchool) {
                if (fishTimer > 0 ) {
                    newSchool.add(fishTimer - 1)
                }
                else if (fishTimer == 0) {
                    newSchool.add(reproduceTimer)
                    newSchool.add(newTimer)
                }

            }

            initialSchool = newSchool
        }

        return initialSchool.size
    }

    fun part2(input: List<String>): Long {
        days = 256
        val initialSchool = input[0].split(",").map { it.toInt() }
        var schoolTimers = LongArray(9)
        for (fish in initialSchool) {
            schoolTimers[fish]++
        }

        for (a in 0 until days) {
            val newTimers = LongArray(9)
            for (day in newTimers.indices) {
                if (day > 0 ) {
                    newTimers[day - 1] += schoolTimers[day]
                }
                else if (day == 0) {
                    newTimers[reproduceTimer] += schoolTimers[day]
                    newTimers[newTimer] += schoolTimers[day]
                }
            }

            schoolTimers = newTimers
        }

        return schoolTimers.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 5934)
    check(part1(testInput) == 5934)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
