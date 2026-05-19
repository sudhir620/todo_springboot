package com.aksoft.todolist.service;

import com.aksoft.todolist.repository.TodoRepository;
import com.aksoft.todolist.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService() {
    }

    public Todo createTask(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo updateTask(Long id, Todo todo){
        Todo existingTask = todoRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
        existingTask.setTaskName(todo.getTaskName());
        existingTask.setTaskDescription(todo.getTaskDescription());
        existingTask.setTaskStatus(todo.getTaskStatus());
        return todoRepository.save(existingTask);
    }

    public List<Todo> getAllTasks(){
        return todoRepository.findAll();
    }
}
