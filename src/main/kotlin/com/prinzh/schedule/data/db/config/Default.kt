package com.prinzh.schedule.data.db.config

import com.improve_future.harmonica.core.DbConfig
import com.improve_future.harmonica.core.Dbms

class Default: DbConfig({
    dbms = Dbms.MySQL
    dbName = "<Database name>"
    host = "<Your host>"
    port = 32769
    sslmode = false
    user = "<Database user>"
    password = "Database user password"
})