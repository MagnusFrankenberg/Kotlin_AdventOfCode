package Day7

import java.io.File
import java.util.Stack

//Gjorde tidigare en första version i Java, utan rekursion.
//i denna Kotlin-version har jag försökt slimma ner koden genom att jobba funktionellt,
//Jag har dessutom velat träna på rekursion och har inkluderat det i koden


fun fileasList(fileName: String): List<String> = File(fileName).useLines { it.toList() }
val list = fileasList("src/main/kotlin/AOC_2022/Day7/Data7.txt")

//klass som representerar ett directory
    class Dir(val name: String) {
    val directories = mutableListOf<Dir>()
    val fileList = mutableListOf<Int>()
    var size:Int=0
}
//skapar upp ett första root-directory
var root = Dir("/")


fun createTree(list: List<String>):Dir {
    var currentDir = root

    var directoryStack = Stack<Dir>()
    directoryStack.push(currentDir)  //en stack håller koll på var i hierarkin vi befinner oss

    for (line in list) {
        when {
            //om numerisk är det en fil vars storlek vi lägger in
            line[0].isDigit()-> {
                currentDir.size += line.split(" ")[0].toInt()
                currentDir.fileList.add(line.split(" ")[0].toInt())
            }
            //om det är dir, skapar vi upp ett nytt directory-objekt (med namnet i konstruktorn)
            line.startsWith("dir ") -> currentDir.directories.add(Dir(line.split(" ")[1]))

            //om cd ska vi byta current directory
            line.startsWith("$ cd") ->
                //om vi ska gå ut ur ett directory:
                if (line.split(" ")[2].equals("..")) {
                    var tempsize = currentDir.size  //när vi lämnar en besökt directory tar vi med dess size
                    directoryStack.pop()
                    currentDir = directoryStack.peek()
                    currentDir.size += tempsize  //vi lägger på vår medtagna size på parent-directory

                    // om går in i ett nytt directory -> ändra currentDir och directoryStack
                } else {
                    currentDir.directories.forEach {dir-> if(dir.name.equals(line.split(" ")[2])){
                        currentDir = dir
                        directoryStack.push(currentDir)
                    } }
                }
            //ingen action, vi går vidare i listan
            line.startsWith("$ ls") -> continue
        }
    }
    //eftersom vi inte hoppar tillbaka hela vägen till root med cd.. måste root.size uppdateras slutligt här:
    currentDir = root
    var size = 0
    currentDir.directories.forEach { dir-> size += dir.size }  //samlar ihop size från root subdirectories
    currentDir.size = size + currentDir.fileList.sum()  //uppdaterar root.size med subdir + file -size
    return currentDir
}


//hitta samtliga directories och returnera i lista (med rekursion) currentDir=root vid start
fun findAllDirectories(rootDir:Dir):MutableList<Dir> {
    val allDirectories = mutableListOf<Dir>()
    var currentDir = rootDir

    fun findAllSubDirs(dirName: String) {
            currentDir.directories.forEach { dir -> allDirectories.add(dir) }
            currentDir.directories.forEach { dir -> currentDir = dir
                findAllSubDirs(currentDir.name)
            }
        }
    allDirectories.add(rootDir) //lägger först till rootdir i listan
    findAllSubDirs(currentDir.name) //kör sedan inre funktion som ger resten av directories
    return allDirectories
}



//B-uppgiften: hitta det minsta directory att ta bort som gör att vi får 30.000.000 unused space på disken.
fun B_findDirectoryToDelete(allDirectories:MutableList<Dir>):Int {
    val needToDelete = 30000000 - (70000000 - allDirectories[0].size)  //allDirectories[0] = root
    val dirToDelete = allDirectories.map { it.size }.filter { it >= needToDelete }.min()
    return dirToDelete
}

fun main() {

    val myTree = createTree(list)
    val allDirectories = findAllDirectories(myTree)

    println("Del A: Tot size på alla dir<=100000: "+findAllDirectories(myTree).map{it.size}.filter{ i->i<=100000}.sum())

    println("Del B: directory(size) att ta bort: "+B_findDirectoryToDelete(allDirectories))

    println("Av Koden Skapade directories: "+ findAllDirectories(myTree).count())
    println("Antal 'dir' från input-filen: " + (list.filter{it.startsWith("dir")}.map { it.split(" ")[1] }.count() + 1))

    println(findAllDirectories(myTree))

}


/*
// första versionen av rekursion som jag sedan har slimmat ner ovan:

fun findAllDirectories(rootDir:Dir):MutableList<Dir> {
    val allDirectories = mutableListOf<Dir>()
    var currentDir = rootDir

    fun findAllSubDirs(dirName: String) {
        if (currentDir.name.equals(dirName)) {
            currentDir.directories.forEach { dir -> allDirectories.add(dir) }

            currentDir.directories.forEach { dir ->
                currentDir = dir
                findAllSubDirs(currentDir.name)
            }
        }
        else {
            //om dirName inte är root("/") -> gå vidare ner en nivå (currentDir=dir) och leta
            currentDir.directories.forEach { dir ->
                currentDir = dir
                findAllSubDirs(dirName)
            }
        }
    }

    allDirectories.add(rootDir) //lägger först till rootdir i listan
    findAllSubDirs(currentDir.name) //kör sedan inre funktion som ger resten av directories
    return allDirectories
}
*/
