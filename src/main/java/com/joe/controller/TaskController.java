package com.joe.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping("/getTasks")
    public String listTasks(){
        return "任务列表";
    }

    @PostMapping
    public String newTasks(){
        return "创建了一个新的任务";
    }

   
}