package Day7

//*** EJ MIN LÖSNING ***
//  solution by ephemient, from IntelliJ-video AOC

    class Day7(lines: List<String>) {
        private val sizes = buildMap {
            put("", 0)
            var cwd = ""
            for (line in lines) {
                val match = PATTERN.matchEntire(line) ?: continue
                match.groups[1]?.value?.let { dir ->
                    cwd = when (dir) {
                        "/" -> ""
                        ".." -> cwd.substringBeforeLast('/', "")
                        else -> if (cwd.isEmpty()) dir else "$cwd/$dir"
                    }
                } ?: match.groups[2]?.value?.toIntOrNull()?.let { size ->
                    var dir = cwd
                    while (true) {
                        put(dir, getOrElse(dir) { 0 } + size)
                        if (dir.isEmpty()) break
                        dir = dir.substringBeforeLast('/', "")
                    }
                }
            }
        }


        fun part1(): Int = sizes.values.sumOf { if (it <= 100000) it else 0 }


        fun part2(): Int {
            val total = sizes.getValue("")
            return sizes.values.asSequence().filter { 70000000 - (total - it) >= 30000000 }.min()
        }
    }

    private val PATTERN = """[$] cd (.*)|(\d+).*""".toRegex()

fun main(){
    println(Day7(list).part1())
    println(Day7(list).part2())

}