package Day1

import java.io.File


fun main() {

    val list: List<String> = fileasList("src/main/kotlin/AOC_2022/Day1/Data1.txt")

    //uppgift a
    println(getMaxElfFood(list))

    //uppgift b
    println(getEachElfFood(list).subList(0,3).sum())

}

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }

fun getMaxElfFood(list: List<String>):Int {
    var maxnumber: Int = 0
    var temp: Int = 0
    var fromix: Int = 0
    var toix: Int = 0

    for ((i, s) in list.withIndex()) {
        if (s.equals("")) {
            toix = i
            temp = list.subList(fromix, toix).map { s -> s.toInt() }.sum()
            if (temp > maxnumber) maxnumber = temp
            fromix = toix + 1
        }
    }
    return maxnumber
}

fun getEachElfFood(list: List<String>):List<Int> {
    var fromix: Int = 0
    var toix: Int = 0
    val list2 = mutableListOf<Int>()

    for ((i, s) in list.withIndex()) {
        if (s.equals("")) {
            toix = i
            list2.add(list.subList(fromix, toix).map { s -> s.toInt() }.sum())
            fromix = toix + 1

        }
    }
    list2.sortDescending()
    return list2
}