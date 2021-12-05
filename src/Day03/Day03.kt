fun main() {
    val dayId = "Day03"
    val zero = '0'
    val one = '1'
    var len : Int

    fun part1(input: List<String>): Int {
        len = input[0].length
        val zeroes = IntArray(len)
        val ones = IntArray(len)

        for (number in input) {
            for (i in number.indices) {
                when (number[i]) {
                    zero -> zeroes[i]++
                    one -> ones[i]++
                }
            }
        }

        val gamma = CharArray(len) { '0' }
        for (i in 0 until len) {
            if (ones[i] > zeroes[i]) {
                gamma[i] = '1'
            }
        }

        val gammaInt = gamma.concatToString().toInt(2)
        val epsilonInt = Integer.toBinaryString(gammaInt.inv()).takeLast(len).toInt(2)

        return gammaInt * epsilonInt
    }

    fun getParameter(input: List<String>, isOxygen: Boolean) : Int {
        var selectedValues = input
        var index = 0
        len = input[0].length

        while (index < len && selectedValues.size > 1) {
            var zeroes = 0
            var ones = 0
            val filteredZeroes = emptyList<String>().toMutableList()
            val filteredOnes = emptyList<String>().toMutableList()

            for (number in selectedValues) {
                when (number[index]) {
                    zero -> {
                        zeroes++
                        filteredZeroes += number
                    }
                    one -> {
                        ones++
                        filteredOnes += number
                    }
                }
            }

            selectedValues = if (ones >= zeroes) {
                if (isOxygen) filteredOnes else filteredZeroes
            } else {
                if (isOxygen) filteredZeroes else filteredOnes
            }

            index++
        }

        return selectedValues[0].toInt(2)
    }

    fun part2(input: List<String>): Int {
        len = input[0].length

        return getParameter(input, true) * getParameter(input, false)
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${dayId}/${dayId}_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("${dayId}/${dayId}")
    println(part1(input))
    println(part2(input))
}
