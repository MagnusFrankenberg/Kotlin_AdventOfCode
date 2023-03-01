package Day4

import java.io.File

fun fileasList2(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list2 = fileasList("src/main/kotlin/AOC_2022/Day4/Data4.txt")


//Har här gått tillbaka och komprimerat koden med .map, it, [] ist för .get(0),
//split med multiple delimiters, skrotat if-satserna-> istället count{it==true}

fun A_isFullyContained2(list:List<String>):Int{

    return list.map{it.split("-",",","-")}
        .map{it.map{it.toInt()}}
        .map{(it[0]>=it[2]&&it[1]<=it[3])||(it[0]<=it[2]&&it[1]>=it[3])}
        .count{it==true}
}

fun B_isFullyOrPartlyContained2(list:List<String>):Int{

   return list.map{it.split("-",",","-")}
        .map{it.map{it.toInt()}}
        .map{it[1]>=it[2]&&it[0]<=it[3]}
        .count{it==true}
}

fun main(){
    println(A_isFullyContained2(list2))
    println(B_isFullyOrPartlyContained2(list2))
}