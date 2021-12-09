import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    val dayId = "Day05"
    val delimiter1 = " -> "
    val delimiter2 = ","
    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
    var lines : List<Line>
    var columnSize : Int
    var rowSize : Int

    fun part1(input: List<String>): Int {
        lines = input.map { line ->
            val l = line.trim().split(delimiter1, delimiter2).map { it.toInt() }
            Line(l[1], l[0], l[3], l[2])
        }
        columnSize = lines.maxOf { line -> maxOf(line.x1, line.x2) } + 1
        rowSize = lines.maxOf { line -> maxOf(line.y1, line.y2) } + 1
        val map = Array(columnSize) { IntArray(rowSize) }

        for (line in lines) {
            if (line.x1 == line.x2) {
                for (y in minOf(line.y1, line.y2)..maxOf(line.y1, line.y2))
                    map[line.x1][y]++
            }
            if (line.y1 == line.y2) {
                for (x in minOf(line.x1, line.x2)..maxOf(line.x1, line.x2))
                    map[x][line.y1]++
            }
        }

        return map.toList().flatMap { it.toList() }.count { it > 1 }
    }

    fun part2(input: List<String>): Int {
        lines = input.map { line ->
            val l = line.trim().split(delimiter1, delimiter2).map { it.toInt() }
            Line(l[1], l[0], l[3], l[2])
        }
        columnSize = lines.maxOf { line -> maxOf(line.x1, line.x2) } + 1
        rowSize = lines.maxOf { line -> maxOf(line.y1, line.y2) } + 1
        val map = Array(columnSize) { IntArray(rowSize) }

        for (line in lines) {
            val signX = (line.x2 - line.x1).sign
            val signY = (line.y2 - line.y1).sign

            for (point in 0..maxOf((line.x1 - line.x2).absoluteValue, (line.y1 - line.y2).absoluteValue))
                map[line.x1 + point * signX][line.y1 + point * signY]++
        }

        return map.toList().flatMap { it.toList() }.count { it > 1 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
