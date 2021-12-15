package tables1.`0`

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


object BasicNoteTable : IntIdTable() {
    val sequelId = integer("sequel_id").uniqueIndex()
    val name = varchar("name", 50)
    val description = varchar("description", 50)
}

class BasicNote(id: EntityID<Int>) : IntEntity(id)  {
    companion object : IntEntityClass<BasicNote>(BasicNoteTable)

    var sequelId by BasicNoteTable.sequelId
    var name by BasicNoteTable.name
    var description by BasicNoteTable.description
}