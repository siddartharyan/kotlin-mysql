package com.example.respository

import com.example.Entities.Todo
import com.example.Entities.TodoDraft
import com.example.database.DatabaseManager

class InMemoryTodoRepository:TodoRespository {
    private val databaseManager=DatabaseManager()
    override fun getAllTodo(): List<Todo> {
        return databaseManager.getAllTodos().map{Todo(it.id,it.title,it.email,it.done)}
    }

    override fun getTodo(id: Int?): Todo? {
        return databaseManager?.getTodo(id)?.let{Todo(it.id,it.title,it.email,it.done)}
    }

    override fun createTodo(todo:TodoDraft):Todo{
        return databaseManager.createTodo(todo).let{Todo(it.id,it.title,it.email,it.done)}
    }

    override fun removeTodo(id: Int?): Boolean {
        return databaseManager.deleteTodo(id)
    }

    override fun updateTodo(id:Int?,todo:TodoDraft):Boolean{
        return databaseManager.updateTodo(id,todo)
    }

}