package com.example.demo.Repositories;

import com.example.demo.Entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT a FROM Record a WHERE CONCAT(a.title, ' ', a.postdate, ' ', a.content, ' ', a.image) LIKE %?1%")
    List<Record> search(String keyword);
}

