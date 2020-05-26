package com.prinzh.schedule.data.db.config

import com.improve_future.harmonica.core.DbConfig
import com.improve_future.harmonica.core.Dbms

class Default: DbConfig({
    dbms = Dbms.MySQL
    dbName = "Schedule"
    host = "192.168.99.106"
    port = 32769
    sslmode = false
    user = "root"
    password = "password"
})