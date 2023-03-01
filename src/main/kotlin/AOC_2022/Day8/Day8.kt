package AOC_2022.Day8

import java.io.File

val myRows = File("src/main/kotlin/AOC_2022/Day8/Data8.txt").useLines { it.toList() }
val myCols = myCols(myRows)

fun main() {
    println("Total Trees visible from outside: " + countVisible(myRows, myCols))
    println("Maximum scenic score: " + getScenicScore(myRows, myCols))
}

fun myCols(list: List<String>): List<String> {
    return (0..list[0].length - 1)  //skapa en range 0...length-1 av en rad (index-positioner)
        .map { myIndex ->  //transformera om varje indexposition till..
            list.map { string -> string[myIndex] }  // transformera om varje string(rad) i listan till en char baserat på myIndex
                .joinToString("")  // joina dessa chars till en string
        }
        .toList() //lägg alla string i lista
}

    //PartA: isVisible from outside grid
fun isVisibleFromOutside(string:String,position:Int):Boolean{
   var isVisible = false
    //position is at any edge?
    if((position==0)||(position==string.length-1)) {
        isVisible = true

    }else if   (
        //left of position is less than position element ?
        ( string.substring(0..position - 1)
            .map { c -> c.digitToInt() }.toList()
            .map { it < string[position].digitToInt() }.count { it == false } == 0 ) ||

        //..or right of position is less than position element ?
            (string.substring(position + 1..string.length - 1)
            .map { c -> c.digitToInt() }.toList()
            .map { it < string[position].digitToInt() }.count { it == false } == 0 )){

            isVisible = true
    }
    return isVisible
}

// Part A: how many trees are visible from outside the grid?

fun countVisible(myRows:List<String>,myCols:List<String>):Int{
     var counter = 0
    for((rowsIndex,row) in myRows.withIndex()){
        for((colsIndex,char) in row.withIndex()){
           if(isVisibleFromOutside(row,colsIndex)||isVisibleFromOutside(myCols[colsIndex],rowsIndex))
               counter++
        }
    }
        return counter
}


//return visible trees in one given direction (string) (up and left looking strings needs to be reversed)
        fun countTrees(string:String,myTreeHeight:Int):Int{

             fun innerCountTrees(string:String,index:Int,myTreeHeight:Int):Int{
                 if(index>=string.length-1 || myTreeHeight <= string[index].digitToInt()) return 1
                 return 1 + innerCountTrees(string,index+1,myTreeHeight)
             }
              return innerCountTrees(string,0,myTreeHeight)
        }
// Part B: Visibility from any given tree in all directions
fun getScenicScore(myRows:List<String>,myCols:List<String>):Int{
    val allScores = mutableListOf<Int>()

    for((rowsIndex,row) in myRows.withIndex()){
        for((colsIndex,char) in row.withIndex()){

            if(rowsIndex==0||rowsIndex==myRows.size-1||colsIndex==0||colsIndex==myCols.size-1){ allScores.add(0)}

            else {
                var treeHeight = char.digitToInt()
                var pointsRight = countTrees(row.substring(colsIndex + 1..row.length - 1), treeHeight)
                var pointsLeft = countTrees(row.substring(0..colsIndex - 1).reversed(), treeHeight)//behöver reverseras när tittar vänster
                var pointsDown = countTrees(myCols[colsIndex].substring(rowsIndex + 1..myCols[colsIndex].length - 1), treeHeight)
                var pointsUp = countTrees(myCols[colsIndex].substring(0..rowsIndex - 1).reversed(), treeHeight)//behöver reverseras när tittar uppåt

                allScores.add(pointsRight * pointsLeft * pointsDown * pointsUp)
            }
        }
    }
    return allScores.max()
}


