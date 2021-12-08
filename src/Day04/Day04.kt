fun main() {
    val dayId = "Day04"

    class Board(boardString: List<String>) {
        val data = boardString.map { b -> b.trim().split(" ").filter { it != "" }.map { it.toInt() } }.flatten()
        val rowsCount = boardString.size
        val columnsCount = if (rowsCount > 0) data.size / rowsCount else 0
        val elementsCount = rowsCount * columnsCount
        val marked = BooleanArray(elementsCount).toMutableList()
        val markedRows = IntArray(5)
        val markedColumns = IntArray(5)

        fun mark(number: Int) : Boolean {
            val index = data.indexOf(number)
            if (index > -1) {
                marked[index] = true
                markedRows[index / columnsCount]++
                markedColumns[index % rowsCount]++
            }

            return markedRows.any { it == 5 } || markedColumns.any { it == 5 }
        }

        fun getScore() : Int {
            var score = 0
            for (index in data.indices) {
                if (!marked[index]) score += data[index]
            }

            return score
        }
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = mutableListOf<Board>()

        for (r in 2 until input.size step 6) {
            boards += Board(input.subList(r, r + 5))
        }

        var result = 0
        loop@ for (number in numbers) {
            for (board in boards) {
                if (board.mark(number)) {
                    result = board.getScore() * number
                    break@loop
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = mutableListOf<Board>()

        for (r in 2 until input.size step 6) {
            boards += Board(input.subList(r, r + 5))
        }
        val markedBoards = BooleanArray(boards.size).toMutableList()

        var result = 0
        loop@ for (number in numbers) {
            for (index in boards.indices) {
                if (!markedBoards[index] && boards[index].mark(number)) {
                    if (markedBoards.count { !it } > 1) {
                        markedBoards[index] = true
                    }
                    else if (boards[index].mark(number)) {
                        result = boards[index].getScore() * number
                        break@loop
                    }
                }
            }
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
