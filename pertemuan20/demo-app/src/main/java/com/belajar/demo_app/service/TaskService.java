package com.belajar.demo_app.service;

import com.belajar.demo_app.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task saveTask(Task task);
    void deleteTask(Long id);
}
