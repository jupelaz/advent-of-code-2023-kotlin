
fun main() {
    fun foundSymbolInCoord(list: List<String>, left: Int, right: Int, top: Int, bottom: Int): Boolean {
        return list.subList(top, bottom + 1)
            .joinToString(separator = "") { it.subSequence(left, right) }
            .filter { !it.isDigit() || it != '.' }
            .any { !it.isLetterOrDigit() }
    }

    fun foundSymbol(list: List<String>, listIndex: Int, start: Int, endOfDigit: Int): Boolean {
        val leftIndex = if (start == 0) 0 else start - 1
        val rightIndex = if (endOfDigit == list[listIndex].length) list[listIndex].length else endOfDigit + 1
        val topIndex = if (listIndex == 0) 0 else listIndex - 1
        val bottomIndex = if (listIndex == list.lastIndex) list.lastIndex else listIndex + 1

        return foundSymbolInCoord(list,leftIndex,rightIndex,topIndex,bottomIndex)
    }

    fun getEndOfNumber(line: String, stringIndex: Int): Int {
        var endIndex = stringIndex
        while (line[endIndex].isDigit() && endIndex < line.lastIndex) {
            endIndex += 1
        }
        return endIndex
    }

    fun part1(input: List<String>) : Int {
       var sum = 0
       input.forEachIndexed { listIndex, line ->
           line.forEachIndexed { stringIndex, c ->
               if (c.isDigit()) {
                   val endIndex = getEndOfNumber(line, stringIndex)
                   if (foundSymbol(input,listIndex,stringIndex,endIndex)) {
                       sum += line.subSequence(stringIndex, endIndex).toString().toInt()
                   }
               }
           }
       }
        println(sum)
       return sum
    }



    fun part2(input: List<String>) : Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}