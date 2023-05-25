package com.example.demo.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Entities.Task;
import com.example.demo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired; // Определяем класс или поле в качестве главного контейнера, связывающего остальные зависимости между друг другом
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service; // Задаем класс в качестве объекта, определяющего бизнес логику

@Service
public class TaskService {
    @Autowired
    private TaskRepository repo;

    @PreAuthorize("isAuthenticated()")
    public List<Task> search(String keyword, boolean titleCheckbox, boolean descriptionCheckbox, boolean startDateCheckbox,
                             boolean endDateCheckbox, boolean rewardCheckbox) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        return repo.search(keyword, titleCheckbox, descriptionCheckbox, startDateCheckbox,
                endDateCheckbox, rewardCheckbox).stream().filter(task -> task.getUserName().equals(userName)).collect(Collectors.toList());
    }

    public void save(Task task) {
        repo.save(task);
    }

    public Task get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}

