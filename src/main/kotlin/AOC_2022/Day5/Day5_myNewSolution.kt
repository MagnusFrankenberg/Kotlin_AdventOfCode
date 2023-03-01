package Day5

import java.io.File
fun fileasList2(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list2 = fileasList2("src/main/kotlin/AOC_2022/Day5/moveData.txt")

var l10 = mutableListOf<Char>('B','S','V','Z','G','P','W')
var l20 = mutableListOf<Char>('J','V','B','C','Z','F')
var l30 = mutableListOf<Char>('V','L','M','H','N','Z','D','C')
var l40 = mutableListOf<Char>('L','D','M','Z','P','F','J','B')
var l50 = mutableListOf<Char>('V','F','C','G','J','B','Q','H')
var l60 = mutableListOf<Char>('G','F','Q','T','S','L','B')
var l70 = mutableListOf<Char>('L','G','C','Z','V')
var l80 = mutableListOf<Char>('N','L','G')
var l90 = mutableListOf<Char>('J','F','H','C')

var myMap2 = mutableMapOf<Int,MutableList<Char>>(1 to l10,2 to l20,3 to l30,4 to l40,5 to l50,6 to l60,7 to l70,8 to l80,9 to l90)


    fun main() {

        A_DoEverything_and_PrintResult(list2)
//  B_DoEverything_and_PrintResult(list2)
    }

//I denna version har jag lagt ihop koden från mina tidigare funktioner till en funktion per A resp B uppgift


    fun A_DoEverything_and_PrintResult(list: List<String>) {
        list.map { s ->
            s.split(" ")
                .filterNot { s -> s.equals("move") || s.equals("from") || s.equals("to") }
                .map { s -> s.toInt() }
        }

            .forEach { moveData ->
                (moveData[0] - 1 downTo 0).forEach { i ->
                    myMap2[moveData[2]]!!.add(
                        myMap2[moveData[1]]!!.takeLast(
                            moveData[0]
                        ).get(i)
                    )
                }
                myMap2.replace(moveData[1], myMap2[moveData[1]]!!.dropLast(moveData[0]).toMutableList())
            }
        println(myMap2.map { m -> m.value.takeLast(1) }.flatten().toList())
    }


    fun B_DoEverything_and_PrintResult(list: List<String>) {
        list.map { s ->
            s.split(" ")
                .filterNot { s -> s.equals("move") || s.equals("from") || s.equals("to") }
                .map { s -> s.toInt() }
        }

            .forEach { moveData ->
                myMap2[moveData[2]]!!.addAll((myMap2[moveData[1]]!!.takeLast(moveData[0])))
                myMap2.replace(moveData[1], myMap2[moveData[1]]!!.dropLast(moveData[0]).toMutableList())
            }
        println(myMap2.map { m -> m.value.takeLast(1) }.flatten().toList())
    }
