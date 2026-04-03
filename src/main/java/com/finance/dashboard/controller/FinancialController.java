package com.finance.dashboard.controller;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/finance")
public class FinancialController {

    @Autowired
    private FinancialService service;

    // CREATE
    @PostMapping
    public FinancialRecord create(@RequestBody FinancialRecord record) {
        return service.create(record);
    }

    // READ ALL
    @GetMapping
    public List<FinancialRecord> getAll() {
        return service.getAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public FinancialRecord update(@PathVariable Long id, @RequestBody FinancialRecord record) {
        return service.update(id, record);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }

    // SUMMARY
    @GetMapping("/summary")
    public Map<String, Double> summary() {
        Map<String, Double> map = new HashMap<>();
        map.put("income", service.totalIncome());
        map.put("expense", service.totalExpense());
        map.put("balance", service.balance());
        return map;
    }
}