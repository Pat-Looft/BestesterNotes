package tables1.`0`

class CLI {
}

//The testing interface for creating notes (and deleting and modifying, but that is down the line)
fun main(){
    var userEntry = notePrompt()
}

/** Request the user to enter a note type, and return the int that it is*/
fun notePrompt() : Int{
    //TODO: this should be an enumeration of all the notes, could automate with any newclass having note in the name gets added or some shiz
    val noteTypeList = mutableListOf("1) Basic Note", "2) List Note")
    val userInputNoteType : String
    println("Please enter the number of the note type you would like to create: ")
    noteTypeList.forEach {
        println(it)
    }
    userInputNoteType = readLine()!!
    return userInputNoteType.toInt()
}