package com.example.database

import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DbTodoTable: Table<DBTodoEntity>("todo"){
    val id=int("id").primaryKey().bindTo {it.id  }
    val title=varchar("title").bindTo {it.title  }
    val email=varchar("email").bindTo { it.email }
    val done=boolean("done").bindTo { it.done }
}