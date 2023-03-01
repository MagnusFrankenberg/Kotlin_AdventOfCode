package Day2

import java.io.File

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day2/Data2.txt")


fun main(){

println(getTotalScoreA(list))
    println(getTotalScoreB(list))

}

fun getTotalScoreA(list: List<String>):Int{
    var totScore:Int =0
    list.forEach{s-> totScore += calculateRound(s)}
    return totScore
}
fun getTotalScoreB(list: List<String>):Int{
    var totScore:Int =0
    list.forEach{s-> totScore += calculateRound(changeStrategy(s))}
    return totScore
}

fun calculateRound(line:String):Int{
    val score = mapOf<String,Int>("A Y" to 8, "A X" to 4,"A Z" to 3,
                                 "B Y" to 5,"B X" to 1,"B Z" to 9,
                                 "C Y" to 2,"C X" to 7,"C Z" to 6)
    val nonNullScore:Int = score[line] ?:0
    return nonNullScore
}

fun changeStrategy(line:String):String{
    val strategy = mapOf<String,String>("A Y" to "A X", "A X" to "A Z","A Z" to "A Y",
        "B Y" to "B Y","B X" to "B X","B Z" to "B Z",
        "C Y" to "C Z","C X" to "C Y","C Z" to "C X")
    val notNullStrategy = strategy[line] ?:"Undefined"
    return notNullStrategy
}



