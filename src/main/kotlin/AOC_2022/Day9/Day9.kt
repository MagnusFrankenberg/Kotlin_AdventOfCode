package AOC_2022.Day9

import java.io.File

val myList = File("src/main/kotlin/AOC_2022/Day9/Data9.txt").useLines { it.toList() }

data class Point(val x:Int,val y:Int)  //genom att använda data class kan vi lägga alla points i ett Set och få bort alla duplicates

fun allMovements(list:List<String>):List<Char> {
    return list.map { s ->
        (0..s.split(" ")[1].toInt() - 1)  //varje rad i indatan görs om till en range av indextal där rangen får samma storlek som siffran på raden
            .map { index -> s[0] }  //gör om varje index i denna range till riktningsbokstaven på position 0 (L,R,U,D).
    }.flatten() //ta bort lists of list så alla chars ligger i en enda List of chars
}

fun moveHead(H:Point,dir:Char):Point{
    when(dir){
        'R'-> return Point(H.x+1,H.y)
        'L'-> return Point(H.x-1,H.y)
        'U'-> return Point(H.x, H.y+1)
        'D'-> return Point(H.x,H.y-1)
        else -> error("error occurred")
    }
}


fun A_moveTail():MutableList<Point>{
    val tailHistory = mutableListOf<Point>()
    var H = Point(0,0) //startposition
    var T = Point(0,0) //startposition
    tailHistory.add(T)

    for(dir in allMovements(myList)){
        H = moveHead(H,dir)  //anrop till funktionen som flyttar head

        when{
            H.x-T.x == 2 -> T = Point(H.x-1,H.y)
            H.x-T.x == -2 -> T = Point(H.x+1,H.y)
            H.y-T.y == 2 -> T = Point(H.x,H.y-1)
            H.y-T.y == -2 -> T = Point(H.x,H.y+1)
        }
        tailHistory.add(T)
    }
    return tailHistory
}

fun B_moveTail():MutableList<Point>{
    val tailHistory = mutableListOf<Point>()
    val knots = mutableListOf<Point>() //10 knots
    (0..9).forEach { knots.add(Point(0,0)) } //lägger till 10 knots med startpunkt 0,0
    tailHistory.add(knots[9])

    for(dir in allMovements(myList)) {
        knots[0] = moveHead(knots[0], dir) //anrop till funktionen som flyttar head
        (0..8).forEach{i->
            when{
                //om knots[i] hoppar diagonalt så hamnar både x & y 2 positioner från knots[i+1]
                knots[i].x-knots[i+1].x==2 && knots[i].y-knots[i+1].y==2 -> knots.set(i+1,Point(knots[i].x-1,knots[i].y-1))
                knots[i].x-knots[i+1].x==2 && knots[i].y-knots[i+1].y==-2-> knots.set(i+1,Point(knots[i].x-1,knots[i].y+1))
                knots[i].x-knots[i+1].x==-2 && knots[i].y-knots[i+1].y==2 -> knots.set(i+1,Point(knots[i].x+1,knots[i].y-1))
                knots[i].x-knots[i+1].x==-2 && knots[i].y-knots[i+1].y==-2 -> knots.set(i+1,Point(knots[i].x+1,knots[i].y+1))

                //Dessa funkar på samma sätt som A-uppgiften
                knots[i].x-knots[i+1].x==2-> knots.set(i+1,Point(knots[i].x-1,knots[i].y))
                knots[i].x-knots[i+1].x==-2-> knots.set(i+1,Point(knots[i].x+1,knots[i].y))
                knots[i].y-knots[i+1].y==2-> knots.set(i+1,Point(knots[i].x,knots[i].y-1))
                knots[i].y-knots[i+1].y==-2-> knots.set(i+1,Point(knots[i].x,knots[i].y+1))
        }
        }
        tailHistory.add(knots[9])
    }
    return tailHistory
}
fun main(){
    println("A: Antal positioner T besökt minst en gång: "+ A_moveTail().toSet().count())
    println("B: Antal positioner T besökt minst en gång: "+ B_moveTail().toSet().count())

}