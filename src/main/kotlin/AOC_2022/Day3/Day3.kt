package Day3

import java.io.File

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day3/Data3.txt")

fun main(){
    println( A_sumOfPoints(list))
    println(B_calcPoints(B_getGroupChars(list)))

}

fun getPrioPoint(c:Char):Int{
    val aAlist = ('a'..'z').toList().plus(('A'..'Z').toList())
    val myMap = aAlist.map{c-> c to (aAlist.indexOf(c)+1)}.toMap()

    val result = myMap.get(c) ?:-1
    return result
}

fun A_findDoubleCharachter(line:String):Char{
   val comp1 = line.substring(0..line.length/2 - 1)
   val comp2 = line.substring(line.length/2)
    var myChar:Char='0'

    for(c in comp1){
        if(comp2.contains(c)) myChar = c
    }
return myChar
}

fun A_sumOfPoints(list: List<String>):Int{
    return list.map{s-> getPrioPoint(A_findDoubleCharachter(s)) }.sum()
}



fun B_getGroupChars(list: List<String>):List<Char>{
    val chunkedList = list.chunked(3)
    var mychars  = mutableListOf<Char>()
    for(l in chunkedList){
        for(char in l.get(0).toSet()){
            if(l.get(1).contains(char)&&l.get(2).contains(char)){
                mychars.add(char)
            }
        }
    }
    return mychars
}

fun B_calcPoints(list:List<Char>):Int{
    return list.map{c-> getPrioPoint(c) }.sum()
}
