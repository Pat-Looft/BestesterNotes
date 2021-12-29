import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    val ds = HikariDataSource()
    ds.driverClassName = "com.mysql.cj.jdbc.Driver"
    ds.jdbcUrl = "jdbc:mysql://localhost:3306/library"
    ds.username = "root"
    ds.password = "admin"

    transaction(Database.connect(url = ds.jdbcUrl.replace("hikari", ""),
        driver = ds.driverClassName,
        user = ds.username,
        password = ds.password)) {
        SchemaUtils.createDatabase("hikari")
    }

    //Creating the users table
    transaction(Database.connect(ds)) {
        SchemaUtils.create(Users)
    }
}

object Users : Table() {
    val id = varchar("id", 10) // Column<String>
    val name = varchar("name", length = 128) // Column<String>
    val desc = varchar("description", length = 4096)//Column<String>
    override val primaryKey = PrimaryKey(id, name = "PK_User_ID")
}
