package Day6

import java.io.File

//solution from Kotlin - Advent of code video

fun fileasList2(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list2 = fileasList2("src/main/kotlin/AOC_2022/Day6/Data6.txt")


fun CharSequence.allUnique():Boolean = toSet().count() == length

fun solve(input:String,windowSize:Int):Int = input.windowedSequence(windowSize){
    it.allUnique()
}.indexOf(true) + windowSize


fun solveNaively(input:String,windowSize:Int):Int{
    val windowed = input.windowed(windowSize)
    for((index,window) in windowed.withIndex()){
        if(window.toSet().count() == windowSize){
            return index + windowSize
        }
    }
    return -1
}



fun main(){

    println(solveNaively(list2.get(0),4))
    println(solve(list2.get(0),4))

}