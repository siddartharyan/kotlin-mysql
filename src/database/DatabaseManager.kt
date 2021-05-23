package com.example.database

import com.example.Entities.Todo
import com.example.Entities.TodoDraft
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    //config
    private val hostname="localhost:3306"
    private val databasename="kotlin"
    private val username="root"
    private val password="954954"

    //database
    private val ktormDatabase: Database

    init{
        val jdbcUrl="jdbc:mysql://$hostname/$databasename?user=$username&password=$password&useSSL=false"
        ktormDatabase= Database.connect(jdbcUrl)
    }

    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DbTodoTable).toList()
    }

    fun getTodo(id:Int?): DBTodoEntity? {
        if(id==null) return null
        return ktormDatabase.sequenceOf(DbTodoTable).firstOrNull { it.id eq id }
    }

    fun createTodo(todo:TodoDraft): Todo {
        val insertedKey:Int=ktormDatabase.insertAndGenerateKey(DbTodoTable){
            set(DbTodoTable.title,todo.title)
            set(DbTodoTable.email,todo.email)
            set(DbTodoTable.done,todo.done)
        } as Int
        return Todo(insertedKey,todo.title,todo.email,todo.done)
    }

    fun updateTodo(id:Int?,todo:TodoDraft): Boolean {
        if(id==null) return false
        val updatedRows=ktormDatabase.update(DbTodoTable){
            set(DbTodoTable.title,todo.title)
            set(DbTodoTable.email,todo.email)
            where{
                it.id eq id
            }
        }
        return updatedRows>0
    }

    fun deleteTodo(id:Int?): Boolean {
        if(id==null) return false
        val deleted=ktormDatabase.delete(DbTodoTable){
            it.id eq id
        }
        return deleted>0
    }
}