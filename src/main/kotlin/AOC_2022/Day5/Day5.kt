package Day5

import java.io.File

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day5/moveData.txt")

var l1 = mutableListOf<Char>('B','S','V','Z','G','P','W')
var l2 = mutableListOf<Char>('J','V','B','C','Z','F')
var l3 = mutableListOf<Char>('V','L','M','H','N','Z','D','C')
var l4 = mutableListOf<Char>('L','D','M','Z','P','F','J','B')
var l5 = mutableListOf<Char>('V','F','C','G','J','B','Q','H')
var l6 = mutableListOf<Char>('G','F','Q','T','S','L','B')
var l7 = mutableListOf<Char>('L','G','C','Z','V')
var l8 = mutableListOf<Char>('N','L','G')
var l9 = mutableListOf<Char>('J','F','H','C')
var myMap = mutableMapOf<Int,MutableList<Char>>(1 to l1,2 to l2,3 to l3,4 to l4,5 to l5,6 to l6,7 to l7,8 to l8,9 to l9)


fun main(){

    //Versions 1 with several separate functions
     // A_makeAllMoves(list)
     // AB_returnResult()

    B_makeAllMoves(list)
    AB_returnResult()

}

fun readString(line:String):List<Int>{
    return line.split(" ")
        .map{s->s.trim()}
        .filterNot { s->s.equals("move")||s.equals("from")||s.equals("to") }
        .map{s->s.toInt()}
}

fun A_addTo(moveData:List<Int>,cratesToMove:List<Char>){
  (moveData[0]-1 downTo 0).forEach {i->myMap[moveData[2]]!!.add(cratesToMove.get(i))}
}

fun A_makeAllMoves(list: List<String>){
    list.map{ s-> readString(s) }
        .forEach{l->A_addTo(l, AB_takeFrom(l))}
}

fun AB_takeFrom(moveData:List<Int>):List<Char>{
    val cratesToMove = myMap[moveData[1]]!!.takeLast(moveData[0])
    myMap.replace(moveData[1],myMap[moveData[1]]!!.dropLast(moveData[0]).toMutableList())
return cratesToMove
}

fun B_addTo(moveData:List<Int>,cratesToMove:List<Char>){
    myMap[moveData[2]]!!.addAll(cratesToMove)
}

fun B_makeAllMoves(list: List<String>){
    list.map{ s-> readString(s) }
    .forEach{l->B_addTo(l, AB_takeFrom(l))}
}

fun AB_returnResult(){
    println( myMap.map{ m->m.value.takeLast(1)}.flatten().toList())
}




