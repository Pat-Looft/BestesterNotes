import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import com.mysql.jdbc.Driver

import java.sql.Connection;
import java.sql.SQLException;

fun main(args: Array<String>) {

    //LOAD properties
//    val config = HikariConfig("../../../hikari.properties")
    //Hikari properties content
    //    jdbcUrl=jdbc:mysql://localhost:3306/hikari
    //    driverClassName=com.mysql.cj.jdbc.Driver
    //    username=root
    //    password=<pwd here>
    //Here replace dbname from jdbc url to empty
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

    transaction(Database.connect(ds)) {
        SchemaUtils.create(Users)
    }
}

object Users : Table() {
    val id = varchar("id", 10) // Column<String>
    val name = varchar("name", length = 50) // Column<String>
    override val primaryKey = PrimaryKey(id, name = "PK_User_ID")
}
