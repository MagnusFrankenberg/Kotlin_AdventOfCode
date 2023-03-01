package Day6

import java.io.File

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day6/Data6.txt")

var queue = mutableListOf<Char>()

    fun main(){

       println("Del A: "+ countCharsToMarker(list,4))
        println("Del B: "+countCharsToMarker(list,14))
    }

fun countCharsToMarker(list:List<String>, seqSize:Int):Int{
    var counter: Int = 0
    for(c in list.get(0)){
        counter++
        if(hasUniqueChars(queue(c,seqSize),seqSize))
            break
            }
    return counter
    }

fun queue(c:Char, seqSize:Int):List<Char>{
    if(queue.size<seqSize) {
        queue.add(c)
    }else {
        queue.removeAt(0)
        queue.add(c)
    }
    return queue
}

fun hasUniqueChars(list:List<Char>, seqSize:Int):Boolean{
    return (list.size==seqSize) && (list.map { it }.toSet().size == list.size )
}






