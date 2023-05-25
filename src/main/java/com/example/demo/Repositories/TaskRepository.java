package com.example.demo.Repositories;

import java.util.List;

import com.example.demo.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository; // Позволяет задавать класс в качестве репозитория, связывающего сущность с базой данных
import org.springframework.data.jpa.repository.Query; // Определяем запрос к базе данных


public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT p FROM Task p WHERE " +
            "((:titleCheckbox = true and p.title like %:keyword%) or :titleCheckbox = false) " +
            "and ((:descriptionCheckbox = true and p.description like %:keyword%) or :descriptionCheckbox = false) " +
            "and ((:startDateCheckbox = true and p.start_date like %:keyword%) or :startDateCheckbox = false) " +
            "and ((:endDateCheckbox = true and p.end_date like %:keyword%) or :endDateCheckbox = false) " +
            "and ((:rewardCheckbox = true and p.reward like %:keyword%) or :rewardCheckbox = false)")
    List<Task> search(String keyword, boolean titleCheckbox, boolean descriptionCheckbox, boolean startDateCheckbox,
                      boolean endDateCheckbox, boolean rewardCheckbox);
}
