package com.example.respository

import com.example.Entities.Todo
import com.example.Entities.TodoDraft

interface TodoRespository {

    fun getAllTodo():List<Todo>

    fun getTodo(id:Int?):Todo?

    fun createTodo(todo:TodoDraft):Todo

    fun removeTodo(id:Int?):Boolean

    fun updateTodo(id:Int?,todo:TodoDraft):Boolean
}