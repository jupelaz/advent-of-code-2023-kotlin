
fun main() {

    fun part1(input: List<String>) : Int {

       val set = mutableSetOf<Pair<Int, Int>>()
       input.forEachIndexed { listIndex, line ->
           line.forEachIndexed { stringIndex, c ->
                   if (!c.isDigit() && c != '.')
                       for (dr in listIndex - 1..listIndex + 1)
                           for (dc in stringIndex - 1..stringIndex + 1)
                               if (dr >= 0 && dr <= input.lastIndex && dc >= 0 && dc <= input.first().lastIndex && input[dr][dc].isDigit()){
                                   var rc = dc
                                   while (rc > 0 && input[dr][rc - 1].isDigit())
                                       rc -= 1
                                   set.add(dr to rc)
                               }
               }
       }

       val ns = mutableListOf<Int>()
       for  (a in set) {
           val r = a.first
           var c = a.second
           var s = ""
           while ((c < input[r].length) && input[r][c].isDigit()) {
               s += input[r][c]
               c++
           }
           ns.add(s.toInt())
       }
       return ns.sum()
    }



  fun part2(input: List<String>) : Int {

    val set = mutableSetOf<Pair<Int, Int>>()
    val ns = mutableListOf<Int>()
    input.forEachIndexed { listIndex, line ->
      line.forEachIndexed { stringIndex, c ->
        if (c == '*') {
          set.clear()
          for (dr in listIndex - 1..listIndex + 1)
            for (dc in stringIndex - 1..stringIndex + 1)
              if (dr >= 0 && dr <= input.lastIndex && dc >= 0 && dc <= input.first().lastIndex && input[dr][dc].isDigit()) {
                var rc = dc
                while (rc > 0 && input[dr][rc - 1].isDigit())
                  rc -= 1
                set.add(dr to rc)
              }
          if (set.size == 2) {
            var mult = 1
            for (a in set) {
              val r = a.first
              var cl = a.second
              var s = ""
              while ((cl < input[r].length) && input[r][cl].isDigit()) {
                s += input[r][cl]
                cl++
              }
              mult *= s.toInt()
            }
            ns.add(mult)
          }
        }
      }
    }

    return ns.sum()
  }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}