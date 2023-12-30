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
                color to number.toInt()})
        }
    }

    fun isValid(validPlay:Play):Boolean{
        return this.plays.all { it.matches.none {
            validPlay.matches.get(it.key)!! < it.value } }
    }
}
class Play (var matches:Map<String,Int>)

val maxPlayPart01 = Play(mapOf("red" to 12, "green" to 13, "blue" to 14))
fun main() {
    fun part1(input: List<String>) : Int {
        val part1 = input.map { Game(it) }
        val part2 = part1.filter { it.isValid(maxPlayPart01) }
        return part2.sumOf { it.id }
    }

    fun part2(input: List<String>) : Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}