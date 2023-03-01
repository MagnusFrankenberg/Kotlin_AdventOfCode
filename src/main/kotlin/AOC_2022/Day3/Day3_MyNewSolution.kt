package AOC_2022.Day3


import java.io.File

fun fileasList2(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list2 = fileasList2("src/main/kotlin/AOC_2022/Day3/Data3.txt")

//har komprimerat koden ytterligare med chunked, .map, .map->boolean, indexOf(true)
//poängen beräknas med indexplatsen för bokstaven +1

fun main(){
    println(A_SumPoints(list2))
    println(B_SumPoints(list2))
}
val prioPoints = ('a'..'z').toList().plus(('A'..'Z').toList())

fun A_SumPoints(list:List<String>):Int {

   return list.map { it.chunked(it.length / 2) }
        .map { l ->l[0].get(l[0].map{c-> l[1].contains(c)}.indexOf(true))}
        .map{c->prioPoints.indexOf(c)+1}.sum()
}

fun B_SumPoints(list:List<String>):Int{

   return list.chunked(3)
        .map{l->l[0].get(l[0].map{c->l[1].contains(c)&&l[2].contains(c)}.indexOf(true))}
        .map{c-> prioPoints.indexOf(c)+1}.sum()
}