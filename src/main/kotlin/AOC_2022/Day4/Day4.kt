package Day4

import java.io.File

fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day4/Data4.txt")

fun main(){

    println(splitLineToInts(list))
    println( A_isFullyContained(splitLineToInts(list)).filter { b->b==true }.count())
    println( B_isFullyOrPartlyContained(splitLineToInts(list)).filter { b->b==true }.count())

}

fun splitLineToInts(list:List<String>):List<List<List<Int>>>{
    val list2 = list.map { s->s.split(",") }
    val list3 = list2.map{l->l.map { s->s.split("-").map { s->s.toInt() } }}
    return list3
}

fun A_isFullyContained(list: List<List<List<Int>>>):List<Boolean>{
    val listB = mutableListOf<Boolean>()
    for(l in list){
        if(l.get(0).get(0)>=l.get(1).get(0)&&l.get(0).get(1)<=l.get(1).get(1)){
            listB.add(true)
        }else if(l.get(0).get(0)<=l.get(1).get(0)&&l.get(0).get(1)>=l.get(1).get(1))
            listB.add(true)
            else {
                listB.add(false)
            }
    }
    return listB
}

fun B_isFullyOrPartlyContained(list: List<List<List<Int>>>):List<Boolean>{
    val listB = mutableListOf<Boolean>()
    for(l in list){
        if(l.get(0).get(0)>=l.get(1).get(0)&&l.get(0).get(1)<=l.get(1).get(1))
            listB.add(true)
        else if(l.get(0).get(0)<=l.get(1).get(0)&&l.get(0).get(1)>=l.get(1).get(1))
            listB.add(true)
        else if(l.get(0).get(0)>=l.get(1).get(0)&&l.get(0).get(0)<=l.get(1).get(1))
            listB.add(true)
        else if(l.get(0).get(1)>=l.get(1).get(0)&&l.get(0).get(1)<=l.get(1).get(1))
            listB.add(true)
        else
            listB.add(false)
    }
    return listB
}

