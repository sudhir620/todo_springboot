package com.aksoft.todolist.controller;

import com.aksoft.todolist.entity.Todo;
import com.aksoft.todolist.service.TodoService;
import com.aksoft.todolist.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add_task")
    public ResponseEntity<String> createTask(@RequestBody Todo todo){
        return ResponseUtils.finalResponse(HttpStatus.CREATED, "Task is create successfully!", todoService.createTask(todo));
    }

    @GetMapping("/get_tasks")
    public ResponseEntity<String> getAllTasks(){
        List<Todo> allTasks = todoService.getAllTasks();
        boolean isEmpty = allTasks.isEmpty();
        return ResponseUtils.finalResponse(isEmpty ? HttpStatus.NOT_FOUND : HttpStatus.OK, isEmpty ? "Data not found!" : "Your all tasks!", allTasks);
    }

    @PutMapping("/update_task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Todo todo){
        try{
            System.out.println(todo.toString());
            if(todo.isDeleted()){
                return ResponseUtils.finalResponse(HttpStatus.METHOD_NOT_ALLOWED, "Deletion not allowed!", null);
            }
            return ResponseUtils.finalResponse(HttpStatus.OK, "Task is updated successfully!", todoService.updateTask(id, todo));
        }catch (Exception e){
            return ResponseUtils.finalResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @DeleteMapping("/delete_task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        try {
            todoService.deleteTask(id);
            return ResponseUtils.finalResponse(HttpStatus.OK, "Task is deleted successfully!", null);
        } catch (Exception e) {
            return ResponseUtils.finalResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

    @GetMapping("/get_task_details/{id}")
    public ResponseEntity<String> getTaskDetails(@PathVariable Long id){
        try {
            Todo todo = todoService.getTaskDetails(id);
            return ResponseUtils.finalResponse(HttpStatus.OK, "task details found!", todo);
        } catch (Exception e){
            return ResponseUtils.finalResponse(HttpStatus.NOT_FOUND, e.getMessage(), null);
        }
    }

}
