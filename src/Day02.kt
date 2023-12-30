data class Game(val loader: String) {
    val id:Int
    val plays:List<Play>
    init {
        val (idPart,gamePart) = loader.split(":")
        val (_,id) = idPart.split(" ")
        this.id = id.toInt()

        this.plays = gamePart.split(";").map {
            Play(it.split(",").associate {
                val (number, color) = it.trim().split(" ")
                color to number.toInt()} as MutableMap<String, Int>)
        }
    }

    fun isValid(validPlay:Play):Boolean{
        return this.plays.all { it.matches.none {
            validPlay.matches.get(it.key)!! < it.value } }
    }

    fun minValid():Play {
        return this.plays.reduce(::getBiggerPlay)
    }


    private fun getBiggerPlay(acc: Play, play: Play): Play {
        play.matches.entries.forEach {
            if(!acc.matches.contains(it.key) || acc.matches.getValue(it.key) < it.value)
                acc.matches[it.key] = it.value
        }
        return acc
    }
}
class Play (var matches:MutableMap<String,Int>)

val maxPlayPart01 = Play(mutableMapOf("red" to 12, "green" to 13, "blue" to 14))
fun main() {
    fun part1(input: List<String>) : Int {
        return input
            .map { Game(it) }
            .filter { it.isValid(maxPlayPart01) }
            .sumOf { it.id }
    }

    fun part2(input: List<String>) : Int {
        return input
            .map { Game(it) }
            .sumOf { it.minValid().matches.values.reduce { acc, i -> acc * i } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}