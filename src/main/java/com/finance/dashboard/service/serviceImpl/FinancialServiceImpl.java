package com.finance.dashboard.service.serviceImpl;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.Type;
import com.finance.dashboard.repository.FinancialRecordRepository;
import com.finance.dashboard.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    private FinancialRecordRepository repo;

    // CREATE
    @Override
    public FinancialRecord create(FinancialRecord record) {
        return repo.save(record);
    }

    // READ ALL
    @Override
    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    // UPDATE
    @Override
    public FinancialRecord update(Long id, FinancialRecord record) {
        FinancialRecord existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

        existing.setAmount(record.getAmount());
        existing.setType(record.getType());
        existing.setCategory(record.getCategory());
        existing.setDate(record.getDate());
        existing.setNotes(record.getNotes());

        return repo.save(existing);
    }

    // DELETE
    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Record not found with id: " + id);
        }
        repo.deleteById(id);
    }

    // TOTAL INCOME
    @Override
    public double totalIncome() {
        return repo.findByType(Type.INCOME)
                .stream()
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    // TOTAL EXPENSE
    @Override
    public double totalExpense() {
        return repo.findByType(Type.EXPENSE)
                .stream()
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    // BALANCE
    @Override
    public double balance() {
        return totalIncome() - totalExpense();
    }
}