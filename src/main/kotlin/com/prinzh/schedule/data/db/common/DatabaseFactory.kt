package com.prinzh.schedule.data.db.common

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private lateinit var db: Database

    fun init() {
        db = Database.connect(hikari())

        transaction {
            addLogger()
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "com.mysql.jdbc.Driver"
            jdbcUrl = "jdbc:mysql://192.168.99.106:32769/Schedule?allowPublicKeyRetrieval=true&useSSL=false"
            username = "root"
            password = "password"
            isAutoCommit = true
            maximumPoolSize = 5
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(
        block: suspend () -> T
    ): T =
        newSuspendedTransaction(Dispatchers.IO, db) { block() }
}