package com.finance.dashboard.service;

import com.finance.dashboard.model.FinancialRecord;
import java.util.List;

public interface FinancialService {

    FinancialRecord create(FinancialRecord record);

    List<FinancialRecord> getAll();

    FinancialRecord update(Long id, FinancialRecord record);

    void delete(Long id);

    double totalIncome();

    double totalExpense();

    double balance();
}
