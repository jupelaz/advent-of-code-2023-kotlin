fun main() {
    fun part1(input: List<String>) = input
            .map { line -> line.filter { it.isDigit() } }
            .sumOf { ("${it.first()}${it.last()}").toInt() }

    fun part2(input: List<String>) : Int {
        println(input)
        val checkpoint = input.map { ("${it.firstAlphanumeric()}${it.lastAlphanumeric()}").toInt() }
        println(checkpoint)
        return checkpoint.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun String.lastAlphanumeric(): String {
    for(i in this.lastIndex downTo 0) {
        if (this[i].isDigit()) return this[i].toString()
        else if (this.subSequence(i,this.lastIndex + 1).contains("one")) return "1"
        else if (this.subSequence(i,this.lastIndex + 1).contains("two")) return "2"
        else if (this.subSequence(i,this.lastIndex + 1).contains("three")) return "3"
        else if (this.subSequence(i,this.lastIndex + 1).contains("four")) return "4"
        else if (this.subSequence(i,this.lastIndex + 1).contains("five")) return "5"
        else if (this.subSequence(i,this.lastIndex + 1).contains("six")) return "6"
        else if (this.subSequence(i,this.lastIndex + 1).contains("seven")) return "7"
        else if (this.subSequence(i,this.lastIndex + 1).contains("eight")) return "8"
        else if (this.subSequence(i,this.lastIndex + 1).contains("nine")) return "9"
    }
    return ""
}

private fun String.firstAlphanumeric(): String {
    for(i in 0..this.lastIndex) {
        if (this[i].isDigit()) return this[i].toString()
        else if (this.subSequence(0, i + 1).contains("one")) return "1"
        else if (this.subSequence(0, i + 1).contains("two")) return "2"
        else if (this.subSequence(0, i + 1).contains("three")) return "3"
        else if (this.subSequence(0, i + 1).contains("four")) return "4"
        else if (this.subSequence(0, i + 1).contains("five")) return "5"
        else if (this.subSequence(0, i + 1).contains("six")) return "6"
        else if (this.subSequence(0, i + 1).contains("seven")) return "7"
        else if (this.subSequence(0, i + 1).contains("eight")) return "8"
        else if (this.subSequence(0, i + 1).contains("nine")) return "9"
    }
    return ""
}
