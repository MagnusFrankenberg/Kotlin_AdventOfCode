package Day6

var queue2 = mutableListOf<Char>()

fun main(){

    println("Del A: "+ countCharsToMarker2(list,4))
    println("Del B: "+ countCharsToMarker2(list,14))
}

//Jämfört med första lösningen har jag slimmat ner denna funktion. Istället för att använda mig av en
//counter-variable i en for-loop för att räkna fram när jag får true, applicerar jag här en map som
//transformerar listan till false eller true-värden. Med indexOf(true) får jag första positionen i listan med true.
fun countCharsToMarker2(list:List<String>, seqSize:Int):Int{
   return list.get(0).map{c-> hasUniqueChars2(queue2(c,seqSize),seqSize) }.indexOf(true) + 1
}

fun queue2(c:Char, seqSize:Int):List<Char>{
    if(queue2.size<seqSize) {
        queue2.add(c)
    }else {
        queue2.removeAt(0)
        queue2.add(c)
    }
    return queue2
}

fun hasUniqueChars2(list:List<Char>, seqSize:Int):Boolean{
    return (list.size==seqSize) && (list.map { it }.toSet().size == list.size )
}
