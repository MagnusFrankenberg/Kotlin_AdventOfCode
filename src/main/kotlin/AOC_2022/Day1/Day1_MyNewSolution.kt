package AOC_2022.Day1


import java.io.File

 val myString = File("src/main/kotlin/AOC_2022/Day1/Data1.txt").readText(Charsets.UTF_8)

fun main(){
      println( getMostCalories(myString,1))
      println( getMostCalories(myString,3))

}

fun getMostCalories(string:String, ix:Int):Int{

   return string
        .trim()
        .split("\n\n")
        .map{s->s.replace("\n",",")}
        .map{s->s.split(",")}
        .map{list->list.map{s->s.toInt()}}
        .map{list->list.sum()}.sortedDescending()
        .take(ix).sum()

}
