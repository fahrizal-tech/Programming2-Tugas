package com.belajar.demo_app.controller;

import com.belajar.demo_app.model.Task;
import com.belajar.demo_app.service.TaskService;
import com.belajar.demo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    @Autowired
    public TaskController(TaskService taskService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    // Redirect root to tasks list
    @GetMapping("/")
    public String index() {
        return "redirect:/tasks";
    }

    // List all tasks
    @GetMapping("/tasks")
    public String listTasks(Model model, Principal principal) {
        addLoggedInUserToModel(model, principal);
        List<Task> tasks = taskService.getAllTasks();
        
        // Initialize dummy data if table is empty
        if (tasks.isEmpty()) {
            taskService.saveTask(new Task("Tugas 1: Instalasi Spring Boot", "Melakukan instalasi JDK 21, Spring Boot CLI, dan IDE untuk persiapan belajar Pemrograman 2.", "Pemrograman 2", LocalDate.now().plusDays(2), "Selesai", "Tinggi"));
            taskService.saveTask(new Task("Tugas 2: Pembuatan CRUD", "Membuat aplikasi CRUD lengkap dengan database H2 dan template Thymeleaf.", "Pemrograman 2", LocalDate.now().plusDays(5), "Sedang Dikerjakan", "Tinggi"));
            taskService.saveTask(new Task("Tugas 3: Relasi Database", "Mempelajari anotasi JPA @OneToMany dan @ManyToOne pada entitas Mahasiswa dan Mata Kuliah.", "Pemrograman 2", LocalDate.now().plusDays(10), "Belum Mulai", "Sedang"));
            taskService.saveTask(new Task("Kuis 1: Konsep MVC", "Mengerjakan kuis online mengenai Model-View-Controller di Spring Boot.", "Pemrograman 2", LocalDate.now().plusDays(1), "Belum Mulai", "Rendah"));
            tasks = taskService.getAllTasks();
        }
        
        // Calculate statistics
        long totalTasks = tasks.size();
        long belumMulai = tasks.stream().filter(t -> "Belum Mulai".equals(t.getStatus())).count();
        long sedangDikerjakan = tasks.stream().filter(t -> "Sedang Dikerjakan".equals(t.getStatus())).count();
        long selesai = tasks.stream().filter(t -> "Selesai".equals(t.getStatus())).count();
        
        model.addAttribute("tasks", tasks);
        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("belumMulai", belumMulai);
        model.addAttribute("sedangDikerjakan", sedangDikerjakan);
        model.addAttribute("selesai", selesai);
        
        return "tasks/index";
    }

    // Show single task detail
    @GetMapping("/tasks/{id}")
    public String viewTask(@PathVariable Long id, Model model, Principal principal) {
        addLoggedInUserToModel(model, principal);
        try {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
            return "tasks/detail";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/404";
        }
    }

    // Show creation form
    @GetMapping("/tasks/new")
    public String showCreateForm(Model model, Principal principal) {
        addLoggedInUserToModel(model, principal);
        model.addAttribute("task", new Task());
        return "tasks/create";
    }

    // Handle creation submit
    @PostMapping("/tasks")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    // Show edit form
    @GetMapping("/tasks/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        addLoggedInUserToModel(model, principal);
        try {
            Task task = taskService.getTaskById(id);
            model.addAttribute("task", task);
            return "tasks/edit";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/404";
        }
    }

    // Handle update submit
    @PostMapping("/tasks/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task) {
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    // Handle deletion
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    // Helper method to add user details to model
    private void addLoggedInUserToModel(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            userRepository.findByUsername(username).ifPresent(user -> {
                model.addAttribute("loggedInUserFullName", user.getFullName());
                model.addAttribute("loggedInUserUsername", user.getUsername());
                
                String initials = "";
                if (user.getFullName() != null && !user.getFullName().isEmpty()) {
                    String[] parts = user.getFullName().trim().split("\\s+");
                    if (parts.length > 0 && !parts[0].isEmpty()) {
                        initials += parts[0].substring(0, 1).toUpperCase();
                    }
                    if (parts.length > 1 && !parts[1].isEmpty()) {
                        initials += parts[1].substring(0, 1).toUpperCase();
                    }
                }
                if (initials.isEmpty()) {
                    initials = "U";
                }
                model.addAttribute("loggedInUserInitials", initials);
            });
        } else {
            model.addAttribute("loggedInUserFullName", "Muhamad Fahrizal");
            model.addAttribute("loggedInUserUsername", "fahrizal");
            model.addAttribute("loggedInUserInitials", "MF");
        }
    }
}
