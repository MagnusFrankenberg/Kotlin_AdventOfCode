
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
            else -> throw IllegalArgumentException("Unknown input: $dir")
        }
    }

    fun AB_moveTail(tailIndex:Int):MutableList<Point>{
        val tailHistory = mutableListOf<Point>()
        val knots = mutableListOf<Point>() //2 el 10 knots
        (0..tailIndex).forEach { knots.add(Point(0,0)) } //lägger till 2 el 10 knots med startpunkt 0,0
        tailHistory.add(knots[tailIndex])

        for(dir in allMovements(myList)) {
            knots[0] = moveHead(knots[0], dir) //anrop till funktionen som flyttar head
            (0..tailIndex-1).forEach{i->
                when{
                    //om knots[i] hoppar diagonalt så hamnar både x & y 2 positioner från knots[i+1] (för B-uppgiften)
                    knots[i].x-knots[i+1].x==2 && knots[i].y-knots[i+1].y==2 -> knots.set(i+1,Point(knots[i].x-1,knots[i].y-1))
                    knots[i].x-knots[i+1].x==2 && knots[i].y-knots[i+1].y==-2-> knots.set(i+1,Point(knots[i].x-1,knots[i].y+1))
                    knots[i].x-knots[i+1].x==-2 && knots[i].y-knots[i+1].y==2 -> knots.set(i+1,Point(knots[i].x+1,knots[i].y-1))
                    knots[i].x-knots[i+1].x==-2 && knots[i].y-knots[i+1].y==-2 -> knots.set(i+1,Point(knots[i].x+1,knots[i].y+1))

                    //Då knots[i] hoppar icke-diagonalt kommer antingen x eller y hamna 2 positioner från knots[i+1] (för A och B uppg)
                    knots[i].x-knots[i+1].x==2-> knots.set(i+1,Point(knots[i].x-1,knots[i].y))
                    knots[i].x-knots[i+1].x==-2-> knots.set(i+1,Point(knots[i].x+1,knots[i].y))
                    knots[i].y-knots[i+1].y==2-> knots.set(i+1,Point(knots[i].x,knots[i].y-1))
                    knots[i].y-knots[i+1].y==-2-> knots.set(i+1,Point(knots[i].x,knots[i].y+1))
                }
            }
            tailHistory.add(knots[tailIndex])
        }
        return tailHistory
    }

    fun main(){
        println("A: Antal positioner T besökt minst en gång: "+ AB_moveTail(1).toSet().count())
        println("B: Antal positioner T besökt minst en gång: "+ AB_moveTail(9).toSet().count())

    }

