package com.example.demo.Services;

import com.example.demo.Entities.Record;
import com.example.demo.Repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository aRepo;

    public List<Record> listAll(String keyword) {
        if (keyword != null) {
            return aRepo.search(keyword);
        }
        return aRepo.findAll();
    }
    public void save(Record record) { aRepo.save(record); }

    public Record get(Long id) { return aRepo.findById(id).get(); }

    public List<Record> getList(Long id) { return Arrays.asList(aRepo.findById(id).get()); }
    public void delete(Long id) { aRepo.deleteById(id); }
}
