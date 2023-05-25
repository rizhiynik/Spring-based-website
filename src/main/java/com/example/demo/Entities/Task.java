package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="task")
public class Task {
    private Long id;
    private String title;
    private String description;
    private String start_date;
    private String end_date;
    private String reward;
    private String userName;

    public Task() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия автоматической генерации "подтягивается" из базы данных (auto-increment)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName;
    }

    @Override
    public String toString() {
        return "task [id=" + id + ", title=" + title + ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + ", reward=" + reward + ", userName=" + userName + "]";
    }
}
