package com.example

import com.example.Entities.Todo
import com.example.Entities.TodoDraft
import com.example.respository.InMemoryTodoRepository
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.TodoRouting(){
    val repository=InMemoryTodoRepository()
    route("/todo"){
        get("/get/{id}"){
            val id: Int?=call.parameters["id"]?.toInt()
            val result: Todo? =repository.getTodo(id)
            if(result==null){
                call.respond(401)
                return@get
            }
            call.respond(result)
        }
        get("/get/all"){
            call.respond(repository.getAllTodo())
        }
        post("/create"){
            val todo=call.receive(TodoDraft::class)
            call.respond(repository.createTodo(todo))
        }
        put("/update/{id}"){
            val id= call.parameters["id"]?.toInt()
            val todo=call.receive(TodoDraft::class)
            call.respond(repository.updateTodo(id,todo))
        }
        delete("/delete/{id}"){
            val id= call.parameters["id"]?.toInt()
            call.respond(repository.removeTodo(id))
        }
    }
}
class TodoRouting {
}